package com.threeDays.controller.SellerManage;

import com.threeDays.POJO.Seller;
import com.threeDays.Utils.RegexUtils;
import com.threeDays.service.Sellerservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;


@Controller
public class SellerInfoController {
    @Autowired
    private Sellerservice sellerservice;

    /**
     * 查询seller的所有信息
     * 前端无需加任何参数，登录后，id从seesion中获取
     * 列如要name，前端就加  ${Seller.getSeller_name()}
     * 要啥属性看pojo中Seller.java中的方法
     */
    @PostMapping("/querySellerInfo")
    public String querySellerInfo(HttpServletRequest request, Model model) {
        BigInteger Seller_id = (BigInteger) request.getSession().getAttribute("Seller_id");
        Seller seller = sellerservice.findSellerById(Seller_id);
        model.addAttribute("Seller", seller);
        return "redirect:/seller";
    }


    /**
     * 更新seller信息
     * 参数中需包含seller_name,seller_address,seller_tel,无需id
     * model中含有错误信息telerrormsg,nameerrormsg
     * 前端可对应在各自的输入框旁边显示错误原因
     * model中含有成功信息okmsg
     */
    @PostMapping("/updateSellerInfo")
    public String updateSellerInfo(Seller seller, HttpServletRequest request, Model model) {
        BigInteger Seller_id = (BigInteger) request.getSession().getAttribute("Seller_id");
        if (Seller_id == null) {//未登录
            return "redirect:/selogin";
        }
        seller.setSeller_id(Seller_id);
        if (!(RegexUtils.checkMobile(seller.getSeller_tel()) || RegexUtils.checkPhone(seller.getSeller_tel()))) {
            model.addAttribute("telerrormsg", "联系方式非法");
            return "redirect:/seller";
        }
        int flag = sellerservice.updateSellerInfo(seller);
        if (flag == -3) {
            model.addAttribute("nameerrormsg", "用户名已存在");
            return "redirect:/seller";
        }
        model.addAttribute("okmsg", "更改成功");
        return "redirect:/seller";
    }
}
