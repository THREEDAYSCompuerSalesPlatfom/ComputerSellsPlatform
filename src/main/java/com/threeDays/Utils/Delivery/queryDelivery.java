package com.threeDays.Utils.Delivery;

import com.alibaba.fastjson.JSON;
import com.threeDays.POJO.Deliver;
import com.threeDays.Utils.Delivery.deliverPojo.deliverpojo;
import org.springframework.beans.factory.annotation.Value;

/**
 * 查快递
 *
 * */
public class queryDelivery {

    static String key ="WyUCYopu9516";				//贵司的授权key

    static String customer="E5835B19D23E75BA51FA919DE49A7C0C" ;			//贵司的查询公司编号
    public static String query(String com, String num){//快递公司编码//快递单号


        String phone = "";				//手机号码后四位
        String from = "";				//出发地
        String to = "";					//目的地
        int resultv2 = 0;				//开启行政规划解析

        SynQueryDemo demo = new SynQueryDemo(key, customer);
        String result = demo.synQueryData(com, num, phone, from, to, resultv2);
        return result;
    }

    public static deliverpojo kuaidiniaoquery(String com,String num){
        KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
        try {
            String result = api.getOrderTracesByJson(com, num);
            System.out.print(result);
            deliverpojo deliverpojo=JSON.parseObject(result, com.threeDays.Utils.Delivery.deliverPojo.deliverpojo.class);
            return deliverpojo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
