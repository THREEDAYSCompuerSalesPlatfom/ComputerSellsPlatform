package com.threeDays.service;

import com.threeDays.POJO.BigGoods;
import com.threeDays.POJO.Cart;
import com.threeDays.dao.BigGoodsMapper;
import com.threeDays.dao.LittleGoodsMapper;
import com.threeDays.dao.SellerMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassNameBigGoodsService
 * @Date2019-12-1717:05
 **/
@Service
public class BigGoodsService {
    @Autowired
    private BigGoodsMapper bigGoodsMapper;
    @Autowired
    private SellerMapper sellerMapper;
    @Autowired
    private LittleGoodsMapper littleGoodsMapper;
    //购物车数据测试
    public List<BigGoods> getAllCart() {
        return bigGoodsMapper.findAll();
    }
    //创建新的商品
    public void createNewBigGoods(String goodsName, BigInteger sellerId) {
        if (goodsName == null) {
            System.out.println("输入goodsName");
        } else if (sellerMapper.findSellerById(sellerId) == null) {
            System.out.println("无此商家");
        } else {
            bigGoodsMapper.saveBigGoods(goodsName, sellerId);
        }
    }

    //删除一类商品
    public void deleteBigGoods(BigInteger bigGoodsId, BigInteger sellerId) {
        if (bigGoodsId == null) {
            System.out.println("输入bigGoodsId");
        } else if (sellerMapper.findSellerById(sellerId) == null) {
            System.out.println("无此商家");
        } else {
            bigGoodsMapper.deleteBigGoods(bigGoodsId);
            littleGoodsMapper.deleteBigGoods(bigGoodsId);
        }
    }

    //更新牟商品名称
    public void updateBigGoods(BigInteger bigGoodsId, BigInteger sellerId, String goodsName) {
        bigGoodsMapper.updateNewBigGoods(bigGoodsId, goodsName);
    }

    //寻找某类商品
    public List<BigGoods> getBigGoodsName(String goodsName) {
        List<BigGoods> bigGoods = bigGoodsMapper.findBigGoodsByName(goodsName);
        return bigGoods;
    }

    //借助商品名称寻找店家ID
    public List<BigInteger> getSellerId(String goodsName) {
        List<BigInteger> sellerId = bigGoodsMapper.getSellerId(goodsName);
        return sellerId;
    }

    //获取所有商品名称->用于查询
    public List<String> getAllGoodsName() {
        List<BigGoods> allGoods = bigGoodsMapper.findAll();
        List<String> goodsName = new ArrayList<>();
        for (BigGoods bigGoods : allGoods) {
            goodsName.add(bigGoods.getGoodsName());
        }
        return goodsName;
    }
    //获取某件商品名称->图片
    public String getGoodsNameById(BigInteger bigGoodsId){
        BigGoods bigGoods=bigGoodsMapper.findBigGoodsById(bigGoodsId);
        return bigGoods.getGoodsName();
    }
    //获取商品Id通过名称
    public List<BigInteger>getBigGoodsId( String goodsName,  BigInteger sellerId){
        List<BigInteger>biggoodsId=bigGoodsMapper.getBigGoodsId(goodsName,sellerId);
        return biggoodsId;
    }
    //获取商品
    public BigGoods getBigGoods(BigInteger bigGoodsId){
        return bigGoodsMapper.getBigGoods(bigGoodsId);
    }

}
