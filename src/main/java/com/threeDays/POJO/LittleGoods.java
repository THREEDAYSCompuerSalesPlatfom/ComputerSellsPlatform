package com.threeDays.POJO;

import java.math.BigInteger;


public class LittleGoods {
    private BigInteger littleGoodsId;//详细商品id
    private BigInteger bigGoodsId;//汇总商品id
    private String edition;//版本
    private BigInteger sellerId;//卖家id
    private float goodsPrice;//价格

    public float getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(float goodsPrice) {
        this.goodsPrice = goodsPrice;
    }


    public BigInteger getLittleGoodsId() {
        return littleGoodsId;
    }

    public void setLittleGoodsId(BigInteger littleGoodsId) {
        this.littleGoodsId = littleGoodsId;
    }

    public BigInteger getBigGoodsId() {
        return bigGoodsId;
    }

    public void setBigGoodsId(BigInteger bigGoodsId) {
        this.bigGoodsId = bigGoodsId;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public BigInteger getSellerId() {
        return sellerId;
    }

    public void setSellerId(BigInteger sellerId) {
        this.sellerId = sellerId;
    }


}
