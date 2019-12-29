package com.threeDays.controller.orderController;

import com.threeDays.POJO.Customer;
import com.threeDays.POJO.LittleGoods;
import com.threeDays.POJO.cuOrderGoods;
import com.threeDays.service.LittleGoodsService;
import com.threeDays.service.OrderService;
import com.threeDays.service.cuOrderGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.*;
//https://blog.csdn.net/mar5342/article/details/85244669

/**
 * @ClassNameorderController
 * @Date2019-12-2316:41
 **/
@Controller
public class orderController {
    @Autowired
    OrderService orderService;
    @Autowired
    LittleGoodsService littleGoodsService;
    @Autowired
    cuOrderGoodsService cuOrderGoodsService;

    @GetMapping("/cuIndex")
    public String order(Model model, HttpServletRequest httpServletRequest) {
        Customer customer = (Customer) httpServletRequest.getSession().getAttribute("customer");
        model.addAttribute("customer", customer);
        List<cuOrderGoods> cuOrderGoods = cuOrderGoodsService.getAll(customer.getCustomerId());
        model.addAttribute("cuOrderGoods", cuOrderGoods);
        return "cu-index";
    }

    @GetMapping("/changeStatus")
    @ResponseBody
    public String changeStatus(BigInteger order_id, Model model) {
        String s = orderService.changeStatus(order_id, new Integer(3));
//        List<cuOrderGoods> cuOrderGoodsList =(List<cuOrderGoods>) model.getAttribute("cuOrderGoods");
//        Iterator<cuOrderGoods> i=cuOrderGoodsList.iterator();
//        while(i.hasNext()){
//            cuOrderGoods s=i.next();
//            if(s.getOrderId()==order_id){
//            s.setStatus(3);
//        }
//    }
//        model.addAttribute("cuOrderGoods",cuOrderGoodsList);
        System.out.println(s);
        return s;
    }
}


