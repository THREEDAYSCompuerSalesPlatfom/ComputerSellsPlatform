package com.threeDays.controller.goodsSearch;

import com.threeDays.POJO.BigGoods;
import com.threeDays.POJO.Goods;
import com.threeDays.service.BigGoodsService;
import com.threeDays.service.GoodsService;
import com.threeDays.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassNameGoodsSearch
 * @Date2019-12-2116:36
 **/
@Controller
public class GoodsSearch {
    @Autowired
    private SearchService searchService;
    @Autowired
    private BigGoodsService bigGoodsService;
    @Autowired
    private GoodsService goodsService;




    /**
     * 牛逼的模糊搜索（zwx快叫爸爸）
     * 返回：符合的商品id列表 List<BigInteger>
     */
//    @PostMapping("/searchGoods1")
//    public String searchGoods(@RequestParam("name") String name, Model model) {
//        fromindex = 0;
//        toindex = 0;
//        resultList = searchService.search(name);
//        toindex = resultList.size() > 3 ? 3 : resultList.size();
//        List<BigInteger> sublist = resultList.subList(0, toindex);
//        bigGoodslist = new ArrayList<>();
//        for (BigInteger bigInteger : sublist) {
//            bigGoodslist.add(bigGoodsService.getBigGoods(bigInteger));
//        }
//        model.addAttribute("result", bigGoodslist);
//        model.addAttribute("size", resultList.size());
//        return "category";
//    }
//
//    @PostMapping("/nextPage")
//    public String nextPage(Model model) {
//        if (resultList.size() == toindex + 1) {
//            model.addAttribute("result", "没了");
//        }
//        fromindex = toindex;
//        toindex = toindex + ((resultList.size() - 1 - toindex) > 3 ? 3 : resultList.size() - 1 - toindex);
//        List<BigInteger> sublist = resultList.subList(fromindex, toindex);
//        bigGoodslist = new ArrayList<>();
//        for (BigInteger bigInteger : sublist) {
//            bigGoodslist.add(bigGoodsService.getBigGoods(bigInteger));
//        }
//        model.addAttribute("result", bigGoodslist);
//        model.addAttribute("size", resultList.size());
//        return "category";
//    }

    /**
     * 返回二维arraylsit方便前端分行展示
     */
    @PostMapping("/searchGoods")
    public String searchGoodss(@RequestParam("name") String name, Model model) {
        List<BigInteger> resultList;
        List<Goods> bigGoodslist = new ArrayList<>();
        bigGoodslist.clear();
        resultList = searchService.search(name);
        for (BigInteger bigInteger : resultList) {
            bigGoodslist.add(goodsService.getGoods(bigInteger));
        }
        int size = bigGoodslist.size() / 3 + 1;
        List<List<Goods>> goodslist = new ArrayList<>();
        // BigGoods[][] biggoods = new BigGoods[3][size];
        int a = 0, j = 0;
        goodslist.add(new ArrayList<>());
        for (int i = 0; i < bigGoodslist.size(); i++) {
            goodslist.get(a).add(bigGoodslist.get(i));
            if (j == 2) {
                a++;
                j = -1;
                goodslist.add(new ArrayList<>());
            }
            j++;
        }

        model.addAttribute("category","Category : Search");
        model.addAttribute("result", goodslist);
        return "category";
    }
}
