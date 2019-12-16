package com.threeDays.POJO;

import java.math.BigInteger;

/**
 * @ClassNameBiggoods
 * @Date2019-12-1320:39
 **/
public class BigGoods {
    private BigInteger bigGoodsId;//汇总商品Id
    private String goodsName;//商品名称

    private BigInteger sellerId;//卖家Id
    public BigInteger getBigGoodsId() {
        return bigGoodsId;
    }

    public void setBigGoodsId(BigInteger bigGoodsId) {
        this.bigGoodsId = bigGoodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }



    public BigInteger getSellerId() {
        return sellerId;
    }

    public void setSellerId(BigInteger sellerId) {
        this.sellerId = sellerId;
    }


}
