package com.threeDays.controller;

import com.threeDays.service.LittleGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @ClassNameLittleGoodsController
 * @Date2019-12-1717:27
 **/
@Controller
public class LittleGoodsController {
    @Autowired
    private LittleGoodsService littlegoodsService;

}
