package com.threeDays.Utils.Delivery;
/**
 * 查快递
 *
 * */
public class queryDelivery {
    public static String query(String com, String num){//快递公司编码//快递单号
        String key = "WyUCYopu9516";				//贵司的授权key
        String customer = "E5835B19D23E75BA51FA919DE49A7C0C";			//贵司的查询公司编号
        String phone = "";				//手机号码后四位
        String from = "";				//出发地
        String to = "";					//目的地
        int resultv2 = 0;				//开启行政规划解析

        SynQueryDemo demo = new SynQueryDemo(key, customer);
        String result = demo.synQueryData(com, num, phone, from, to, resultv2);
        return result;
    }
}
