package com.threeDays.dao;

import com.threeDays.POJO.Cart;
import com.threeDays.POJO.LittleGoods;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * @ClassNamecart
 * @Date2019-12-1320:50
 **/
public interface CartMapper {
    //test
    List<Cart> findAll();

    //增加一件新的详细商品
    int addLittleGoods(@Param("customerId") BigInteger customerId,
                       @Param("littleGoodsId") BigInteger littleGoodsId,
                       @Param("littleGoodsNum") int littleGoodsNum);

    //删除一群相同的详细商品
    int deleteLittleGoods(@Param("customerId") BigInteger customerId,
                          @Param("littleGoodsId") BigInteger littleGoodsId);

    //删除一件详细商品(num>=1)
    int removeLittleGoods(@Param("customerId") BigInteger customerId,
                          @Param("littleGoodsId") BigInteger littleGoodsId,
                          @Param("littleGoodsNum") int littleGoodsNum);

    //修改一件商品属性
    int updateLittleGoodsEdition(@Param("customerId") BigInteger customerId,
                                 @Param("littleGoodsId") BigInteger littleGoodsId,
                                 @Param("littleGoodsNum") int littleGoodsNum);//oldidnum-1 newidnum=1

    //修改商品数量
    int updateLittleGoodsNum(@Param("customerId") BigInteger customerId,
                             @Param("littleGoodsId") BigInteger littleGoodsId,
                             @Param("littleGoodsNum") int littleGoodsNum);

    //获取某件商品数量
    int selectLittleGoodsNum(@Param("customerId") BigInteger customerId,
                             @Param("littleGoodsId") BigInteger littleGoodsId);

    //查询某详细商品
    LittleGoods selectLittleGoods(@Param("customerId") BigInteger customerId,
                                  @Param("littleGoodsId") BigInteger littleGoodsId);
    //查询商品所有Id
    List<BigInteger> getAllLittleGoodsId(@Param("customerId") BigInteger customerId);

}
