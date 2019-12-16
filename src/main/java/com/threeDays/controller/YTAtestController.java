package com.threeDays.controller;

import com.threeDays.POJO.AfterSales;
import com.threeDays.POJO.Deliver;
import com.threeDays.POJO.Order;
import com.threeDays.POJO.Seller;
import com.threeDays.dao.OrderMapper;
import com.threeDays.dao.SellerMapper;
import com.threeDays.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@Controller
public class YTAtestController {
    @Autowired
    private YTATestservice testservice;
    @Autowired
    private AfterSalesService afterSalesService;
    @Autowired
    private DeliverService deliverService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private Sellerservice sellerservice;
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

    /**
     * AfterSalesService
     */
    @GetMapping("/findAfterSalesById/{aftersales_id}")
    @ResponseBody
    public AfterSales findAfterSalesById(@PathVariable("aftersales_id") BigInteger aftersales_id) {
        return afterSalesService.findAfterSalesById(aftersales_id);
    }

    @GetMapping("insertAfterSales")
    @ResponseBody
    public BigInteger insertAfterSales(AfterSales afterSales){
//        System.out.println(afterSales.getExpress());
//        System.out.println(afterSales.getAfter_id());
        return afterSalesService.insertAfterSales(afterSales);
    }


    @GetMapping("changeAfterSalesStatus")
    @ResponseBody
    public String changeAfterSalesStatus(BigInteger order_id,int status){
        return afterSalesService.changeAfterSalesStatus(order_id,status);
    }


    /**
     * Sellerservice
    */

    @PostMapping("Sellerlogin")
    @ResponseBody
    public BigInteger login(@RequestParam("name") String name,@RequestParam("password") String password){
        System.out.println(name+"  "+password);
        return sellerservice.login(name,password);
    }

    @PostMapping("SellerRegister")
    @ResponseBody
    public BigInteger register(Seller seller, String password){
        return sellerservice.register(seller,password);
    }

    @GetMapping("updateSellerInfo")
    @ResponseBody
    public int updateSellerInfo(Seller seller){
        return sellerservice.updateSellerInfo(seller);
    }

    @PostMapping("updateSellerPassword")
    @ResponseBody
    public int updateSellerPassword(BigInteger seller_id,String password){
        return sellerservice.updateSellerPassword(seller_id, password);
    }



    /**
     * DeliverService
     * */
    @GetMapping("findDeliverById")
    @ResponseBody
    public Deliver findDeliverById(BigInteger order_id){
        return deliverService.findDeliverById(order_id);
    }

    @GetMapping("insertExpress")
    @ResponseBody
    public String insertExpress(BigInteger order_id,String express){
        return deliverService.insertExpress(order_id, express);
    }

    @GetMapping("updateExpress")
    @ResponseBody
    public String updateExpress(@RequestParam("order_id") BigInteger order_id, @RequestParam("express")String express){
        return deliverService.updateExpress(order_id,express);
    }

    @GetMapping("deleteExpress")
    @ResponseBody
    public String deleteExpress(BigInteger order_id){
        return deliverService.deleteExpress(order_id);
    }



}
