package com.threeDays.service;

import com.threeDays.POJO.BigGoods;
import com.threeDays.POJO.Cart;
import com.threeDays.POJO.CartGoods;
import com.threeDays.POJO.LittleGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartGoodsService {
    @Autowired
    private BigGoodsService bigGoodsService;
    @Autowired
    private LittleGoodsService littleGoodsService;
    @Autowired
    private CartService cartService;
    public List<CartGoods> findCartGoodsByCuid(BigInteger cuid){
        List<CartGoods> cartGoodsList=new ArrayList<>();
        List<Cart> cartList=cartService.getAllCart(cuid);
        for(Cart cart:cartList){
            BigInteger littlegoodsid=cart.getLittleGoodsId();
            LittleGoods littleGoods=littleGoodsService.findLittleGoodsById(littlegoodsid);
            CartGoods cartGoods=new CartGoods();
            cartGoods.setLittlegoodsid(littlegoodsid);
            cartGoods.setBiggoodsid(littleGoods.getBigGoodsId());
            cartGoods.setEdtion(littleGoods.getEdition());
            cartGoods.setPrize(littleGoods.getGoodsPrice());
            BigGoods bigGoods=bigGoodsService.getBigGoods(littleGoods.getBigGoodsId());
            cartGoods.setBrand(bigGoods.getBrand());
            cartGoods.setName(bigGoods.getGoodsName());
            cartGoods.setNum(cart.getLittleGoodsNum());
            cartGoodsList.add(cartGoods);
        }
        return cartGoodsList;
    }

}
