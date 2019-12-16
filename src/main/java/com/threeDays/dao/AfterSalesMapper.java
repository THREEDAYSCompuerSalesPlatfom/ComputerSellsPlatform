package com.threeDays.dao;

import com.threeDays.POJO.AfterSales;
import com.threeDays.POJO.Order;

import java.math.BigInteger;

public interface AfterSalesMapper {
    AfterSales findAfterSalesById(BigInteger after_id);
    int insertAfterSales(AfterSales afterSales);//插入新的order，order_id需初始化,返回after_id
    int updateExpress(BigInteger order_id,String express);//更新物流单号，成功1，失败0
    int changeAfterSalesStatus(BigInteger order_id,int status);//改变售后状态，成功1，失败0

}
