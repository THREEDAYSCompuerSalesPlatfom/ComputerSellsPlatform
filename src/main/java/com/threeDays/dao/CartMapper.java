package com.threeDays.dao;

import com.threeDays.POJO.Cart;

import java.util.List;

/**
 * @ClassNamecart
 * @Date2019-12-1320:50
 **/
public interface CartMapper {
    List<Cart>findAll();
}
