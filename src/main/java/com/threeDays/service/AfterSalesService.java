package com.threeDays.service;

import com.threeDays.POJO.AfterSales;
import com.threeDays.dao.AfterSalesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class AfterSalesService {
    @Autowired
    private AfterSalesMapper afterSalesMapper;
    @Autowired
    private OrderService orderService;

    /**
     * 根据售后id返回售后对象
     * 没有返回null
     */
    public AfterSales findAfterSalesById(BigInteger aftersales_id) {
        return afterSalesMapper.findAfterSalesById(aftersales_id);
    }

    /**
     * 插入新的order，order_id可以不用手动初始化,返回新的after_id
     * 返回-1：对象中orderid未初始化
     * 返回-2：数据库中不存在此订单id
     */
    public BigInteger insertAfterSales(AfterSales afterSales) {
        if (afterSales.getOrder_id() == null) {
            return new BigInteger("-1");
        }
        if (orderService.findOrderById(afterSales.getOrder_id()) == null) {
            return new BigInteger("-2");
        }
        return afterSalesMapper.insertAfterSales(afterSales);
    }

    /**
     * 更新物流单号，成功1，失败0
     */
    private String updateExpress(BigInteger order_id, String express) {
        if (orderService.findOrderById(order_id) == null) {
            return "不存在此订单";
        }
        if (afterSalesMapper.updateExpress(order_id, express) == 0) {
            return "fail";
        } else {
            return "success";
        }
    }

    /**
     * 更新售后状态，成功1，失败0
     */
    public String changeAfterSalesStatus(BigInteger order_id, int status) {
        if (orderService.findOrderById(order_id) == null) {
            return "不存在此订单";
        }
        if (afterSalesMapper.changeAfterSalesStatus(order_id, status) == 0) {
            return "fail";
        } else {
            return "success";
        }
    }
}
