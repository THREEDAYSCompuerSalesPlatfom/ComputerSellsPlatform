package com.threeDays.dao;

import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;

public interface CustomerPasswordMapper {
    String queryPasswordById(BigInteger cu_id);//通过id返回密码
    int updatePassword(@Param("seller_id") BigInteger seller_id,@Param("password") String password);//1标识成功，0失败
    int insertPassword(@Param("seller_id")BigInteger seller_id,@Param("password") String password);//1标识成功，0失败
}
