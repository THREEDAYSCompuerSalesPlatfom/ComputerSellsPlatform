package com.threeDays.controller.orderController;

import com.threeDays.POJO.Customer;
import com.threeDays.POJO.LittleGoods;
import com.threeDays.service.LittleGoodsService;
import com.threeDays.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public String order(Model model, HttpServletRequest httpServletRequest) {
        Map<BigInteger, List<LittleGoods>> map = new HashMap<>();
        Customer customer = (Customer) httpServletRequest.getSession().getAttribute("customer");
        List<LittleGoods> littleGoodsList = new ArrayList<>();
        List<BigInteger> orderList = orderService.findOrderByCuId(customer.getCustomerId());
        for (BigInteger i : orderList) {
            List<BigInteger> littleGoodsIdOrder = orderService.findGoodsIdByOrderId(i);
            for (BigInteger j : littleGoodsIdOrder) {
                littleGoodsList.add(littleGoodsService.findLittleGoodsById(j));
                map.put(i, littleGoodsList);//订单号，littleGoods
            }
            littleGoodsList.clear();
        }
        model.addAttribute("map",map);//https://blog.csdn.net/mar5342/article/details/85244669
        return "order";
    }
}


