package com.threeDays.controller.surface;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassNameSurface
 * @Date2019-12-2116:55
 **/
@Controller
public class Surface {
    @RequestMapping("/index")
    public String login(String name, String pwd, HttpServletRequest request) {
        return "index.html";
    }
    @RequestMapping("/account")
    public String goToSellerLogin() {
        return "/account";
    }
}

