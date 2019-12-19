package com.threeDays.POJO;

import java.math.BigInteger;

public class AfterSales {
    private BigInteger after_id;//售后id
    private BigInteger seller_id;//卖家id
    private BigInteger order_id;//订单id
    private String express;//物流单号
    private int after_status;//售后状态
    private String cu_excuse;
    private String seller_excuse;

    public String getCu_excuse() {
        return cu_excuse;
    }

    public void setCu_excuse(String cu_excuse) {
        this.cu_excuse = cu_excuse;
    }

    public String getSeller_excuse() {
        return seller_excuse;
    }

    public void setSeller_excuse(String seller_excuse) {
        this.seller_excuse = seller_excuse;
    }


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

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public int getAfter_status() {
        return after_status;
    }

    public void setAfter_status(int after_status) {
        this.after_status = after_status;
    }
}
