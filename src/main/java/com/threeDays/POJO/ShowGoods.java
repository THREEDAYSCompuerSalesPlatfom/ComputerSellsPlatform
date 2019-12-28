package com.threeDays.POJO;

import java.math.BigInteger;

/**
 * @ClassNameshowGoods
 * @Date2019-12-2821:46
 **/
public class ShowGoods {
    private  BigInteger order_id;
    private LittleGoods littlegoods;
    private BigGoods bigGoods;
    private int number;
    public ShowGoods(BigInteger order_id,LittleGoods littleGoods,BigGoods bigGoods,int number){
        this.order_id=order_id;
        this.littlegoods=littleGoods;
        this.bigGoods=bigGoods;
        this.number=number;
    }
    public BigInteger getOrder_id() {
        return order_id;
    }

    public void setOrder_id(BigInteger order_id) {
        this.order_id = order_id;
    }

    public LittleGoods getLittlegoods() {
        return littlegoods;
    }

    public void setLittlegoods(LittleGoods littlegoods) {
        this.littlegoods = littlegoods;
    }

    public BigGoods getBigGoods() {
        return bigGoods;
    }

    public void setBigGoods(BigGoods bigGoods) {
        this.bigGoods = bigGoods;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
