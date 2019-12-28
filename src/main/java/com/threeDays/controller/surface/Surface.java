package com.threeDays.controller.surface;

import com.threeDays.POJO.Goods;
import com.threeDays.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassNameSurface
 * @Date2019-12-2116:55
 **/
@Controller
public class Surface {
    @Autowired
    GoodsService goodsService;

    @RequestMapping("/")
    public String login1(Model model) {
        List<Goods> goodsList = new ArrayList<>();
        goodsService.getGoodsList(goodsList);
        List<Goods> goodsList1 = goodsList.subList(0, 3);
        List<Goods> goodsList2 = goodsList.subList(5, 8);
        model.addAttribute("GoodsList1", goodsList1);
        model.addAttribute("GoodsList2", goodsList2);
        return "index";
    }
    @RequestMapping("/index")
    public String login(Model model) {
        List<Goods> goodsList = new ArrayList<>();
        goodsService.getGoodsList(goodsList);
        List<Goods> goodsList1 = goodsList.subList(0, 3);
        List<Goods> goodsList2 = goodsList.subList(4, 7);
        model.addAttribute("GoodsList1", goodsList1);
        model.addAttribute("GoodsList2", goodsList2);
        return "index";
    }

    @RequestMapping("/account")
    public String goToSellerLogin() {
        return "/account";
    }
}

