package com.threeDays.controller.uaoth2;


import com.threeDays.POJO.Customer;
import com.threeDays.POJO.Goods;
import com.threeDays.POJO.cuOrderGoods;
import com.threeDays.service.CustomerService;
import com.threeDays.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    CustomerService customerService;
    @Autowired
    GoodsService goodsService;

    @GetMapping("/cuSetting")
    public String cuSetting( Model model, HttpServletRequest httpServletRequest) {
        Customer customer = (Customer) httpServletRequest.getSession().getAttribute("customer");
        model.addAttribute("customer",customer);
       // model.addAttribute("cuOrderGoods",cuOrderGoods);
        return "cu-setting-info";
    }
    @GetMapping("/Setting")
    public String setting(@RequestParam("name")String name,@RequestParam("tel")String tel,@RequestParam("address")String address,
                          HttpServletRequest httpServletRequest,Model model){
        Customer customer = (Customer) httpServletRequest.getSession().getAttribute("customer");
        customer.setCu_Address(address);
        customer.setCu_Telephone(tel);
        customer.setCu_Name(name);
        customerService.updateCustomer(customer.getCustomerId(),customer.getCu_Telephone(),customer.getCu_Address(),customer.getCu_Name());


        List<Goods> goodsList = new ArrayList<>();
        goodsService.getGoodsList(goodsList);
        List<Goods> goodsList1 = goodsList.subList(0, 3);
        List<Goods> goodsList2 = goodsList.subList(4, 7);
        model.addAttribute("GoodsList1", goodsList1);
        model.addAttribute("GoodsList2", goodsList2);
        return "index";
    }

}

