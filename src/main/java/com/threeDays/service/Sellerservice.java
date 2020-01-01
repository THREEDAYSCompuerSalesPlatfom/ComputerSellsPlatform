package com.threeDays.service;

import com.sun.org.apache.regexp.internal.RE;
import com.threeDays.POJO.Seller;
import com.threeDays.dao.SellerMapper;
import com.threeDays.dao.SellerPasswordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


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
     * 返回-1：用户名已存在
     **/
    public BigInteger register(Seller seller, String password) {
        String name = seller.getSeller_name();
        if (sellerMapper.findSellerByName(name) != null) {
            return new BigInteger("-1");
        }
        sellerMapper.insertSeller(seller);
        BigInteger seller_id = seller.getSeller_id();
        sellerPasswordMapper.insertPassword(seller_id, password);

        sellerMapper.updateSeller(seller);
        return seller_id;

    }

    /**
     * 更新seller信息
     * 返回1标识成功，0失败
     * 返回-1：id未在seller对象中标明
     * 返回-2：不存在此id
     * 返回-3：用户名重复
     **/
    public int updateSellerInfo(Seller seller,String originname) {
        if (seller.getSeller_id() == null) {
            return -1;
        }
        if (sellerMapper.findSellerById(seller.getSeller_id()) == null) {
            return -2;
        }
        if(seller.getSeller_name().equals(originname)){
            return sellerMapper.updateSeller(seller);
        }

        if (sellerMapper.findSellerByName(seller.getSeller_name()) != null) {
            return -3;
        }
        return sellerMapper.updateSeller(seller);
    }

    /**
     * 更新seller密码
     * 返回1标识成功，0失败
     **/
    public int updateSellerPassword(BigInteger seller_id, String password) {
        return sellerPasswordMapper.updatePassword(seller_id, password);
    }

    /**
     * 通过seller_id返回Seller
     **/
    public Seller findSellerById(BigInteger seller_id) {
        return sellerMapper.findSellerById(seller_id);
    }

    /**
     * 判断商家名是否已经存在
     * 已经存在返回1
     * 不存在返回0
     */
    public int isNameExist(String seller_name) {
        if (sellerMapper.findSellerByName(seller_name) == null) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * 查余额
     */
    public Float queryBalance(BigInteger seller_id) {
        return sellerMapper.queryBalance(seller_id);
    }

    /**
     * 改余额
     * 返回1成功
     * 返回0失败
     */
    public int updateBalance(BigInteger seller_id, float balance) {
        return sellerMapper.updateBalance(seller_id, balance);
    }


}
