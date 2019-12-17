package com.threeDays.controller;

import com.threeDays.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @ClassNameCartController
 * @Date2019-12-1717:25
 **/
@Controller
public class CartController {
    @Autowired
    private CartService cartService;
}
