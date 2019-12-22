package com.threeDays.dao;

import com.threeDays.POJO.CustomerPassword;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;

public interface CustomerPasswordMapper {
    CustomerPassword passwordById(@Param("customerId") BigInteger customerId);//通过id返回密码
    int updatePassword(@Param("customerId") BigInteger customerId,@Param("customerPassWord") String customerPassWord);//1标识成功，0失败
    int insertPassword(@Param("customerId")BigInteger customerId,@Param("customerPassWord") String customerPassWord);//1标识成功，0失败
}
