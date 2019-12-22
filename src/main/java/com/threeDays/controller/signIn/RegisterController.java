package com.threeDays.controller.signIn;

import com.threeDays.POJO.Customer;
import com.threeDays.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassNamesignIn
 * @Date2019-12-2219:10
 * 点击网站账号注册 跳转注册界面 完成进入登陆界面
 **/
@RestController
public class RegisterController {
    @Autowired
    CustomerService customerService;

    @RequestMapping("/signIn")
    public String signIn(@RequestParam("cu_Telephone") String cu_Telephone,
                         @RequestParam("cu_Address") String cu_Address,
                         @RequestParam("cu_Name") String cu_Name,
                         @RequestParam("customerPassWord") String customerPassWord,
                         HttpServletRequest httpServletRequest) {
        String token = customerService.createCustomer(cu_Telephone, cu_Address, cu_Name,customerPassWord);
        Customer newCustomer=customerService.findByToken(token);//从这里返回对象相应填充
        httpServletRequest.getSession().setAttribute("user", newCustomer);//id填充
        return "redirect:/Login";
    }
}
