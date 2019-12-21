package com.threeDays.Interceptor;

import com.threeDays.POJO.User;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;


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
public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("开始请求地址拦截");
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            System.out.println("登陆完成");
            return true;
        } else {
            PrintWriter printWriter = response.getWriter();
            printWriter.write("{code:0,message:\"not login!\"}");
           // response.sendRedirect("/customerLogin");
            return false;
        }
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
