package com.threeDays.service;

import com.threeDays.POJO.*;
import com.threeDays.dao.SellerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.basic.BasicIconFactory;
import java.math.BigInteger;

/**
 * @ClassNametest
 * @Date2019-12-1323:29
 **/
@Service
public class YTATestservice {
//    @Autowired
//    private Cart cart;
//    @Autowired
//    private BigGoods bigGoods;
//    @Autowired
//    private Customer customer;
//    @Autowired
//    private LittleGoods littleGoods;
//    @Autowired
//    private AfterSales afterSales;
//    @Autowired
//    private Deliver deliver;
//    @Autowired
//    private Order order;
//    @Autowired
//    private Seller seller;
    @Autowired
    private SellerMapper sellerMapper;

    public Seller findSellerById(BigInteger seller_id){
        return sellerMapper.findSellerById(seller_id);
    }
}
