package com.threeDays.POJO;

import java.math.BigInteger;

public class FinalDeliver {
    private BigInteger orderid;
    private String name;
    private String edtion;
    private int num;
    private String cuname;
    private String cutel;
    private String cuaddress;
    private String commemts;
    private String reply;

    public String getCommemts() {
        return commemts;
    }

    public void setCommemts(String commemts) {
        this.commemts = commemts;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public BigInteger getOrderid() {
        return orderid;
    }

    public void setOrderid(BigInteger orderid) {
        this.orderid = orderid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEdtion() {
        return edtion;
    }

    public void setEdtion(String edtion) {
        this.edtion = edtion;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCuname() {
        return cuname;
    }

    public void setCuname(String cuname) {
        this.cuname = cuname;
    }

    public String getCutel() {
        return cutel;
    }

    public void setCutel(String cutel) {
        this.cutel = cutel;
    }

    public String getCuaddress() {
        return cuaddress;
    }

    public void setCuaddress(String cuaddress) {
        this.cuaddress = cuaddress;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    private String express;
}
