package com.threeDays.controller.changePassword;

import com.threeDays.service.CustomerPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;


/**
 * @ClassNameChangePassword
 * @Date2019-12-2223:42
 **/
@Controller
public class ChangePassword {
    @Autowired
    CustomerPasswordService customerPasswordService;
    @RequestMapping(value="/changePassword")
    public String changePassword(@RequestParam("customerId")BigInteger customerId,
                                 @RequestParam("customerPassword")String customerPassword,
                                 HttpServletRequest httpServletRequest){
        customerPasswordService.changePassword(customerId,customerPassword);
        httpServletRequest.getSession().setAttribute("changePassword","修改成功");
        return "redirect:/changePassword";
    }
}
