package com.threeDays.controller.orderController;

import com.threeDays.POJO.Customer;
import com.threeDays.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.List;

/**
 * @ClassNameorderController
 * @Date2019-12-2316:41
 **/
@Controller
public class orderController {
    @Autowired
    OrderService orderService;
    public String order(Model model, HttpServletRequest httpServletRequest){
        Customer customer= (Customer) httpServletRequest.getSession().getAttribute("customer");
        List<BigInteger>orderList =orderService.findOrderByCuId(customer.getCustomerId());
        for(BigInteger i:orderList){
           List<BigInteger> littleGoodsOrder= orderService.findGoodsIdByOrderId(i);
            model.addAttribute("orderLittleGoods",littleGoodsOrder);
        }


        return "order";
    }
}
