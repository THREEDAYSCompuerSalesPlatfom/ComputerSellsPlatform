package com.threeDays.controller.sellerlogin;

import com.threeDays.POJO.Seller;
import com.threeDays.service.Sellerservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;

@Controller
public class LoginController {
    @Autowired
    private Sellerservice sellerservice;

    /**
     * 从客户登录界面转到卖家登录界面
     * 卖家登录界面：selogin.html
     */
    @RequestMapping("/selogin")
    public String goToSellerLogin() {
        return "selogin";
    }

    /**
     * 商家登录表单提交
     * 表单action：/selogin/submit
     * 错误时跳转至selogin界面，model中含错误信息：errormsg，可在页面直接展示
     * 正确登陆时，session中存有Seller_id,作为是否登录的依据。成功后跳转seller界面
     **/
    @PostMapping("/selogin/submit")
    public String login(@RequestParam("name") String name, @RequestParam("password") String password, Model model, HttpServletRequest Request) {
        BigInteger Sellerid = sellerservice.login(name, password);
        if (Sellerid.equals(new BigInteger("-1"))) {
            model.addAttribute("errormsg", "账号不存在");
            return "redirect:/selogin";
        } else if (Sellerid.equals(new BigInteger("-2"))) {
            model.addAttribute("errormsg", "密码错误");
            return "redirect:/selogin";
        } else {
            Request.getSession().setAttribute("Seller_id", Sellerid);
            return "redirect:/seller";
        }
    }

    /**
     * 进入商家注册界面
     * 返回界面seregesiter
     */
    @RequestMapping("/sellerregesiter")
    public String goToSellerRegester() {
        return "seregesiter";
    }

    /**
     * 商家注册表单提交
     * post请求
     * 参数需包含seller_name,seller_address,seller_tel,password
     * 错误时跳转至seregesiter界面，model中含错误信息：errormsg，可在页面直接展示
     * 成功注册时，session中存有Seller_id,作为是否登录的依据。成功后跳转seller界面
     * */
    @PostMapping("/sellerregesiter/submit")
    public String regesiter(Seller seller, @RequestParam("password") String password, Model model,HttpServletRequest request) {
        if (seller.getSeller_address() == null) {
            model.addAttribute("errormsg", "地址未添加");
            model.addAttribute("Seller", seller);//使得重新加载的网页不丢失用户原有填写的信息
            return "redirect:/seregesiter";
        } else if (seller.getSeller_tel() == null) {
            model.addAttribute("errormsg", "联系方式未添加");
            model.addAttribute("Seller", seller);//使得重新加载的网页不丢失用户原有填写的信息
            return "redirect:/seregesiter";
        }

        BigInteger seller_id = sellerservice.register(seller, password);
        if (seller_id.equals(new BigInteger("-1"))) {
            model.addAttribute("errormsg", "账号已存在");
            model.addAttribute("Seller", seller);//使得重新加载的网页不丢失用户原有填写的信息
            return "redirect:/seregesiter";
        }else {//注册成功，进入商家界面
            request.getSession().setAttribute("Seller_id", seller_id);
            return "redirect:/seller";
        }

    }

    /**
     * 判断商家名是否已经存在,可在用户注册时输入商家名后，立即检测商家是否存在并在在网页提示
     * 已经存在返回1
     * 不存在返回0
     * */
    @PostMapping("/sellerregesiter/isNameExist")
    @ResponseBody
    public int isNameExist(@RequestParam("seller_name") String seller_name){
        return sellerservice.isNameExist(seller_name);
    }

}
