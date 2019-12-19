package com.threeDays.controller;

import com.threeDays.POJO.LittleGoods;
import com.threeDays.dao.LittleGoodsMapper;
import com.threeDays.service.LittleGoodsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.util.List;

/**
 * @ClassNameLittleGoodsController
 * @Date2019-12-1717:27
 **/
@Controller
public class LittleGoodsController {
    @Autowired
    private LittleGoodsService littleGoodsService;
    @RequestMapping(value = "/test3",method = RequestMethod.POST)
    @ResponseBody
    public List<LittleGoods> test(){
        return littleGoodsService.getAllLittleGoods();
    }
/*    @Autowired
    private LittleGoodsMapper littleGoodsMapper;

    //测试LittleGoods
    @RequestMapping(value = "/findAllLittleGoods", method = RequestMethod.POST)
    @ResponseBody
    public List<LittleGoods> findAllLittleGoods() {
        return littleGoodsMapper.findAll();
    }

    @RequestMapping(value = "/updateEdition", method = RequestMethod.POST)
    @ResponseBody
    public void updateEdition(BigInteger littleGoodsId,
                              String edition,
                              BigInteger sellerId) {
        littleGoodsMapper.updateEdition(littleGoodsId, edition, sellerId);
    }


    @RequestMapping(value = "/addNewLittleGoods", method = RequestMethod.POST)
    @ResponseBody
    public void addNewLittleGoods(BigInteger bigGoodsId, String edition,
                                  BigInteger sellerId, float goodsPrice) {
        littleGoodsMapper.addNewLittleGoods(bigGoodsId, edition, sellerId, goodsPrice);
    }


    @RequestMapping(value = "/deleteBigGoods", method = RequestMethod.POST)
    @ResponseBody
    public void deleteBigGoods( BigInteger bigGoodsId) {
        littleGoodsMapper.deleteBigGoods(bigGoodsId);
    }


    @RequestMapping(value = "/deleteLittleGoods", method = RequestMethod.POST)
    @ResponseBody
    public void deleteLittleGoods( BigInteger littleGoodsId){
        littleGoodsMapper.deleteLittleGoods(littleGoodsId);
    }



    @RequestMapping(value = "/updatePrice", method = RequestMethod.POST)
    @ResponseBody
    public void updatePrice(BigInteger littleGoodsId, float goodsPrice, BigInteger sellerId){
        littleGoodsMapper.updatePrice(littleGoodsId,goodsPrice,sellerId);
    }

    @RequestMapping(value = "/minPrice", method = RequestMethod.POST)
    @ResponseBody
    public float minPrice( BigInteger bigGoodsId,
                BigInteger sellerId){
        return littleGoodsMapper.minPrice(bigGoodsId,sellerId);
    }


    @RequestMapping(value = "/maxPrice", method = RequestMethod.POST)
    @ResponseBody
    public float maxPrice( BigInteger bigGoodsId,
                           BigInteger sellerId){
        return littleGoodsMapper.minPrice(bigGoodsId,sellerId);
    }
    @RequestMapping(value = "/LittleGoods", method = RequestMethod.POST)
    @ResponseBody
    public List<LittleGoods> LittleGoods( BigInteger bigGoodsId){
        return littleGoodsMapper.LittleGoods(bigGoodsId);
    }

    @RequestMapping(value = "/getEdition", method = RequestMethod.POST)
    @ResponseBody
    public List<String> getEdition(BigInteger bigGoodsId){
        return littleGoodsMapper.getEdition(bigGoodsId);
    }

    @RequestMapping(value = "/findSellerById", method = RequestMethod.POST)
    @ResponseBody
    public BigInteger findSellerById( BigInteger littleGoodsId){
        return littleGoodsMapper.findSellerById(littleGoodsId);
    }

    @RequestMapping(value = "/findAllLittleGoods", method = RequestMethod.POST)
    @ResponseBody
    public  LittleGoods findLittleGoodsById(BigInteger littleGoodsId){
        return littleGoodsMapper.findLittleGoodsById(littleGoodsId);
    }*/


}
