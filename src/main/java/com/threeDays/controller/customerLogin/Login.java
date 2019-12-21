package com.threeDays.controller.customerLogin;

import com.threeDays.POJO.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ClassNameLoginController
 * @Date2019-12-2116:21
 *
 * session cookie 拦截器
 * 第三方github登陆
 **/
@Controller
@RestController
public class Login {
    @RequestMapping("/Login")
    public String login(String name,String pwd,HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(name.equals("root")&&pwd.equals("root")) {
            User user = new User();
            user.setName(name);
            session.setAttribute("user",user);
            return "登录成功";
        } else {
            return "用户名或密码错误!";
        }
    }
}

