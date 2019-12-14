package com.threeDays.service;

import com.threeDays.POJO.Seller;
import com.threeDays.dao.SellerMapper;
import com.threeDays.dao.SellerPasswordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigInteger;

@Service
public class Sellerservice {
    @Autowired
    private SellerMapper sellerMapper;
    @Autowired
    private SellerPasswordMapper sellerPasswordMapper;

    /**
     * 登录业务
     * 成功返回Seller_id
     * name不存在返回-1
     * password不对返回-2
     **/
    public BigInteger login(String name, String password) {
        Seller seller = sellerMapper.findSellerByName(name);
        if (seller == null) {
            return new BigInteger("-1");
        }
        BigInteger seller_id = seller.getSeller_id();
        if (password.equals(sellerPasswordMapper.queryPasswordById(seller_id))) {
            return seller_id;
        } else {
            return new BigInteger("-2");
        }
    }

    /**
     * 注册业务
     * 返回-1：姓名已存在
     **/
    public BigInteger register(Seller seller, String password) {
        String name = seller.getSeller_name();
        if (sellerMapper.findSellerByName(name) != null) {
            return new BigInteger("-1");
        }

        BigInteger seller_id = sellerMapper.insertSeller(seller);
        sellerPasswordMapper.insertPassword(seller_id, password);
        return seller_id;

    }

    /**
     * 更新seller信息
     * 返回1标识成功，0失败
     * **/
    public int updateSellerInfo(Seller seller) {
        return sellerMapper.updateSeller(seller);
    }
    /**
     * 更新seller密码
     * 返回1标识成功，0失败
     * **/
    public int updateSellerPassword(BigInteger seller_id,String password) {
        return sellerPasswordMapper.updatePassword(seller_id,password);
    }

    /**
     * 通过seller_id返回Seller
     **/
    public Seller findSellerById(BigInteger seller_id) {
        return sellerMapper.findSellerById(seller_id);
    }

}
