package com.threeDays.dao;

import com.threeDays.POJO.Ordergoods;

import java.math.BigInteger;
import java.util.List;

public interface OrdergoodsMapper {
    Ordergoods findOrdergoodsByOrderGoodsId(BigInteger ordergoods_id);//根据ordergoods_id（主键）返回ordergoods
    Ordergoods[] findOrderGoodsByOrderId(BigInteger order_id);//根据order_id返回订单内所有ordergoods对象
    int insertOrderGoods(Ordergoods ordergoods);//增加订单时调用
    int deleteOrderGoods(BigInteger order_id);//删除订单时调用
}
