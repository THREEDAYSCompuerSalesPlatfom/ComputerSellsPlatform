package com.threeDays.dao;

import com.threeDays.POJO.GithubCustomer;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;

public interface GithubCustomerMapper {
    //新增买家
    int addGithubCustomer(@Param("accountId")String accountId,
                          @Param("name") String name,
                          @Param("token") String token);
    //获取github买家
    GithubCustomer findByToken(@Param("token")String token);
}
