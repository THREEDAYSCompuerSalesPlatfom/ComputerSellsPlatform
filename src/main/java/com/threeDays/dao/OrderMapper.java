package com.threeDays.dao;

import com.threeDays.POJO.Order;

import java.math.BigInteger;

public interface OrderMapper {
    Order findOrderById(BigInteger order_id);//通过id查找order
    BigInteger insertOrder(Order order);//插入新的order，order_id可以不用手动初始化,返回order_id
    int updateOrder(Order order);//更新Order，order参数中必须手动初始化order_id，成功1，失败0
    int insertComment(BigInteger order_id,String commment);//插入买家评价，成功1，失败0
    int insertReply(BigInteger order_id,String reply);//插入卖家回复，成功1，失败0
    int deleteOrder(BigInteger order_id);//删除订单，成功1，失败0
    int changeStatus(BigInteger order_id,int order_status);//变更订单状态

}
