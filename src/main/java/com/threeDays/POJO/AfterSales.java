package com.threeDays.POJO;

import java.math.BigInteger;

public class AfterSales {
    private BigInteger after_id;
    private BigInteger seller_id;
    private BigInteger order_id;
    private BigInteger express;
    private int after_status;

    public BigInteger getAfter_id() {
        return after_id;
    }

    public void setAfter_id(BigInteger after_id) {
        this.after_id = after_id;
    }

    public BigInteger getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(BigInteger seller_id) {
        this.seller_id = seller_id;
    }

    public BigInteger getOrder_id() {
        return order_id;
    }

    public void setOrder_id(BigInteger order_id) {
        this.order_id = order_id;
    }

    public BigInteger getExpress() {
        return express;
    }

    public void setExpress(BigInteger express) {
        this.express = express;
    }

    public int getAfter_status() {
        return after_status;
    }

    public void setAfter_status(int after_status) {
        this.after_status = after_status;
    }
}
