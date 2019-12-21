package com.threeDays.dao;

import com.threeDays.POJO.Deliver;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;

public interface DeliverMapper {

    Deliver findDeliverByOrderId(BigInteger order_id);//根据order_id返回deliver对象
    int insertExpress(@Param("order_id") BigInteger order_id, @Param("express") String express, @Param("seller_id") BigInteger seller_id);//根据订单号加入物流单号,1成功，0失败
    int updateExpress(@Param("order_id")BigInteger order_id,@Param("express")String express);//根据订单号修改物流单号,1成功，0失败
    int deleteExpress(BigInteger order_id);//根据订单号删除delive,1成功，0失败
}
