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
                    @Param("cu_Name") String cu_Name);

    //注销买家
    int deleteCustomer(@Param("customerId") BigInteger customerId);

    //修改买家信息
    int updateCustomer(@Param("customerId") BigInteger customerId,
                       @Param("cu_Telephone") String cu_Telephone,
                       @Param("cu_Address") String cu_Address,
                       @Param("cu_Name") String cu_Name);

    //查询买家
    Customer getCustomerAddress(@Param("customerId") BigInteger customerId);

}
