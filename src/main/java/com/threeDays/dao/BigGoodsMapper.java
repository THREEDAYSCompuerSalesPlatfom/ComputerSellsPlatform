package com.threeDays.dao;

import com.threeDays.POJO.BigGoods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Property;


import java.math.BigInteger;
import java.util.List;

public interface BigGoodsMapper {
    //test
    List<BigGoods> findAll();

    /**增加一类商品,，参数中主键id可以为null*/
    int saveBigGoods(BigGoods bigGoods);

    //删除某类商品
    int deleteBigGoods(@Param("bigGoodsId") BigInteger bigGoodsId);

    //更新某类商品名称
    int updateNewBigGoods(@Param("bigGoodsId") BigInteger bigGoodsId,
                          @Param("goodsName") String goodsName,
                          @Param("brand") String brand);

    //寻找某类商品（按ID）
    BigGoods findBigGoodsById(@Param("bigGoodsId") BigInteger bigGoodsId);

    //寻找某类商品（按名字）
    List<BigGoods> findBigGoodsByName(@Param("goodsName") String goodsName);

    //获取某件商品Id(实际角度为一个，数据库角度可有多个)
    List<BigInteger> getBigGoodsId(@Param("goodsName") String goodsName, @Param("sellerId") BigInteger sellerId);

    //获取商品商家Id
    List<BigInteger> getSellerId(@Param("goodsName") String goodsName);

    //获取商品BigGoods
    BigGoods getBigGoods(@Param("bigGoodsId") BigInteger bigGoodsId);

    //按牌子获取对象列表
    List<BigGoods> getBigGoodsByBrand(@Param("brand") String brand);

    List<BigInteger> getBigGoodsIdByBrand(@Param("brand") String brand);
    //获取商品列表（根据卖家Id）
    List<BigGoods> getBigGoodsBySellerId(@Param("sellerId")BigInteger sellerId);

}
