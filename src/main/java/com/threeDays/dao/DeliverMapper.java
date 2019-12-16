package com.threeDays.dao;

import com.threeDays.POJO.Deliver;

import java.math.BigInteger;

public interface DeliverMapper {
    Deliver findDeliverById(BigInteger order_id);//根据order_id返回deliver对象
    int insertExpress(BigInteger order_id,String express,BigInteger seller_id);//根据订单号加入物流单号,1成功，0失败
    int updateExpress(BigInteger order_id,String express);//根据订单号修改物流单号,1成功，0失败
    int deleteExpress(BigInteger order_id);//根据订单号删除delive,1成功，0失败
}
