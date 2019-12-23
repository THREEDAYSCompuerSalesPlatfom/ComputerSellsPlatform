package com.threeDays.controller.bigGoodsController;

import com.threeDays.POJO.BigGoods;
import com.threeDays.POJO.LittleGoods;
import com.threeDays.service.BigGoodsService;
import com.threeDays.service.LittleGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;

/**
 * @ClassNamebigGoodsController
 * @Date2019-12-230:58
 * 预想修改加入session
 **/
@Controller
public class bigGoodsController {
    @Autowired
    BigGoodsService bigGoodsService;
    @Autowired
    LittleGoodsService littleGoodsService;
    /**
     * 具体大商品展示接口
     * */

    @RequestMapping(value = "/bigGoods")
    public String lookBigGoods(Model model, @RequestParam("bigGoodsId") BigInteger bigGoodsId) {
        BigInteger sellerId=bigGoodsService.getSellerId(bigGoodsId);
        float minPrice=littleGoodsService.minPrice(bigGoodsId,sellerId);
        float maxPrice=littleGoodsService.maxPrice(bigGoodsId,sellerId);
        BigGoods bigGoods = bigGoodsService.getBigGoods(bigGoodsId);
        model.addAttribute("bigGoods", bigGoods);
        model.addAttribute("minPrice",minPrice);
        model.addAttribute("maxPrice",maxPrice);
        return "bigGoods";
    }
}
