package com.threeDays.dao;

import com.threeDays.POJO.Order;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

public interface OrderMapper {
    Order findOrderById(BigInteger order_id);//通过id查找order
    int insertOrder(Order order);//插入新的order，order_id可以不用手动初始化,返回order_id
    int updateOrder(Order order);//更新Order，order参数中必须手动初始化order_id，成功1，失败0
    int insertComment(@Param("order_id") BigInteger order_id, @Param("comment") String comment);//插入买家评价，成功1，失败0
    int insertReply(@Param("order_id")BigInteger order_id,@Param("reply")String reply);//插入卖家回复，成功1，失败0
    int deleteOrder(BigInteger order_id);//删除订单，成功1，失败0
    int changeStatus(@Param("order_id") BigInteger order_id,@Param("order_status") int order_status);//变更订单状态
    int updatePrize(@Param("order_id")BigInteger order_id,@Param("prize")float prize);
    List<Order> findOrderByStatusANDSeller(int order_status,BigInteger seller_id);
}
