package com.threeDays.controller.CartController;

import com.threeDays.POJO.Customer;
import com.threeDays.POJO.LittleGoods;
import com.threeDays.service.CartService;
import com.threeDays.service.CustomerService;
import com.threeDays.service.LittleGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassNameCartController
 * @Date2019-12-230:56
 **/
@Controller
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    CustomerService customerService;
    @Autowired
    LittleGoodsService littleGoodsService;

    @RequestMapping("/addToCart")
    public String addToCart(HttpServletRequest httpServletRequest,
                            @RequestParam("littleGoodsId") BigInteger littleGoodsId,
                            @RequestParam(value = "littleGoodsNum", defaultValue = "1") int littleGoodsNum) {
        Customer customer=(Customer) httpServletRequest.getSession().getAttribute("customer");
        cartService.addNewLittleGoods(customer.getCustomerId(), littleGoodsId, littleGoodsNum);
        return "bigGoods";
    }

    @RequestMapping("/getCart")
    public String getCart(Model model, HttpServletRequest httpServletRequest) {
        Customer customer=(Customer) httpServletRequest.getSession().getAttribute("customer");
        List<LittleGoods> sellerLittleGoods = cartService.getSellerLittleGoods(customer.getCustomerId());
        /*List<BigInteger> sellerIdList = new ArrayList<>();
        for (BigInteger sellerId : sellerLittleGoods.keySet()) {
            sellerIdList.add(sellerId);
        }
        model.addAttribute("sellerIdList", sellerIdList);*/
        //通过商家Id列表Map映射获取LittleGoods
        model.addAttribute("sellerLittleGoods", sellerLittleGoods);
        return "cart.html";
    }
    @RequestMapping("/updateLittleGoods")
    public String changeCart( HttpServletRequest httpServletRequest,
                             @RequestParam("littleGoods") BigInteger oldLittleGoodsId,
                             @RequestParam("edition") String newEdition,
                             @RequestParam(value = "bigGoodsId") BigInteger bigGoodsId){
        List<LittleGoods>littleGoods= littleGoodsService.findLittleGoods(bigGoodsId);
        for(LittleGoods littleGoods1:littleGoods){
           if(littleGoods1.getEdition().equals(newEdition)){
               BigInteger newLittleGoodsId=littleGoods1.getLittleGoodsId();
               Customer customer=(Customer) httpServletRequest.getSession().getAttribute("customer");
               cartService.changeEdition(customer.getCustomerId(),oldLittleGoodsId,newLittleGoodsId);
           }
        }
        return "redirect:/cart";
    }
    @RequestMapping("/deleteLittleGoods")
    public String deleteLittleGoods( HttpServletRequest httpServletRequest,   @RequestParam("littleGoods") BigInteger LittleGoodsId){
        Customer customer=(Customer) httpServletRequest.getSession().getAttribute("customer");
        List<BigInteger> littleGoodsId=new ArrayList<>();
        littleGoodsId.add(LittleGoodsId);
        cartService.deleteLittleGoods(customer.getCustomerId(),littleGoodsId);
        return "redirect:/cart";
    }
}

