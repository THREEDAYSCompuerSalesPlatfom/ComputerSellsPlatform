package com.threeDays.service;

import com.threeDays.POJO.BigGoods;
import com.threeDays.POJO.LittleGoods;
import com.threeDays.dao.BigGoodsMapper;
import com.threeDays.dao.LittleGoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.ListIterator;

@Service("LittleGoodsService")
public class LittleGoodsService {
    @Autowired
    private LittleGoodsMapper littleGoodsMapper;
    @Autowired
    private BigGoodsMapper bigGoodsMapper;
    @Autowired
    private BigGoodsService bigGoodsService;


    //test
    public List<LittleGoods> getAllLittleGoods() {
        return littleGoodsMapper.findAll();
    }

    public BigInteger getLittleGoodsId(String edition,
                                       BigInteger bigGoodsId) {
        BigInteger sellerId=bigGoodsMapper.getSellerIdById(bigGoodsId);
        return littleGoodsMapper.getLittleGoodsId(edition, bigGoodsId, sellerId);
    }

    /**
     * 根据详细商品id返回sellerid
     * 不存在返回null
     */
    public BigInteger findSellerById(BigInteger littlegoods_id) {
        return littleGoodsMapper.findSellerById(littlegoods_id);
    }

    //检查配置是否重复
    public Boolean edition(String edition, BigInteger bigGoodsId) {
        Boolean a = true;
        List<String> littleGoodsEdition = littleGoodsMapper.getEdition(bigGoodsId);
        ListIterator<String> iterator = littleGoodsEdition.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next() != edition) {
                a = true;
            } else
                a = false;
        }
        return a;
    }

    /**
     * 添加新的配置newLittleGoods
     * 成功返回新加入的littlegoodsid
     * 失败返回-1：无此商品请添加商品
     * 失败返回-2：已有此配置
     */
    public BigInteger addNewLittleGoods(BigInteger bigGoodsId, String edition, BigInteger sellerId, float goodsPrice) {
        if (bigGoodsMapper.findBigGoodsById(bigGoodsId) == null) {
            // System.out.println("无此商品请添加商品");
            return new BigInteger("-1");
        } else if (edition(edition, bigGoodsId) == false) {
            //  System.out.println("已有此配置");
            return new BigInteger("-2");
        } else {
            LittleGoods littleGoods = new LittleGoods();
            littleGoods.setBigGoodsId(bigGoodsId);
            littleGoods.setEdition(edition);
            littleGoods.setSellerId(sellerId);
            littleGoods.setGoodsPrice(goodsPrice);
            littleGoodsMapper.addNewLittleGoods(littleGoods);
            return littleGoods.getLittleGoodsId();
        }
    }

    //寻找具体商品列表
    public List<LittleGoods> findLittleGoods(BigInteger bigGoodsId) {
        List<LittleGoods> littleGoods = littleGoodsMapper.LittleGoods(bigGoodsId);
        return littleGoods;
    }

    //删除原有某个配置
    public int deleteLittleGoodsById(BigInteger littleGoodsId) {
        LittleGoods littleGoods=findLittleGoodsById(littleGoodsId);
       List<LittleGoods> littleGoodsList= findLittleGoods(littleGoods.getBigGoodsId());
       if(littleGoodsList.size()!=1){
           return littleGoodsMapper.deleteLittleGoods(littleGoodsId);
       }else {
          BigInteger i= bigGoodsService.deleteBigGoods(littleGoods.getBigGoodsId(),littleGoods.getSellerId());
           if(i.equals("-1")){
               return 0;
           }else {
               return 1;
           }
       }

    }

//    //修改某个配置
//    public void updateLittleGoods(String edition, BigInteger sellerId, BigInteger bigGoodsId) {
//        littleGoodsMapper.updateEdition(edition, sellerId, bigGoodsId);
//    }

    /**
     * 修改某个配置
     * 成功返回1
     * 错误返回0
     */
    public int updateLittleGoods(String oldEdition, String newEdition, BigInteger sellerId, BigInteger bigGoodsId) {
        BigInteger littleGoodsId = littleGoodsMapper.getLittleGoodsId(oldEdition, bigGoodsId, sellerId);
        return littleGoodsMapper.updateEdition(newEdition, littleGoodsId);
    }

    public int updateLittleGoods(String newEdition, BigInteger littleGoodsId) {
        return littleGoodsMapper.updateEdition(newEdition, littleGoodsId);
    }

    //获取某个商品价格范围
    public float minPrice(BigInteger bigGoodsId, BigInteger sellerId) {
        return littleGoodsMapper.minPrice(bigGoodsId, sellerId);
    }

    public float maxPrice(BigInteger bigGoodsId, BigInteger sellerId) {
        return littleGoodsMapper.maxPrice(bigGoodsId, sellerId);
    }

    //修改商品价格
    public int updateLittleGoodsPrice(String edition, float goodsPrice, BigInteger sellerId, BigInteger bigGoodsId) {
        return littleGoodsMapper.updatePrice(edition, goodsPrice, sellerId, bigGoodsId);
    }

    //获取某个具体商品
    public LittleGoods findLittleGoodsById(BigInteger littleGoodsId) {
        return littleGoodsMapper.findLittleGoodsById(littleGoodsId);
    }

    //获取配置列表
    public List<String> getEdition(BigInteger bigGoodsId) {
        return littleGoodsMapper.getEdition(bigGoodsId);
    }

}
