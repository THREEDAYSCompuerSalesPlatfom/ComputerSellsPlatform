package com.threeDays.service;

import com.threeDays.dao.SellerPasswordMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class SellerpasswordService {
    @Autowired
    SellerPasswordMapper sellerPasswordMapper;
    public int updatePassword(@Param("seller_id") BigInteger seller_id, @Param("password") String password) {//1标识成功，0失败
        return sellerPasswordMapper.updatePassword(seller_id,password);
    }
    public String queryPasswordById(BigInteger sellerid){
        return sellerPasswordMapper.queryPasswordById(sellerid);
    }

}
