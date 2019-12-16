package com.threeDays.dao;


import com.threeDays.POJO.LittleGoods;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;


public interface LittleGoodsMapper {
    //test
    List<LittleGoods> findAll();

    //修改配置信息
    int updateEdition();

    //新增具体商品
    int addNewLittleGoods(@Param("bigGoodsId") BigInteger bigGoodsId,
                          @Param("edition") String edition,
                          @Param("sellerId") BigInteger sellerId,
                          @Param("goodsPrice") float goodsPrice);
    //修改价格
    int updatePrice(@Param("littleGoodsId") BigInteger littleGoodsId);

    //获取商品最低价格
    int minPrice(@Param("bigGoodsId") BigInteger bigGoodsId);

    //获取商品最高价格
    int maxPrice(@Param("bigGoodsId") BigInteger bigGoodsId);
     BigInteger findSellerById(@Param("littlegoodId") BigInteger littlegoodsId);
}
