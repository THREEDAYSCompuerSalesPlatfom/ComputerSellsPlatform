package com.threeDays.controller.CartController;

import com.threeDays.POJO.CartGoods;
import com.threeDays.POJO.Customer;
import com.threeDays.POJO.LittleGoods;
import com.threeDays.service.CartGoodsService;
import com.threeDays.service.CartService;
import com.threeDays.service.CustomerService;
import com.threeDays.service.LittleGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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
    @Autowired
    CartGoodsService cartGoodsService;


    @GetMapping("/addToCart")
    @ResponseBody
    public String addToCart(HttpServletRequest httpServletRequest,
                            @RequestParam("edition") String edition,
                            @RequestParam("bigGoodsId") String bigGoodsId,
                            @RequestParam(value = "littleGoodsNum", defaultValue = "1") String littleGoodsNum) {
        System.out.println("edition" + edition + "bigGoodsId" + bigGoodsId + "littlteGoodsNum" + littleGoodsNum);
        Customer customer = (Customer) httpServletRequest.getSession().getAttribute("customer");
        BigInteger littleGoodsId = littleGoodsService.getLittleGoodsId(edition, new BigInteger(bigGoodsId));
        if (cartService.addNewLittleGoods(customer.getCustomerId(), littleGoodsId, Integer.parseInt(littleGoodsNum)) == 1) {
            List<CartGoods> cartGoodsList = cartGoodsService.findCartGoodsByCuid(customer.getCustomerId());
            httpServletRequest.getSession().setAttribute("cartNum", cartGoodsList.size());
            return "success";
        } else {
            return "fail";
        }
    }

    @RequestMapping("/getCart")
    public String getCart(Model model, HttpServletRequest httpServletRequest) {
        Customer customer = (Customer) httpServletRequest.getSession().getAttribute("customer");
        System.out.println("cascascc" + customer.getCustomerId());
        if (customer == null)
            return "account";

        List<CartGoods> cartGoodsList = cartGoodsService.findCartGoodsByCuid(customer.getCustomerId());
        float total = 0;
        for (CartGoods cartGoods : cartGoodsList) {
            total = total + cartGoods.getPrize() * cartGoods.getNum();
        }
        float deliveryCharges = 20;
        float totalprize = total + deliveryCharges;
        httpServletRequest.getSession().setAttribute("cartNum", cartGoodsList.size());
        model.addAttribute("cartGoodsList", cartGoodsList);
        model.addAttribute("total", total);
        model.addAttribute("deliveryCharges", deliveryCharges);
        model.addAttribute("totalprize", totalprize);
        return "cart";
    }

    @RequestMapping("/updateLittleGoods")
    public String changeCart(HttpServletRequest httpServletRequest,
                             @RequestParam("littleGoods") BigInteger oldLittleGoodsId,
                             @RequestParam("edition") String newEdition,
                             @RequestParam(value = "bigGoodsId") BigInteger bigGoodsId) {
        List<LittleGoods> littleGoods = littleGoodsService.findLittleGoods(bigGoodsId);
        for (LittleGoods littleGoods1 : littleGoods) {
            if (littleGoods1.getEdition().equals(newEdition)) {
                BigInteger newLittleGoodsId = littleGoods1.getLittleGoodsId();
                Customer customer = (Customer) httpServletRequest.getSession().getAttribute("customer");
                cartService.changeEdition(customer.getCustomerId(), oldLittleGoodsId, newLittleGoodsId);
            }
        }
        return "redirect:/cart";
    }

    @RequestMapping("/deleteLittleGoods")
    public String deleteLittleGoods(HttpServletRequest httpServletRequest, @RequestParam("littleGoods") BigInteger LittleGoodsId) {
        Customer customer = (Customer) httpServletRequest.getSession().getAttribute("customer");
        List<BigInteger> littleGoodsId = new ArrayList<>();
        littleGoodsId.add(LittleGoodsId);
        cartService.deleteLittleGoods(customer.getCustomerId(), littleGoodsId);
        return "redirect:/cart";
    }

    @GetMapping("/updateNum")
    public String updateNum(BigInteger littlegoodsid, int num, HttpServletRequest httpServletRequest, Model model) {
        System.out.println(littlegoodsid + "   " + num);
        Customer customer = (Customer) httpServletRequest.getSession().getAttribute("customer");
        cartService.addNewLittleGoods(customer.getCustomerId(), littlegoodsid, num);
        List<CartGoods> cartGoodsList = cartGoodsService.findCartGoodsByCuid(customer.getCustomerId());
        httpServletRequest.getSession().setAttribute("cartNum", cartGoodsList.size());
        return getCart(model, httpServletRequest);
    }

    @GetMapping("/deleteLittleGoods")
    public String deleteLittleGoods(Model model, HttpServletRequest httpServletRequest, @RequestParam("littleGoods") BigInteger LittleGoodsId) {
        Customer customer = (Customer) httpServletRequest.getSession().getAttribute("customer");
        List<BigInteger> littleGoodsId = new ArrayList<>();
        littleGoodsId.add(LittleGoodsId);
        cartService.deleteLittleGoods(customer.getCustomerId(), littleGoodsId);
        List<CartGoods> cartGoodsList = cartGoodsService.findCartGoodsByCuid(customer.getCustomerId());
        httpServletRequest.getSession().setAttribute("cartNum", cartGoodsList.size());
        return getCart(model, httpServletRequest);
    }
}

