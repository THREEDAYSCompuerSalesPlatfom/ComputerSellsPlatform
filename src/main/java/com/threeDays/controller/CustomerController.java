package com.threeDays.controller;

import com.threeDays.POJO.Customer;
import com.threeDays.dao.CustomerMapper;
import com.threeDays.service.CustomerService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.util.List;

/**
 * @ClassNameCustomerController
 * @Date2019-12-1717:27
 **/
@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @RequestMapping(value = "/test2",method = RequestMethod.POST)
    @ResponseBody
    public List<Customer> test(){
        return customerService.getAllCustomer();
    }
    @RequestMapping(value = "/updateName",method=RequestMethod.POST)
    @ResponseBody
        public void updateName (BigInteger cu_id,String cu_Telephone,String cu_Address,String cu_Name){
        customerService.updateCustomer(cu_id,cu_Telephone,cu_Address,cu_Name);
    }

 /*   @Autowired
    private CustomerMapper customerMapper;

    //测试customer
    @RequestMapping(value = "/findAllCustomer", method = RequestMethod.POST)
    @ResponseBody
    public List<Customer> findAllCustomer() {
        return customerMapper.findAll();
    }

    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
    @ResponseBody
    public void addCustomer(String cu_Telephone, String cu_Address, String cu_Name) {
        customerMapper.addCustomer(cu_Telephone, cu_Address, cu_Name);
    }

    @RequestMapping(value = "/deleteCustomer", method = RequestMethod.POST)
    @ResponseBody
    public void deleteCustomer(BigInteger customerId){
        customerMapper.deleteCustomer(customerId);
    }

    @RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
    @ResponseBody
    public  void updateCustomer( BigInteger customerId, String cu_Telephone, String cu_Address, String cu_Name){
        customerMapper.updateCustomer(customerId,cu_Telephone,cu_Address,cu_Name);
    }

    @RequestMapping(value = "/getCustomer", method = RequestMethod.POST)
    @ResponseBody
    public Customer getCustomer(@Param("customerId") BigInteger customerId){
        return customerMapper.getCustomer(customerId);
    }*/
}
