package com.threeDays.POJO;

import java.math.BigInteger;

public class Order {//订单
    private BigInteger order_id;
    private BigInteger littlegoods_id;//详细商品id
    private BigInteger cu_id;
    private int order_status;
    private String comments;

    public BigInteger getOrder_id() {
        return order_id;
    }

    public void setOrder_id(BigInteger order_id) {
        this.order_id = order_id;
    }

    public BigInteger getLittlegoods_id() {
        return littlegoods_id;
    }

    public void setLittlegoods_id(BigInteger littlegoods_id) {
        this.littlegoods_id = littlegoods_id;
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

    private String reply;
}
