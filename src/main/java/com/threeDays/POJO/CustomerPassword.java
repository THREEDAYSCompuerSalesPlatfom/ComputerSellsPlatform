package com.threeDays.POJO;

import java.math.BigInteger;

/**
 * @ClassNameCustomerPassword
 * @Date2019-12-2219:49
 **/
public class CustomerPassword {
    private BigInteger customerId;
    private String customerPassWord;

    public BigInteger getCustomerId() {
        return customerId;
    }

    public void setCustomerId(BigInteger customerId) {
        this.customerId = customerId;
    }

    public String getCustomerPassWord() {
        return customerPassWord;
    }

    public void setCustomerPassWord(String customerPassWord) {
        this.customerPassWord = customerPassWord;
    }
}
