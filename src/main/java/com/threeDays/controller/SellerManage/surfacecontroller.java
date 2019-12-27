package com.threeDays.controller.SellerManage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class surfacecontroller {
    @RequestMapping("sellermanage")
    public String sellermanage(HttpServletRequest request, Model model){
        return "seller/sellerindex";
    }
    @RequestMapping("home")
    public String home(HttpServletRequest request, Model model){
        return "seller/home";
    }
    @RequestMapping("sellerinfo")
    public String sellerinfo(HttpServletRequest request, Model model){
        return "seller/seller";
    }
    @RequestMapping("password")
    public String password(HttpServletRequest request, Model model){
        return "seller/password";
    }
    @RequestMapping("goods")
    public String goods(HttpServletRequest request, Model model){
        return "seller/goods";
    }
//    @RequestMapping("deliver")
//    public String deliver(HttpServletRequest request, Model model){
//        return "seller/deliver";
//    }
//    @RequestMapping("comment")
//    public String comment(HttpServletRequest request, Model model){
//        return "seller/comment";
//    }
    @RequestMapping("backgoods")
    public String backgoods(HttpServletRequest request, Model model){
        return "seller/backgoods";
    }
    @RequestMapping("aftersale")
    public String aftersale(HttpServletRequest request, Model model){
        return "seller/aftersale";
    }
    @RequestMapping("aftersale2")
    public String aftersale2(HttpServletRequest request, Model model){
        return "seller/aftersale2";
    }
}
