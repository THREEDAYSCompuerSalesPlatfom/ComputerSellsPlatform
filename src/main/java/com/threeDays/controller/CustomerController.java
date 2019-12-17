package com.threeDays.controller;

import com.threeDays.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @ClassNameCustomerController
 * @Date2019-12-1717:27
 **/
@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
}
