package com.threeDays.service;

import com.threeDays.POJO.Customer;
import com.threeDays.dao.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * @ClassNameCustomerService
 * @Date2019-12-1717:04
 **/
@Service
public class CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    //test
    public List<Customer> getAllCustomer() {
        return customerMapper.findAll();
    }

    //修改用户信息
    public void updateCustomer(BigInteger customerId, String cu_Telephone, String cu_Address, String cu_Name) {
        if (customerMapper.getCustomer(customerId) == null) {
            System.out.println("无此用户");
        } else {
            customerMapper.updateCustomer(customerId, cu_Telephone, cu_Address, cu_Name);
            System.out.println("successful");
        }
    }

    //注销用户
    public void deleteCustomer(BigInteger customerId) {
        if (customerMapper.getCustomer(customerId) == null) {
            System.out.println("无此用户");
        } else {
            customerMapper.deleteCustomer(customerId);
            System.out.println("successful");
        }
    }

    //创建用户
    public void createCustomer(String cu_Telephone, String cu_Address, String cu_Name) {
        customerMapper.addCustomer(cu_Telephone, cu_Address, cu_Name);
    }


}
