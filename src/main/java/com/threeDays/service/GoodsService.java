package com.threeDays.service;

import com.threeDays.POJO.BigGoods;
import com.threeDays.POJO.Goods;
import com.threeDays.POJO.LittleGoods;
import com.threeDays.dao.BigGoodsMapper;
import com.threeDays.dao.LittleGoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 * @ClassNameGoodsService
 * @Date2019-12-250:16
 **/
@Service
public class GoodsService {
    @Autowired
    BigGoodsMapper bigGoodsMapper;
    @Autowired
    LittleGoodsMapper littleGoodsMapper;
    @Autowired
    BigGoodsService bigGoodsService;
    @Autowired
    LittleGoodsService littleGoodsService;
    @Autowired
    uploadservice uploadservice;

    public Goods getGoods(BigInteger bigGoodsId) {
        Goods goods = new Goods();
        BigGoods bigGoods = bigGoodsMapper.findBigGoodsById(bigGoodsId);
        goods.setBrand(bigGoodsMapper.getBigGoods(bigGoodsId).getBrand());
        List<LittleGoods> littleGoods = littleGoodsMapper.LittleGoods(bigGoodsId);
        goods.setBigGoodsId(bigGoodsId);
        goods.setLittleGoodsList(littleGoods);
        Float minPrice = littleGoodsMapper.minPrice(bigGoodsId, bigGoods.getSellerId());
        if (minPrice == null)
            minPrice = 0f;
        goods.setMinPrice(minPrice);
        Float maxPrice = littleGoodsMapper.maxPrice(bigGoodsId, bigGoods.getSellerId());
        if (maxPrice == null)
            maxPrice = 0f;
        goods.setMaxPrice(maxPrice);
        goods.setName(bigGoods.getGoodsName());
        return goods;
    }

    public List<Goods> getgoodsbysellerid(BigInteger sellerid) {
        List<BigGoods> bigGoods = bigGoodsMapper.getBigGoodsBySellerId(sellerid);
        List<Goods> goodsList = new ArrayList<>();
        for (BigGoods bigGoods1 : bigGoods) {
            goodsList.add(getGoods(bigGoods1.getBigGoodsId()));
        }
        return goodsList;

    }

    //生成n个随机数
    private void randomSet(int min, int max, int n, List<BigGoods> bigGoodsList, HashSet<BigInteger> set) {
        for (int i = 0; i < n; i++) {
            // 调用Math.random()方法
            int num = (int) (Math.random() * (max - min)) + min;
            if (bigGoodsMapper.findBigGoodsById(BigInteger.valueOf(num)) != null) {
                set.add(BigInteger.valueOf(num));// 将不同的数存入HashSet中
                bigGoodsList.add(bigGoodsMapper.findBigGoodsById(BigInteger.valueOf(num)));
            }
        }
        int setSize = set.size();
        // 如果存入的数小于指定生成的个数，则调用递归再生成剩余个数的随机数，如此循环，直到达到指定大小
        if (setSize < n) {
            randomSet(min, max, n - setSize, bigGoodsList, set);// 递归
        }
    }
    public void randomArray(int min ,int max,int n,List<BigGoods>bigGoodsList){
        for(int i=0;i<n;i++){
            int num = new Random().nextInt(max-min+1)+min;
            System.out.println("min"+min+"max"+ min+"num"+num);
            if (bigGoodsMapper.findBigGoodsById(BigInteger.valueOf(num)) != null) {
                bigGoodsList.add(bigGoodsMapper.findBigGoodsById(BigInteger.valueOf(num)));
            }
        }
    }

    public List<Goods> getGoodsList(List<Goods> goods) {
        HashSet<BigInteger> set = new HashSet<>();
        List<BigGoods> bigGoodsList = new ArrayList<>();
        int num=bigGoodsMapper.getNum();
        int min=0;
        int max=0;
        if (bigGoodsMapper.getMinId() != null) {
            min = bigGoodsMapper.getMinId().intValue();
            System.out.println("bigIntegerMin"+bigGoodsMapper.getMinId());
        }
        if (bigGoodsMapper.getMaxId() != null) {
            max = bigGoodsMapper.getMaxId().intValue();
        }if(num>7){
            randomSet(min, max, 8, bigGoodsList, set);
        }else{
            randomArray(min, max, 8, bigGoodsList);
        }
        for (BigGoods bigGoods : bigGoodsList) {
            goods.add(getGoods(bigGoods.getBigGoodsId()));
        }
        return goods;
    }

    public BigInteger newgoods(BigInteger biggoodsid, String name, String brand, String edtion, float prize, BigInteger seller_id) {
        if (biggoodsid != null && bigGoodsService.getBigGoods(biggoodsid) != null) {//大商品存在
            littleGoodsService.addNewLittleGoods(biggoodsid, edtion, seller_id, prize);
            return new BigInteger("1");
        } else {//大商品不存在
            BigInteger newbiggoodsid = bigGoodsService.createNewBigGoods(name, seller_id, brand);

            littleGoodsService.addNewLittleGoods(newbiggoodsid, edtion, seller_id, prize);

            return  newbiggoodsid;

        }
    }
}
