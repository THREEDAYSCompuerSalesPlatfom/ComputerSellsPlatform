package com.threeDays.controller.customerLogin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassNameLoginController
 * @Date2019-12-2116:21 session cookie 拦截器
 * 点击第三方github登陆 url 登陆完成进入index
 **/

@Controller
public class GithubLogin {
    @RequestMapping("/gitLogin")
    public String login() {
        return "index";
    }
}

/*HttpServletRequest httpServletRequest
    Cookie[] cookies = httpServletRequest.getCookie()
          for(Cookie cookie:cookies){
                  if(cookie.getName.equal("token")){
                  String token=cookie.getValue();
                  Customer customer=Customer.findByToken(token)
                  if(customer!=null){
                  httpServletRequest.getSession().setAttribute("customer",customer);
                  }
                  break;
                  }
                  }*/
/*HttpSession session = request.getSession();
        if (name.equals("root") && pwd.equals("root")) {
        User user = new User();
        user.setName(name);
        session.setAttribute("user", user);
        return "登录成功";
    } else {
        return "用户名或密码错误!";
    }
}*/

