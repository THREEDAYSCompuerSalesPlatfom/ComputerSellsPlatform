package com.threeDays.POJO;

import java.math.BigInteger;
import java.util.Date;

public class Order {//订单
    private BigInteger order_id;//订单id
    // private BigInteger littlegoods_id;//详细商品id
    private BigInteger cu_id;//客户id
    private int order_status;//订单状态
    private String comments;//客户的评价
    private String reply;//商家回复
    private Date date;
    private float prize;

    public float getPrize() {
        return prize;
    }

    public void setPrize(float prize) {
        this.prize = prize;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigInteger getOrder_id() {
        return order_id;
    }

    public void setOrder_id(BigInteger order_id) {
        this.order_id = order_id;
    }


    public BigInteger getCu_id() {
        return cu_id;
    }

    public void setCu_id(BigInteger cu_id) {
        this.cu_id = cu_id;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }


}
