package com.threeDays.service;

import com.threeDays.POJO.Deliver;
import com.threeDays.dao.DeliverMapper;
import com.threeDays.dao.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class DeliverService {
    @Autowired
    private DeliverMapper deliverMapper;
    @Autowired
    private OrderService orderService;

    /**
     * 根据order_id返回deliver对象
     */
    public Deliver findDeliverById(BigInteger order_id) {
        return deliverMapper.findDeliverById(order_id);
    }

    /**
     * 插入物流单号
     */
    public String insertExpress(BigInteger order_id, String express) {
        if (orderService.findOrderById(order_id) == null) {
            return "没有此订单";
        }
        if (express.equals("")) {
            return "物流单号不能为空";
        }
        if (deliverMapper.insertExpress(order_id, express) == 0) {
            return "插入失败，数据库错误";
        } else {
            orderService.changeStatus(order_id, 1);//更改订单状态为卖家已经发货
            return "加入成功";
        }
    }

    /**
     * 更新物流单号
     */
    public String updateExpress(BigInteger order_id, String express) {
        if (orderService.findOrderById(order_id) == null) {
            return "没有此订单";
        }
        if (express.equals("")) {
            return "物流单号不能为空";
        }
        if (deliverMapper.updateExpress(order_id, express) == 0) {
            return "插入失败，数据库错误";
        } else {
            return "加入成功";
        }
    }

    /**
     * 删除物流单号
     */
    public String deleteExpress(BigInteger order_id) {
        if (orderService.findOrderById(order_id) == null) {
            return "没有此订单";
        }
        if (deliverMapper.deleteExpress(order_id) == 0) {
            return "删除失败，数据库错误";
        } else {
            return "删除成功";
        }
    }

}
