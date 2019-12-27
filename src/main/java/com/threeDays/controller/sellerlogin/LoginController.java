package com.threeDays.controller.sellerlogin;

import com.threeDays.POJO.Seller;
import com.threeDays.Utils.RegexUtils;
import com.threeDays.service.Sellerservice;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;

@Controller
public class LoginController {
    @Autowired
    private Sellerservice sellerservice;


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

    /**
     * 从客户登录界面转到卖家登录界面
     * 卖家登录界面：selogin.html
     */
    @RequestMapping("/selogin")
    public String goToSellerLogin() {
        return "se-login";
    }


    /**
     * 商家登录表单提交
     * 表单action：/selogin/submit
     * 错误时跳转至selogin界面，model中含错误信息：errormsg，可在页面直接展示
     * 正确登陆时，session中存有Seller_id,作为是否登录的依据。成功后跳转seller界面
     **/
    @PostMapping("/selogin/submit")
    public String login(@RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("verCode") String verCode, Model model, HttpServletRequest Request) {
        System.out.println("login name:"+name);
        verCode = verCode.toUpperCase();//将用户输入转为大写
        if (!CaptchaUtil.ver(verCode, Request)) {
            CaptchaUtil.clear(Request);  // 清除session中的验证码
            model.addAttribute("errormsg", "验证码错误");
            System.out.println("验证码错误");
            return "se-login";
        }
        BigInteger Sellerid = sellerservice.login(name, password);
        if (Sellerid.equals(new BigInteger("-1"))) {
            model.addAttribute("errormsg", "账号不存在");
            System.out.println("账号不存在");
            return "se-login";
        } else if (Sellerid.equals(new BigInteger("-2"))) {
            model.addAttribute("errormsg", "密码错误");
            System.out.println("密码错误");
            return "se-login";
        } else {
            Request.getSession().setAttribute("Seller_id", Sellerid);
            Request.getSession().setAttribute("Seller", sellerservice.findSellerById(Sellerid));
            System.out.println("login finish");
            return "seller/sellerindex";
        }
    }

    /**
     * 进入商家注册界面
     * 返回界面seregesiter
     */
    @RequestMapping("/sellerregesiter")
    public String goToSellerRegester() {
        return "se-register";
    }

    /**
     * 商家注册表单提交
     * post请求
     * 参数需包含seller_name,seller_address,seller_tel,password
     * 错误时跳转至seregesiter界面，model中含错误信息：errormsg，可在页面直接展示
     * 成功注册时，session中存有Seller_id,作为是否登录的依据。成功后跳转seller界面
     */
    @PostMapping("/sellerregesiter/submit")
    public String regesiter(Seller seller, @RequestParam("password") String password,@RequestParam("againpassword")String againpassword, @RequestParam("verCode") String verCode, Model model, HttpServletRequest request) {
        verCode = verCode.toUpperCase();//将用户输入转为大写
        if (!CaptchaUtil.ver(verCode, request)) {
            CaptchaUtil.clear(request);  // 清除session中的验证码
            model.addAttribute("errormsg", "验证码错误");
            return "/se-register";
        }
        seller.setBalance(0);
        if(!password.equals(againpassword)){
            model.addAttribute("errormsg", "密码不同");
            return "se-register";
        }
        if (seller.getSeller_address() == null) {
            model.addAttribute("errormsg", "地址未添加");
            model.addAttribute("Seller", seller);//使得重新加载的网页不丢失用户原有填写的信息
            return "se-register";
        } else if (seller.getSeller_tel() == null) {
            model.addAttribute("errormsg", "联系方式未添加");
            model.addAttribute("Seller", seller);//使得重新加载的网页不丢失用户原有填写的信息
            return "/se-register";
        } else if (!(RegexUtils.checkMobile(seller.getSeller_tel()) || RegexUtils.checkPhone(seller.getSeller_tel()))) {
            model.addAttribute("errormsg", "联系方式非法");
            model.addAttribute("Seller", seller);//使得重新加载的网页不丢失用户原有填写的信息
            return "/se-register";
        }

        BigInteger seller_id = sellerservice.register(seller, password);
        if (seller_id.equals(new BigInteger("-1"))) {
            model.addAttribute("errormsg", "账号已存在");
            model.addAttribute("Seller", seller);//使得重新加载的网页不丢失用户原有填写的信息
            return "redirect:/se-register";
        } else {//注册成功，进入商家界面
            request.getSession().setAttribute("Seller_id", seller_id);
            request.getSession().setAttribute("Seller", sellerservice.findSellerById(seller_id));
            return "seller/sellerindex";
        }

    }

    /**
     * 判断商家名是否已经存在,可在用户注册时输入商家名后，立即检测商家是否存在并在在网页提示
     * 已经存在返回1
     * 不存在返回0
     */
    @PostMapping("/sellerregesiter/isNameExist")
    @ResponseBody
    public int isNameExist(@RequestParam("seller_name") String seller_name) {
        return sellerservice.isNameExist(seller_name);
    }

}
