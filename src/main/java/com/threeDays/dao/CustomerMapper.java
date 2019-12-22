package com.threeDays.dao;

import com.threeDays.POJO.Customer;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * @ClassNamecustomer
 * @Date2019-12-1320:49
 **/
public interface CustomerMapper {
    //test
    List<Customer> findAll();

    //新增买家
    int addCustomer(@Param("cu_Telephone") String cu_Telephone,
                    @Param("cu_Address") String cu_Address,
                    @Param("cu_Name") String cu_Name,
                    @Param("token")String token,
                    @Param("accountId")String accountId,
                    @Param("balance")float balance);

    //注销买家
    int deleteCustomer(@Param("customerId") BigInteger customerId);

    //修改买家信息
    int updateCustomer(@Param("customerId") BigInteger customerId,
                       @Param("cu_Telephone") String cu_Telephone,
                       @Param("cu_Address") String cu_Address,
                       @Param("cu_Name") String cu_Name);

    //查询买家
    Customer getCustomer(@Param("customerId") BigInteger customerId);

    //获取买家Id
    BigInteger getCustomerId(@Param("token")String token);

    //获取买家
    Customer findByToken(@Param("token") String token);

    //更新Token
    int updateToken(@Param("customerId") BigInteger customerId,
                    @Param("token")String token);
    //通过accountId获取买家
    Customer findByAccountId(@Param("accountId")String accountId);
}
