package com.threeDays.service;

import com.threeDays.POJO.BigGoods;
import com.threeDays.POJO.Cart;
import com.threeDays.POJO.LittleGoods;
import com.threeDays.dao.BigGoodsMapper;
import com.threeDays.dao.CartMapper;
import com.threeDays.dao.CustomerMapper;
import com.threeDays.dao.LittleGoodsMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

/**
 * @ClassNameCartService
 * @Date2019-12-1717:02
 **/
@Service
public class CartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private LittleGoodsMapper littleGoodsMapper;
    @Autowired
    private BigGoodsMapper bigGoodsMapper;

    //购物车数据测试
    public List<Cart> getAllCart(BigInteger customerId) {
        return cartMapper.findAll(customerId);
    }

    //商品添加littleGoodsNum个
    public void addNewLittleGoods(BigInteger customerId,
                                  BigInteger littleGoodsId,
                                  int littleGoodsNum) {
        if (customerId == null) {
            System.out.println("请输入customerId");
        } else if (littleGoodsId == null) {
            System.out.println("请输入littleGoodsId");
        } else if (littleGoodsNum == 0) {
            System.out.println("请输入数量");
        } else if (customerMapper.getCustomer(customerId) == null) {
            System.out.println("买家不存在");
        } else if (littleGoodsMapper.findLittleGoodsById(littleGoodsId) == null) {
            System.out.println("商品不存在");
        } else if (cartMapper.selectLittleGoods(customerId, littleGoodsId) == null) {//商品原本不存在
            cartMapper.addLittleGoods(customerId, littleGoodsId, littleGoodsNum, 0);

        } else {
            int num = cartMapper.selectLittleGoodsNum(customerId, littleGoodsId);//商品存在
            littleGoodsNum = num + littleGoodsNum;
            cartMapper.updateLittleGoodsNum(customerId, littleGoodsId, littleGoodsNum);

        }
    }

    //修改状态
    public void updateStatus(BigInteger customerId, BigInteger littleGoodsId, int goodsStatus) {
        cartMapper.updateStatus(customerId, littleGoodsId, goodsStatus);
    }
    //检查商品是否存在在购物车中
    public int check(List<BigInteger> littleGoodsId) {
        for (BigInteger i : littleGoodsId) {
            if (littleGoodsMapper.findLittleGoodsById(i) == null) {
                return 0;
            }
        }
        return 1;
    }
    //商品验证删除
    public void deleteLittleGoods(BigInteger customerId,
                                  List<BigInteger> littleGoodsId) {
        if (customerId == null) {
            System.out.println("请输入customerId");
        } else if (littleGoodsId == null) {
            System.out.println("请输入littleGoodsId");
        } else if (customerMapper.getCustomer(customerId) == null) {
            System.out.println("买家ID不存在");
        } else if (check(littleGoodsId)==0) {
            System.out.println("具体商品ID不存在");
        } else {
            System.out.println("删除成功");
            for(BigInteger i : littleGoodsId){
                cartMapper.deleteLittleGoods(customerId, i);
            }
        }
    }

    //商品数量减一,若仅一减一后删除商品
    public void removeLittleGoods(BigInteger customerId,
                                  BigInteger littleGoodsId) {
        if (customerId == null) {
            System.out.println("请输入customerId");
        } else if (littleGoodsId == null) {
            System.out.println("请输入littleGoodsId");
        } else if (cartMapper.selectLittleGoods(customerId, littleGoodsId) == null) {
            System.out.println("该商品不存在");
        } else if (cartMapper.selectLittleGoodsNum(customerId, littleGoodsId) == 1) {
            cartMapper.deleteLittleGoods(customerId, littleGoodsId);
        } else {
            int littleGoodsNum = cartMapper.selectLittleGoodsNum(customerId, littleGoodsId) - 1;
            cartMapper.removeLittleGoods(customerId, littleGoodsId, littleGoodsNum);
        }
    }

    //修改具体商品
    public void changeEdition(BigInteger customerId, BigInteger oldLittleGoodsId, BigInteger newLittleGoodsId) {
        if (customerId == null) {
            System.out.println("请输入customerId");
        } else if (oldLittleGoodsId == null) {
            System.out.println("请输入oldLittleGoodsId");
        } else if (cartMapper.selectLittleGoods(customerId, oldLittleGoodsId) == null) {
            System.out.println("该商品不存在");
        } else if (cartMapper.selectLittleGoodsNum(customerId, oldLittleGoodsId) == 1) {
            System.out.println("一件商品属性直接更新");
            BigInteger cartId = cartMapper.selectCartId(customerId, oldLittleGoodsId);
            cartMapper.updateLittleGoodsId(newLittleGoodsId, cartId);
        } else {
            System.out.println("原商品少一，新商品加一");
            removeLittleGoods(customerId, oldLittleGoodsId);
            addNewLittleGoods(customerId, newLittleGoodsId, 1);
        }
    }
   public int getLittleGoodsNum(BigInteger customerId,
                             BigInteger littleGoodsId){
        return cartMapper.selectLittleGoodsNum(customerId,littleGoodsId);
   }


    //获取商品名称列表
    public List<String> getAllBigGoodsName(BigInteger customerId) {
        List<LittleGoods> LittleGood = cartMapper.getAllLittleGoods(customerId);
        List<String> goodsName = new ArrayList<>();
        for (LittleGoods i : LittleGood) {
            goodsName.add(bigGoodsMapper.findBigGoodsById(i.getBigGoodsId()).getGoodsName());
        }
        return goodsName;
    }

    //获取商家列表内对应商品
    public List<LittleGoods> getSellerLittleGoods(BigInteger customerId) {
        /*Map<BigInteger, List<LittleGoods>> sellerLittleGoods = new HashMap<>();*/
        //获取所有商品
        List<LittleGoods> littleGoods = cartMapper.getAllLittleGoods(customerId);
        //获取所有商家Id
       /* List<BigInteger> sellerId = new ArrayList<>();
        //商家对应的商品Id
        List<LittleGoods> littleGoodsBySeller=new ArrayList<>();
        for (LittleGoods littleGoods1 : littleGoods) {
            sellerId.add(littleGoods1.getSellerId());
        }
        for (BigInteger id : sellerId) {
            for(LittleGoods littleGoods1:littleGoods){
                if(littleGoods1.getSellerId()==id){
                    littleGoodsBySeller.add(littleGoods1);
                }
            }
            sellerLittleGoods.put(id,littleGoodsBySeller);
            littleGoodsBySeller.clear();
        }*/
        return littleGoods;
    }

}
