package com.threeDays.service;

import com.threeDays.POJO.BigGoods;
import com.threeDays.POJO.Goods;
import com.threeDays.POJO.LittleGoods;
import com.threeDays.dao.BigGoodsMapper;
import com.threeDays.dao.LittleGoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;

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
    public Goods getGoods(BigInteger bigGoodsId){
        Goods goods=new Goods();
        BigGoods bigGoods=bigGoodsMapper.findBigGoodsById(bigGoodsId);
        List<LittleGoods> littleGoods= littleGoodsMapper.LittleGoods(bigGoodsId);
        goods.setBigGoodsId(bigGoodsId);
        goods.setLittleGoodsList(littleGoods);
        goods.setMinPrice(littleGoodsMapper.minPrice(bigGoodsId,bigGoods.getSellerId()));
        goods.setMaxPrice(littleGoodsMapper.maxPrice(bigGoodsId,bigGoods.getSellerId()));
        goods.setName(bigGoods.getGoodsName());
        return goods;
    }
    //生成n个随机数
    /*public  List<BigGoods> randomSet(int min, int max, int n, List<BigGoods> bigGoodsList, HashSet<BigInteger> set) {
        if (n > (max - min + 1) || max < min) {
            return null;
        }
        for (int i = 0; i < n; i++) {
            // 调用Math.random()方法
            int num = (int) (Math.random() * (max - min)) + min;
            set.add(BigInteger.valueOf(num));// 将不同的数存入HashSet中
            bigGoodsList.add(bigGoodsMapper.findBigGoodsById(BigInteger.valueOf(num)));
        }
        int setSize = set.size();
        // 如果存入的数小于指定生成的个数，则调用递归再生成剩余个数的随机数，如此循环，直到达到指定大小
        if (setSize < n) {
            randomSet(min, max, n - setSize,bigGoodsList,set);// 递归
        }else
            return bigGoodsList;
    }*/

}
