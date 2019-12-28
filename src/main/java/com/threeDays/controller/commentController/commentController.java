package com.threeDays.controller.commentController;

import com.threeDays.POJO.cuOrderGoods;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassNamecommentController
 * @Date2019-12-294:23
 **/
@Controller
public class commentController {
    @RequestMapping("/cuComment")
    public String cuComment(@RequestParam("cuOrderGoods") cuOrderGoods cuOrderGoods, Model model){
        model.addAttribute("cuOrderGoods",cuOrderGoods);
        return "cu-comment";
    }
    @RequestMapping("/cuBack")
    public String cuBack(@RequestParam("cuOrderGoods") cuOrderGoods cuOrderGoods, Model model){
        model.addAttribute("cuOrderGoods",cuOrderGoods);
        return "cu-back";
    }
    @RequestMapping("/orderDetail")
    public String orderDetail(@RequestParam("cuOrderGoods") cuOrderGoods cuOrderGoods, Model model){
        model.addAttribute("cuOrderGoods",cuOrderGoods);
        return "orderDetail";
    }
}
