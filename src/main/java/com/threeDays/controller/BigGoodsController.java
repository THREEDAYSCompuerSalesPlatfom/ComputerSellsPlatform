package com.threeDays.controller;

import com.threeDays.POJO.BigGoods;
import com.threeDays.POJO.Cart;
import com.threeDays.POJO.Customer;
import com.threeDays.POJO.LittleGoods;
import com.threeDays.dao.BigGoodsMapper;
import com.threeDays.dao.CartMapper;
import com.threeDays.dao.CustomerMapper;
import com.threeDays.dao.LittleGoodsMapper;
import com.threeDays.service.BigGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;
import java.math.BigInteger;
import java.util.List;

@Controller
public class BigGoodsController {
    @Autowired
    private BigGoodsService bigGoodsService;
    @RequestMapping(value = "/tect",method = RequestMethod.POST)
    @ResponseBody
    public List<BigGoods> test(){
        return bigGoodsService.getAllGoods();
    }
    @RequestMapping(value = "/getBigGoodsByBrand",method = RequestMethod.POST)
    @ResponseBody
    public List<BigGoods> getBigGoodsByBrand(@PathParam("brand")String brand){
        return bigGoodsService.getBigGoodsByBrand(brand);
    }
    @RequestMapping(value = "/getBigGoodsName",method = RequestMethod.POST)
    @ResponseBody
    public List<BigGoods> getBigGoodsName(@PathParam("goodsName")String goodsName){
        return bigGoodsService.getBigGoodsName(goodsName);
    }
/*//测试biggoods
    @RequestMapping(value = "/findAllBigGoods", method = RequestMethod.POST)
    @ResponseBody
    public List<BigGoods> findAllBigGoods() {
        return bigGoodsMapper.findAll();
    }

    @RequestMapping(value = "/findAllBigGoods", method = RequestMethod.POST)
    @ResponseBody
    public void findAllBigGoods(String goodsName, BigInteger sellerId,String brand) {
        bigGoodsMapper.saveBigGoods(goodsName, sellerId,brand);
    }

    @RequestMapping(value = "/deleteBigGoods", method = RequestMethod.POST)
    @ResponseBody
    public void deleteBigGoods(BigInteger bigGoodsId) {
        bigGoodsMapper.deleteBigGoods(bigGoodsId);
    }

    @RequestMapping(value = "/updateNewBigGoods", method = RequestMethod.POST)
    @ResponseBody
    public void updateNewBigGoods(BigInteger bigGoodsId,
                                  String goodsName,String brand) {
        bigGoodsMapper.updateNewBigGoods(bigGoodsId, goodsName,brand);
    }

    @RequestMapping(value = "/findBigGoodsById", method = RequestMethod.POST)
    @ResponseBody
    public BigGoods findBigGoodsById(BigInteger bigGoodsId) {
        return bigGoodsMapper.findBigGoodsById(bigGoodsId);
    }

    @RequestMapping(value = "/findBigGoodsByName", method = RequestMethod.POST)
    @ResponseBody
    public List<BigGoods> findBigGoodsByName(String goodsName) {
        return bigGoodsMapper.findBigGoodsByName(goodsName);
    }

    @RequestMapping(value = "/getBigGoodsId", method = RequestMethod.POST)
    @ResponseBody
    public List<BigInteger> getBigGoodsId(String goodsName, BigInteger sellerId) {
        return bigGoodsMapper.getBigGoodsId(goodsName, sellerId);
    }


    @RequestMapping(value = "/getSellerId", method = RequestMethod.POST)
    @ResponseBody
    public List<BigInteger> getSellerId(String goodsName) {
        return bigGoodsMapper.getSellerId(goodsName);
    }

    @RequestMapping(value = "/getBigGoods", method = RequestMethod.POST)
    @ResponseBody
    public BigGoods getBigGoods(BigInteger bigGoodsId){
        return bigGoodsMapper.getBigGoods(bigGoodsId);
    }*/


}
