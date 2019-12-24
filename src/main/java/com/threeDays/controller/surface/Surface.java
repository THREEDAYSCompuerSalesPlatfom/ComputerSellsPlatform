package com.threeDays.controller.surface;

import com.threeDays.POJO.Goods;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassNameSurface
 * @Date2019-12-2116:55
 **/
@Controller
public class Surface {
    @RequestMapping("/index")
    public String login(Model model) {
        List<Goods> goodsList=new ArrayList<>();

        model.addAttribute("GoodsList",goodsList);
        return "index.html";
    }
    @RequestMapping("/")
    public String login1(Model model) {
        List<Goods> goodsList=new ArrayList<>();

        model.addAttribute("GoodsList",goodsList);
        return "index.html";
    }
    @RequestMapping("/account")
    public String goToSellerLogin() {
        return "/account";
    }
}

