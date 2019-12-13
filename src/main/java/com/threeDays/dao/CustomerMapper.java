package com.threeDays.dao;

import com.threeDays.POJO.Customer;

import java.util.List;

/**
 * @ClassNamecustomer
 * @Date2019-12-1320:49
 **/
public interface CustomerMapper {
    List<Customer> findAll();
}
