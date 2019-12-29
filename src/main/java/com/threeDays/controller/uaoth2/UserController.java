package com.threeDays.controller.uaoth2;


import com.threeDays.POJO.Customer;
import com.threeDays.POJO.cuOrderGoods;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @GetMapping("/cuSetting")
    public String cuSetting(@RequestParam("cuOrderGoods") cuOrderGoods cuOrderGoods, Model model, HttpServletRequest httpServletRequest) {
        Customer customer = (Customer) httpServletRequest.getSession().getAttribute("customer");
        model.addAttribute("customer",customer);
        model.addAttribute("cuOrderGoods",cuOrderGoods);
        return "cu-setting-info";
    }
    @PostMapping("/Setting")
    public String setting(@RequestParam("name")String name,@RequestParam("tel")String tel,@RequestParam("address")String address,
                          HttpServletRequest httpServletRequest){
        Customer customer = (Customer) httpServletRequest.getSession().getAttribute("customer");
        customer.setCu_Address(address);
        customer.setCu_Telephone(tel);
        customer.setCu_Name(name);
        return "index";
    }

}

