package com.threeDays.POJO;

import java.math.BigInteger;

/**
 * @ClassNamecart
 * @Date2019-12-1320:40
 **/
public class Cart {
    private BigInteger cartId;//购物车Id
    private BigInteger customerId;//买家Id
    private BigInteger littleGoodsId;//具体商品Id
    int number;//商品数量
    int goodsStatus;//状态

    public BigInteger getCustomerId() {
        return customerId;
    }

    public void setCustomerId(BigInteger customerId) {
        this.customerId = customerId;
    }




    public BigInteger getCartId() {
        return cartId;
    }

    public void setCartId(BigInteger cartId) {
        this.cartId = cartId;
    }

    public BigInteger getLittleGoodsId() {
        return littleGoodsId;
    }

    public void setLittleGoodsId(BigInteger littleGoodsId) {
        this.littleGoodsId = littleGoodsId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public int getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(int goodsStatus) {
        this.goodsStatus = goodsStatus;
    }


}
