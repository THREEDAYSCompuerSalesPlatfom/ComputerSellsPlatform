package com.threeDays.Interceptor;

import com.threeDays.POJO.Customer;
import com.threeDays.dao.CustomerMapper;
import com.threeDays.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 17:02 2018/3/12
 * @Modified By:
 */


/**
 * 进入拦截器后首先进入的方法
 * 返回false则不再继续执行
 * 返回true则继续执行
 */
@Service
public class SessionInterceptor implements HandlerInterceptor {
    @Autowired
    CustomerService customerService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0)
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String Token = cookie.getValue();
                    Customer customer = customerService.findByToken(Token);
                    if (customer != null) {
                        request.getSession().setAttribute("customer", customer);
                    }
                    break;
                }

            }
        if(request.getSession().getAttribute("customer")!=null){
            return true;
        }
        String url="/login";
        response.sendRedirect(url);
        return false;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("生成视图之前执行，可以修改ModelAndView");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("生成视图时执行，可以用来处理异常，并记录在日志中");
    }
}
 /*System.out.println("开始请求地址拦截");
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            System.out.println("登陆完成");
            return true;
        } else {
            PrintWriter printWriter = response.getWriter();
            printWriter.write("{code:0,message:\"not login!\"}");
           // response.sendRedirect("/customerLogin");
            return false;
        }*/