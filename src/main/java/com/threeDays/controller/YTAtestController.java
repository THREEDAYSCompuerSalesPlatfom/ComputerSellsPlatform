package com.threeDays.controller;

import com.threeDays.POJO.Seller;
import com.threeDays.service.YTATestservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;

@Controller
public class YTAtestController {
    @Autowired
    private YTATestservice testservice;
    @RequestMapping("/findfindSellerById/{seller_id}")
    @ResponseBody
    public Seller findSellerById(@PathVariable("seller_id") BigInteger seller_id){
        return testservice.findSellerById(seller_id);
    }

}
