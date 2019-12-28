package com.threeDays.service;

import com.threeDays.POJO.*;
import com.threeDays.dao.CustomerMapper;
import com.threeDays.dao.DeliverMapper;
import com.threeDays.dao.OrdergoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class finalOrderService {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrdergoodsMapper ordergoodsMapper;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private DeliverService deliverService;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private LittleGoodsService littleGoodsService;
    @Autowired
    private AfterSalesService afterSalesService;
    @Autowired
    private BigGoodsService bigGoodsService;

    public List<FinalDeliver> findfinalDeliver(BigInteger orderid) {
        Order order = orderService.findOrderById(orderid);
        BigInteger cuid = order.getCu_id();
        Customer customer = customerMapper.getCustomer(cuid);
        Deliver deliver = deliverService.findDeliverById(orderid);

        Ordergoods[] ordergoods = ordergoodsMapper.findOrderGoodsByOrderId(orderid);
        List<FinalDeliver> finalDelivers = new ArrayList<>();
        for (Ordergoods ordergoods1 : ordergoods) {
            FinalDeliver finalDeliver = new FinalDeliver();
            finalDeliver.setOrderid(orderid);
            finalDeliver.setName(bigGoodsService.getGoodsNameById(littleGoodsService.findLittleGoodsById(ordergoods1.getLittlegoods_id()).getBigGoodsId()));
            finalDeliver.setCuaddress(customer.getCu_Address());
            finalDeliver.setCuname(customer.getCu_Name());
            finalDeliver.setCutel(customer.getCu_Telephone());
            System.out.println(deliver.getDeliver_id());
            finalDeliver.setExpress(deliver.getExpress()==null?"无":deliver.getExpress());
            finalDeliver.setCommemts(order.getComments());
            finalDeliver.setReply(order.getReply());
            finalDeliver.setEdtion(littleGoodsService.findLittleGoodsById(ordergoods1.getLittlegoods_id()).getEdition());
            finalDeliver.setNum(ordergoods1.getNumber());
            finalDeliver.setAfterSales(afterSalesService.findAftersalesByOrderid(orderid));
            finalDeliver.setOrderstatus(order.getOrder_status());
            finalDelivers.add(finalDeliver);
        }

        return finalDelivers;
    }

    public List<FinalDeliver> getfinaldeliverbySeler(BigInteger sellerid, int status) {
        List<Order> orders = orderService.findOrderByStatusANDSeller(status, sellerid);
        List<FinalDeliver> finalDelivers=new ArrayList<>();
        for (Order order : orders) {
            BigInteger orderid = order.getOrder_id();
            List<FinalDeliver> finalDelivers1=findfinalDeliver(orderid);
            for(FinalDeliver finalDeliver:finalDelivers1){
                finalDelivers.add(finalDeliver);
            }
        }
        return finalDelivers;
    }
}
