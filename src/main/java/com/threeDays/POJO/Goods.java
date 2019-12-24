package com.threeDays.POJO;

import java.math.BigInteger;
import java.util.List;

/**
 * @ClassNameGoods
 * @Date2019-12-250:13
 **/
public class Goods {
    private BigInteger bigGoodsId;
    private String name;
    private float minPrice;
    private float maxPrice;
    List<LittleGoods> littleGoodsList;

    public BigInteger getBigGoodsId() {
        return bigGoodsId;
    }

    public void setBigGoodsId(BigInteger bigGoodsId) {
        this.bigGoodsId = bigGoodsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(float minPrice) {
        this.minPrice = minPrice;
    }

    public float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(float maxPrice) {
        this.maxPrice = maxPrice;
    }

    public List<LittleGoods> getLittleGoodsList() {
        return littleGoodsList;
    }

    public void setLittleGoodsList(List<LittleGoods> littleGoodsList) {
        this.littleGoodsList = littleGoodsList;
    }
}
