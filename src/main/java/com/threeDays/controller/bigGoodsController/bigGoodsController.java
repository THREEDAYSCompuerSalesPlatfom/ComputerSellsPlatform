package com.threeDays.controller.bigGoodsController;

import com.threeDays.POJO.BigGoods;
import com.threeDays.POJO.Customer;
import com.threeDays.POJO.Goods;
import com.threeDays.POJO.LittleGoods;
import com.threeDays.controller.Filecontroller.UploadController;
import com.threeDays.service.BigGoodsService;
import com.threeDays.service.CartService;
import com.threeDays.service.GoodsService;
import com.threeDays.service.LittleGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassNamebigGoodsController
 * @Date2019-12-230:58 预想修改加入session
 **/
@Controller
public class bigGoodsController {
    @Autowired
    BigGoodsService bigGoodsService;
    @Autowired
    LittleGoodsService littleGoodsService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    CartService cartService;
    @Autowired
    UploadController uploadController;
    @RequestMapping("/cartNum")
    public String cartNum(Model model, HttpServletRequest httpServletRequest){
        Customer customer = (Customer) httpServletRequest.getSession().getAttribute("customer");
        List<LittleGoods>littleGoods=cartService.getSellerLittleGoods(customer.getCustomerId());
        model.addAttribute("number",littleGoods.size());
        return "product";
    }
    @RequestMapping("/addCart")
    public String addToCart( HttpServletRequest httpServletRequest,
                             @RequestParam("bigGoodsId") BigInteger bigGoodsId,
                             @RequestParam("edition")String edition,
                             @RequestParam(value = "littleGoodsNum", defaultValue = "1") int littleGoodsNum) {
        Customer customer = (Customer) httpServletRequest.getSession().getAttribute("customer");
        BigInteger littleGoodsId=littleGoodsService.getLittleGoodsId(edition,bigGoodsId);
        cartService.addNewLittleGoods(customer.getCustomerId(), littleGoodsId, littleGoodsNum);
        return "redirect:product";
    }
    /**
     * 具体大商品展示接口
     */

    @RequestMapping(value = "/bigGoods", method = RequestMethod.GET)
    public String lookBigGoods(Model model, @RequestParam("bigGoodsId") BigInteger bigGoodsId,HttpServletResponse httpServletResponse) {

        BigInteger sellerId = bigGoodsService.getSellerId(bigGoodsId);
        System.out.println(sellerId);
        Float minPrice = littleGoodsService.minPrice(bigGoodsId, sellerId);
        minPrice=(minPrice==null)?0:minPrice;
        Float maxPrice = littleGoodsService.maxPrice(bigGoodsId, sellerId);
        maxPrice=(maxPrice==null)?0:minPrice;
        BigGoods bigGoods = bigGoodsService.getBigGoods(bigGoodsId);
        List<String> edition = littleGoodsService.getEdition(bigGoodsId);
        List<Goods> goodsList = new ArrayList<>();
        goodsService.getGoodsList(goodsList);
        Goods goods = goodsService.getGoods(bigGoodsId);
        String[] imgName = uploadController.AllImageNames(bigGoodsId);
        if(imgName==null){
            model.addAttribute("error","图片文件不存在");
        }
        List<String> Name = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < imgName.length; i++) {
            if (!imgName[i].equals("index") && !imgName[i].equals("show")&&!imgName[i].equals("description")) {
                if (j < 4) {
                    Name.add(imgName[i]);
                    j++;
                }
            }
        }
        String  description=uploadController.getText(bigGoodsId,httpServletResponse);
        model.addAttribute("description",description);
        System.out.println(description);
        List<Goods> goodsList1 = goodsList.subList(0, 3);
        Goods goods1 = goodsList.get(3);
        Goods goods2 = goodsList.get(4);
        Goods goods3 = goodsList.get(5);
        model.addAttribute("bigGoods", bigGoods);
        model.addAttribute("edition", edition);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("goods", goods);
        model.addAttribute("imgName", Name);
        model.addAttribute("goodsList1", goodsList1);
        model.addAttribute("goods1", goods1);
        model.addAttribute("goods2", goods2);
        model.addAttribute("goods3", goods3);
        return "product";
    }

    /**
     * 粗略大商品展示接口
     */

    @RequestMapping(value = "/CbigGoods")
    public void ClookBigGoods(Model model, @RequestParam("bigGoodsId") BigInteger bigGoodsId) {
        BigInteger sellerId = bigGoodsService.getSellerId(bigGoodsId);
        float minPrice = littleGoodsService.minPrice(bigGoodsId, sellerId);
        float maxPrice = littleGoodsService.maxPrice(bigGoodsId, sellerId);
        BigGoods bigGoods = bigGoodsService.getBigGoods(bigGoodsId);
        model.addAttribute("bigGoods", bigGoods);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
    }
}
