package com.threeDays.dao;

import java.math.BigInteger;

public interface SellerPasswordMapper {
    String queryPasswordById(BigInteger seller_id);//通过id返回密码
    int updatePassword(BigInteger seller_id,String password);//1标识成功，0失败
}