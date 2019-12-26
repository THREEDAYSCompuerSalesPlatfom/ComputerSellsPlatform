package com.threeDays.controller.goodsSelect;

import com.threeDays.POJO.BigGoods;
import com.threeDays.POJO.Goods;
import com.threeDays.dao.BigGoodsMapper;
import com.threeDays.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassNameGoodsSelect
 * @Date2019-12-2116:37
 **/
@Controller
public class GoodsSelect {
    @Autowired
    private BigGoodsMapper bigGoodsMapper;
    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/selectbrand/{brand}")
    public String selectBrand(Model model, @PathVariable("brand") String brand) {
        List<BigGoods> bigGoodslist = bigGoodsMapper.getBigGoodsByBrand(brand);
        List<Goods> goodsList =new ArrayList<>();
        for(BigGoods bigGoods:bigGoodslist){
            goodsList.add(goodsService.getGoods(bigGoods.getBigGoodsId()));
        }

        int size = goodsList.size() / 3 + 1;
        List<List<Goods>> biggoods = new ArrayList<>();
        // BigGoods[][] biggoods = new BigGoods[3][size];
        int a = 0, j = 0;
        biggoods.add(new ArrayList<>());
        for (int i = 0; i < goodsList.size(); i++) {
            biggoods.get(a).add(goodsList.get(i));
            if (j == 2) {
                a++;
                j = -1;
                biggoods.add(new ArrayList<>());
            }
            j++;
        }

        model.addAttribute("category", "Category : " + brand);
        model.addAttribute("result", biggoods);
        return "/category";//重定向搜索筛选结果界面
    }
}
