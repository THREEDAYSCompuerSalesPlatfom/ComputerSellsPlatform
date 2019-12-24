package com.threeDays.service;

import com.threeDays.POJO.CustomerPassword;
import com.threeDays.dao.CustomerPasswordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * @ClassNameCustomerPasswordService
 * @Date2019-12-2220:35
 **/
@Service
public class CustomerPasswordService {
    @Autowired
    CustomerPasswordMapper customerPasswordMapper;
    public int check(BigInteger customerId, String customerPassword) {
        if (customerPasswordMapper.passwordById(customerId) == null) {
            System.out.println("用户不存在");
            return 0;
        } else if (!customerPasswordMapper.passwordById(customerId).getCustomerPassWord().equals(customerPassword)) {
            System.out.println("密码错误");
            return 2;
        } else {
            return 1;
        }
    }
    public void changePassword(BigInteger customerId,String customerPassword){
        customerPasswordMapper.updatePassword(customerId,customerPassword);
    }
}
