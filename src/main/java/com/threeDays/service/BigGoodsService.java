package com.threeDays.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<BigGoods> getAllGoods() {
        return bigGoodsMapper.findAll();
    }

    /**
     * 创建新的大商品
     * 返回大商品id
     * 返回-1：商品名字没填写
     * 返回-2:没此商家
     * */
    public BigInteger createNewBigGoods(String goodsName, BigInteger sellerId, String brand) {
        if (goodsName == null) {
          //  System.out.println("输入goodsName");
            return new BigInteger("-1");
        } else if (sellerMapper.findSellerById(sellerId) == null) {
           // System.out.println("无此商家");
            return new BigInteger("-2");
        } else {
            BigGoods bigGoods=new BigGoods();
            bigGoods.setGoodsName(goodsName);
            bigGoods.setSellerId(sellerId);
            bigGoods.setBrand(brand);
            bigGoodsMapper.saveBigGoods(bigGoods);
            return bigGoods.getBigGoodsId();
        }
    }

    /**
     * 删除一类商品
     * 成功返回商品id
     * 失败返回-1：商品号非该商家号所有的商品
     * */
    public BigInteger deleteBigGoods(BigInteger bigGoodsId, BigInteger sellerId) {
        if (bigGoodsMapper.getBigGoods(bigGoodsId).getSellerId().equals(sellerId)){
            bigGoodsMapper.deleteBigGoods(bigGoodsId);
            littleGoodsMapper.deleteBigGoods(bigGoodsId);
            return bigGoodsId;
        }else{
            return new BigInteger("-1");
        }
    }

    /**
     * 更新商品名称以及品牌
     * 成功返回商品id
     * 失败返回-1：商品号非该商家号所有的商品
     * */
    public BigInteger updateBigGoods(BigInteger bigGoodsId, BigInteger sellerId, String goodsName, String brand) {
        if(bigGoodsMapper.getBigGoods(bigGoodsId).getSellerId().equals(sellerId)){//验证商品id是否为商家自己的
            bigGoodsMapper.updateNewBigGoods(bigGoodsId, goodsName, brand);
            return bigGoodsId;
        }else{
            return new BigInteger("-1");
        }

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
    public String getGoodsNameById(BigInteger bigGoodsId) {
        BigGoods bigGoods = bigGoodsMapper.findBigGoodsById(bigGoodsId);
        return bigGoods.getGoodsName();
    }

    //获取商品Id通过名称
    public List<BigInteger> getBigGoodsId(String goodsName, BigInteger sellerId) {
        List<BigInteger> biggoodsId = bigGoodsMapper.getBigGoodsId(goodsName, sellerId);
        return biggoodsId;
    }

    //获取商品
    public BigGoods getBigGoods(BigInteger bigGoodsId) {
        return bigGoodsMapper.getBigGoods(bigGoodsId);
    }

    //获取商品列表
    public List<BigGoods> getBigGoodsByBrand(String brand){
        return bigGoodsMapper.getBigGoodsByBrand(brand);
    }
    /**
     * 分页通过商家id获取商品列表
     * 用PageNum控制返回第几页
     * 一页中有pagesize个商品
     * 通过返回的map.get("list")获取到返回的表
     * 通过返回的map.get("Total")获取总页数
     * 通过返回的map.get("PageNum")获取当前页数
     * */
    public Map<String,Object> getPAGEBigGoodsBySellerId(BigInteger seller_id,int PageNum,int pagesize){
        PageHelper.startPage(PageNum, pagesize);
        List<BigGoods> list=bigGoodsMapper.getBigGoodsBySellerId(seller_id);
        PageInfo<BigGoods> pageInfo = new PageInfo(list, pagesize);
        long totalPageNum=pageInfo.getTotal();
        Map<String,Object> map=new HashMap<>();
        map.put("list",list);//表本身
        map.put("Total",totalPageNum);//加入总页数
        map.put("PageNum",pageInfo.getPageNum());//当前页数
        return map;
    }
}
