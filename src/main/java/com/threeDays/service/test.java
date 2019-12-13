package com.threeDays.service;

import com.threeDays.POJO.BigGoods;
import com.threeDays.POJO.Cart;
import com.threeDays.POJO.Customer;
import com.threeDays.POJO.LittleGoods;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.plaf.basic.BasicIconFactory;

/**
 * @ClassNametest
 * @Date2019-12-1323:29
 **/
public class test {
    @Autowired
    private Cart cart;
    @Autowired
    private BigGoods bigGoods;
    @Autowired
    private Customer customer;
    @Autowired
    private LittleGoods littleGoods;
}
