package com.threeDays.controller;

import com.threeDays.POJO.Order;
import com.threeDays.POJO.Seller;
import com.threeDays.dao.OrderMapper;
import com.threeDays.dao.SellerMapper;
import com.threeDays.service.YTATestservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;

@Controller
public class YTAtestController {
    @Autowired
    private YTATestservice testservice;
    @Autowired
    private SellerMapper sellerMapper;
    @Autowired
    private OrderMapper orderMapper;

    @RequestMapping("/findfindSellerById/{seller_id}")
    @ResponseBody
    public Seller findSellerById(@PathVariable("seller_id") BigInteger seller_id){
        return testservice.findSellerById(seller_id);
    }
    @GetMapping("/test")
    @ResponseBody
    public String testC(Seller seller){
        return seller.getSeller_name();
    }

    @RequestMapping("/findorder/{order_id}")
    @ResponseBody
    public Order findOrder(@PathVariable("order_id")BigInteger order_id){
        Order order=orderMapper.findOrderById(order_id);
        if(order==null)
            System.out.println("null");
        else
            System.out.println("ok");
        return order;
    }



}
