package com.threeDays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class LittlegoodsService {
    @Autowired
    private LittlegoodsService littlegoodsService;

    /**
     * 根据详细商品id返回sellerid
     * 不存在返回null
     * */
   public BigInteger findSellerById(BigInteger littlegoods_id){
       return littlegoodsService.findSellerById(littlegoods_id);
   }
}