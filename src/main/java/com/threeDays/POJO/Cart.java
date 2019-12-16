package com.threeDays.POJO;

import java.math.BigInteger;

/**
 * @ClassNamecart
 * @Date2019-12-1320:40
 **/
public class Cart {
    private BigInteger Id;//（主键）购物车Id
    private BigInteger customerId;//买家Id
    private BigInteger littleGoodsId;//具体商品Id
    private int littleGoodsNum;//商品数量
    private int goodsStatus;//状态

    public BigInteger getId() {
        return Id;
    }

    public void setId(BigInteger Id) {
        this.Id = Id;
    }

    public BigInteger getCustomerId() {
        return customerId;
    }

    public void setCustomerId(BigInteger customerId) {
        this.customerId = customerId;
    }

    public BigInteger getLittleGoodsId() {
        return littleGoodsId;
    }

    public void setLittleGoodsId(BigInteger littleGoodsId) {
        this.littleGoodsId = littleGoodsId;
    }

    public int getLittleGoodsNum() {
        return littleGoodsNum;
    }

    public void setLittleGoodsNum(int littleGoodsNum) {
        this.littleGoodsNum = littleGoodsNum;
    }

    public int getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(int goodsStatus) {
        this.goodsStatus = goodsStatus;
    }
}
