package com.threeDays.controller.loginOut;

import com.threeDays.POJO.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ClassNameloginOut
 * @Date2019-12-294:31
 **/
@Controller
public class loginOut {
    @RequestMapping("/user/loginOut")
    public String loginOut(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().invalidate();
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                cookie.setValue(null);

            }
        }
        return "redirect:/index";
    }
}



