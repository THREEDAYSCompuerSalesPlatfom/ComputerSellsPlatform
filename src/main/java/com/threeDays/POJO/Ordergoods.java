package com.threeDays.POJO;

import java.math.BigInteger;

public class Ordergoods {
    private BigInteger ordergoods_id;
    private  BigInteger order_id;
    private BigInteger littlegoods_id;
    private int number;

    public Ordergoods(){};

    public Ordergoods(BigInteger order_id, BigInteger littlegoods_id, int number) {
        this.order_id = order_id;
        this.littlegoods_id = littlegoods_id;
        this.number = number;
    }

    public BigInteger getOrdergoods_id() {
        return ordergoods_id;
    }

    public void setOrdergoods_id(BigInteger ordergoods_id) {
        this.ordergoods_id = ordergoods_id;
    }

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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
