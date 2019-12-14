package com.threeDays.dao;

import com.threeDays.POJO.Seller;

import java.math.BigInteger;
import java.util.List;

public interface SellerMapper {
   Seller findSellerById(BigInteger seller_id);//通过id查找Seller所有信息
   Seller findSellerByName(String seller_name);//通过name查找Seller所有信息
   BigInteger insertSeller(Seller seller);//增加新的商家，返回seller_id
   int updateSeller(Seller seller);//更新原有商家信息，1为成功，0为失败

}
