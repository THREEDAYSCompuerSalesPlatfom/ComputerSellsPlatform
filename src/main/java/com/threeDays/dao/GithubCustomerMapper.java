package com.threeDays.dao;

import com.threeDays.POJO.GithubCustomer;
import org.apache.ibatis.annotations.Param;

public interface GithubCustomerMapper {
    //新增买家
    int addGithubCustomer(GithubCustomer githubCustomer);
    //获取github买家
    GithubCustomer findByToken(@Param("token")String token);
}
