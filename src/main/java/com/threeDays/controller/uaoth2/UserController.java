package com.threeDays.controller.uaoth2;


import com.threeDays.POJO.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @GetMapping("/cuSetting")
    public String cuSetting(Model model, HttpServletRequest httpServletRequest) {
        Customer customer = (Customer) httpServletRequest.getSession().getAttribute("customer");
        model.addAttribute("customer",customer);
        return "cu-setting-info";
    }

}

