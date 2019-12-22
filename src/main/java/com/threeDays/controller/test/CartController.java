package com.threeDays.controller.test;

import com.threeDays.POJO.Cart;
import com.threeDays.POJO.LittleGoods;
import com.threeDays.dao.CartMapper;
import com.threeDays.service.CartService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.util.List;

/**
 * @ClassNameCartController
 * @Date2019-12-1717:25
 **/
@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private CartMapper cartMapper;

    @RequestMapping(value = "/test1", method = RequestMethod.POST)
    @ResponseBody
    public List<Cart> test() {
        return cartService.getAllCart();
    }

    @RequestMapping(value = "/updateNum", method = RequestMethod.POST)
    @ResponseBody
    public void updateNum(BigInteger customerId, BigInteger littleGoodsId, int littleGoodsNum) {
        cartMapper.updateLittleGoodsNum(customerId, littleGoodsId, littleGoodsNum);
    }
    @RequestMapping(value = "/addNewLittleGoods",method = RequestMethod.POST)
    @ResponseBody
    public void addNewLittleGoods(BigInteger customerId,BigInteger littleGoodsId,int littleGoodsNum){
        cartService.addNewLittleGoods(customerId,littleGoodsId,littleGoodsNum);
    }



   /* @Autowired
    private CartMapper cartMapper;
    //测试cart
    @RequestMapping(value = "/findAllCart", method = RequestMethod.POST)
    @ResponseBody
    public List<Cart> findAllCart() {
        return cartMapper.findAll();
    }

    @RequestMapping(value = "/addLittleGoods", method = RequestMethod.POST)
    @ResponseBody
    public void addLittleGoods( BigInteger customerId, BigInteger littleGoodsId, int littleGoodsNum){
        cartMapper.addLittleGoods(customerId,littleGoodsId,littleGoodsNum);
    }

    @RequestMapping(value = "/deleteLittleGoods", method = RequestMethod.POST)
    @ResponseBody
    public void deleteLittleGoods( BigInteger customerId, BigInteger littleGoodsId){
        cartMapper.deleteLittleGoods(customerId,littleGoodsId);
    }

    @RequestMapping(value = "/removeLittleGoods", method = RequestMethod.POST)
    @ResponseBody
    public void removeLittleGoods( BigInteger customerId, BigInteger littleGoodsId, int littleGoodsNum){
        cartMapper.removeLittleGoods(customerId,littleGoodsId,littleGoodsNum);
    }

    @RequestMapping(value = "/updateLittleGoodsEdition", method = RequestMethod.POST)
    @ResponseBody
    public void  updateLittleGoodsEdition( BigInteger customerId, BigInteger littleGoodsId, int littleGoodsNum){
        cartMapper.updateLittleGoodsEdition(customerId,littleGoodsId,littleGoodsNum);
    }
    @RequestMapping(value = "/updateLittleGoodsNum", method = RequestMethod.POST)
    @ResponseBody
    public void  updateLittleGoodsNum(BigInteger customerId, BigInteger littleGoodsId, int littleGoodsNum){
        cartMapper.updateLittleGoodsNum(customerId,littleGoodsId,littleGoodsNum);
    }

    @RequestMapping(value = "/selectLittleGoodsNum", method = RequestMethod.POST)
    @ResponseBody
    public int selectLittleGoodsNum( BigInteger customerId, BigInteger littleGoodsId){
        return cartMapper.selectLittleGoodsNum(customerId,littleGoodsId);
    }

    @RequestMapping(value = "/selectLittleGoods", method = RequestMethod.POST)
    @ResponseBody
    LittleGoods selectLittleGoods(BigInteger customerId, BigInteger littleGoodsId){
        return cartMapper.selectLittleGoods(customerId,littleGoodsId);
    }
    @RequestMapping(value = "/getAllLittleGoodsId", method = RequestMethod.POST)
    @ResponseBody
    List<BigInteger> getAllLittleGoodsId( BigInteger customerId){
        return cartMapper.getAllLittleGoodsId(customerId);
    }*/

}
