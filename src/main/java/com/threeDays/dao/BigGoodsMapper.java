package com.threeDays.dao;

import com.threeDays.POJO.BigGoods;
import org.apache.ibatis.annotations.Param;


import java.math.BigInteger;
import java.util.List;

public interface BigGoodsMapper {
    //test
    List<BigGoods> findAll();

    //增加一类商品
    int saveBigGoods(@Param("goodsName") String goodsName,
                     @Param("sellerId") BigInteger sellerId);

    //删除某类商品
    int deleteBigGoods(@Param("bigGoodsId") BigInteger bigGoodsId);

    //更新某类商品名称
    int updateNewBigGoods(@Param("bigGoodsId") BigInteger bigGoodsId,
                         @Param("goodsName") String goodsName,
                         @Param("sellerId") BigInteger sellerId);

    //寻找某类商品（按ID）
    BigGoods findBigGoodsById(@Param("bigGoodsId") BigInteger bigGoodsId);

    //寻找某类商品（按名字）
    List <BigGoods> findBigGoodsByName(@Param("goodsName") String goodsName);

    //获取某件商品Id
    BigInteger getBigGoodsId(@Param("goodsName") String goodsName,
                             @Param("sellerId") BigInteger sellerId);

    //获取商品商家Id
    BigInteger getSellerId(@Param("bigGoodsId") BigInteger bigGoodsId,
                           @Param("goodsName") String goodsName);

}
