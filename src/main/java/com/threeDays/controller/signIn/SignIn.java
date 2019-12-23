package com.threeDays.controller.signIn;

import com.threeDays.dao.CustomerMapper;
import com.threeDays.dao.CustomerPasswordMapper;
import com.threeDays.service.CustomerPasswordService;
import com.threeDays.service.CustomerService;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.base.Captcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class SignIn {
    @Autowired
    CustomerPasswordService customerPasswordService;
    @Autowired
    CustomerService customerService;

    /**
     * 获取验证码图片(项目源地址：https://github.com/whvcse/EasyCaptcha)
     * 前端代码：<img src="/captcha" width="130px" height="48px" />
     * 不要忘了把/captcha路径排除登录拦截
     */
    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 使用gif验证码
        GifCaptcha gifCaptcha = new GifCaptcha(130, 48);
        gifCaptcha.setCharType(Captcha.TYPE_NUM_AND_UPPER);
        gifCaptcha.setFont(Captcha.FONT_8);
        System.out.println(gifCaptcha.text());
        CaptchaUtil.out(gifCaptcha, request, response);
    }
    @RequestMapping(value = "/SignIn")
    public String signUp(@RequestParam("customerId")BigInteger customerId,
                         @RequestParam("customerPassword")String customerPassword,
                         @RequestParam("verCode") String verCode,
                         HttpServletResponse httpServletResponse,
                         HttpServletRequest httpServletRequest, Model model){
        verCode = verCode.toUpperCase();//将用户输入转为大写
        if (!CaptchaUtil.ver(verCode, httpServletRequest)) {
            CaptchaUtil.clear(httpServletRequest);  // 清除session中的验证码
            model.addAttribute("errormsg", "验证码错误");
            return "redirect:/SignIn";
        }
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
