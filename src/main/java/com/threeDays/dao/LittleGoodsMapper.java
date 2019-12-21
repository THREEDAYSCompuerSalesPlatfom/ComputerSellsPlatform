package com.threeDays.dao;


import com.threeDays.POJO.LittleGoods;
import org.apache.ibatis.annotations.Param;

import javax.websocket.server.PathParam;
import java.math.BigInteger;
import java.util.List;


public interface LittleGoodsMapper {
    //test
    List<LittleGoods> findAll();

    //修改配置信息
    int updateEdition(@Param("edition") String edition,
                      @Param("sellerId") BigInteger sellerId,
                      @Param("littleGoodsId") BigInteger littleGoodsId);

    //修改价格
    int updatePrice(@Param("edition") String edition,
                    @Param("goodsPrice") float goodsPrice,
                    @Param("sellerId") BigInteger sellerId,
                    @Param("bigGoodsId")BigInteger bigGoodsId);

    /**新增具体商品，参数中主键id可以为null*/
    int addNewLittleGoods(LittleGoods littleGoods);

    //删除商品
    int deleteBigGoods(@Param("bigGoodsId") BigInteger bigGoodsId);

    //删除具体商品（配置）
    int deleteLittleGoods(@Param("littleGoodsId") BigInteger littleGoodsId);


    //获取商品最低价格
    float minPrice(@Param("bigGoodsId") BigInteger bigGoodsId,
                 @Param("sellerId") BigInteger sellerId);

    //获取商品最高价格
    float maxPrice(@Param("bigGoodsId") BigInteger bigGoodsId,
                 @Param("sellerId") BigInteger sellerId);

    //获取具体商品列表
    List<LittleGoods> LittleGoods(@Param("bigGoodsId") BigInteger bigGoodsId);

    //获取配置列表
    List<String> getEdition(@Param("bigGoodsId") BigInteger bigGoodsId);

    //获取卖家Id
    BigInteger findSellerById(@Param("littleGoodId") BigInteger littleGoodsId);

    //获取具体商品
    LittleGoods findLittleGoodsById(@Param("littleGoodId") BigInteger littleGoodsId);


}
