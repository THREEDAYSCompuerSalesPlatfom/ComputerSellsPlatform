package com.threeDays.controller.signUp;

import com.threeDays.dao.CustomerMapper;
import com.threeDays.dao.CustomerPasswordMapper;
import com.threeDays.service.CustomerPasswordService;
import com.threeDays.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.util.UUID;

/** 跳转登陆界面完成进入index
 * @ClassNamesignUp
 * @Date2019-12-2219:30
 **/
@Controller
public class signUp {
    @Autowired
    CustomerPasswordService customerPasswordService;
    @Autowired
    CustomerService customerService;
    @RequestMapping(value = "/signUp")
    public String signUp(@RequestParam("customerId")BigInteger customerId,
                         @RequestParam("customerPassword")String customerPassword,
                         HttpServletResponse httpServletResponse,
                         HttpServletRequest httpServletRequest){
        String token = UUID.randomUUID().toString();
        if(customerPasswordService.check(customerId,customerPassword)==1){
            customerService.updateToken(customerId,token);//更新token
            httpServletResponse.addCookie(new Cookie("token", token));//借助token，cookie维持登陆验证是否登陆成功
            httpServletRequest.getSession().setAttribute("result","登录成功");
        }
        else{
            httpServletRequest.getSession().setAttribute("result","用户名密码错误");
        }
        return "redirect:/index";
    }
}
