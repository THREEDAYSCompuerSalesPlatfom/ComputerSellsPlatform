package com.threeDays.POJO;

import java.math.BigInteger;

public class CartGoods {
    private BigInteger biggoodsid;
    private BigInteger littlegoodsid;
    private String edtion;
    private String name;
    private String brand;
    private float prize;
    private int num;

    public String getEdtion() {
        return edtion;
    }

    public void setEdtion(String edtion) {
        this.edtion = edtion;
    }

    public BigInteger getBiggoodsid() {
        return biggoodsid;
    }

    public void setBiggoodsid(BigInteger biggoodsid) {
        this.biggoodsid = biggoodsid;
    }

    public BigInteger getLittlegoodsid() {
        return littlegoodsid;
    }

    public void setLittlegoodsid(BigInteger littlegoodsid) {
        this.littlegoodsid = littlegoodsid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public float getPrize() {
        return prize;
    }

    public void setPrize(float prize) {
        this.prize = prize;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
