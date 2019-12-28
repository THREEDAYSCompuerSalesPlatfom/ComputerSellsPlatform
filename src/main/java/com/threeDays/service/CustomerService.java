package com.threeDays.service;

import com.threeDays.POJO.Customer;
import com.threeDays.POJO.CustomerPassword;
import com.threeDays.POJO.GithubCustomer;
import com.threeDays.dao.CustomerMapper;
import com.threeDays.dao.CustomerPasswordMapper;
import com.threeDays.dao.GithubCustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

/**
 * @ClassNameCustomerService
 * @Date2019-12-1717:04
 **/
@Service
public class CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerPasswordMapper customerPasswordMapper;

    @Autowired
    private GithubCustomerMapper githubCustomerMapper;

    //test
    public List<Customer> getAllCustomer() {
        return customerMapper.findAll();
    }

    //修改用户信息
    public void updateCustomer(BigInteger customerId, String cu_Telephone, String cu_Address, String cu_Name) {
        if (customerMapper.getCustomer(customerId) == null) {
            System.out.println("无此用户");
        } else {
            customerMapper.updateCustomer(customerId, cu_Telephone, cu_Address, cu_Name);
            System.out.println("successful");
        }
    }

    //注销用户
    public void deleteCustomer(BigInteger customerId) {
        if (customerMapper.getCustomer(customerId) == null) {
            System.out.println("无此用户");
        } else {
            customerMapper.deleteCustomer(customerId);
            System.out.println("successful");
        }
    }

    //创建用户
    public String createCustomer(String cu_Telephone, String cu_Address, String cu_Name, String customerPassWord) {
        String token = UUID.randomUUID().toString();
        String accountId = UUID.randomUUID().toString();
        customerMapper.addCustomer(cu_Telephone, cu_Address, cu_Name, token, accountId,0);
        BigInteger customerId = customerMapper.getCustomerId(token);
        customerPasswordMapper.insertPassword(customerId, customerPassWord);
        return token;
    }

    //更新token
    public void updateToken(BigInteger customerId,String token){
        customerMapper.updateToken(customerId, token);
    }
    //通过Github登陆用户和本地用户绑定
    public BigInteger mergeCustomer(GithubCustomer githubCustomer) {
        if (customerMapper.findByAccountId(githubCustomer.getAccountId()) != null) {
            customerMapper.addCustomer("", "", githubCustomer.getName(), githubCustomer.getToken(), githubCustomer.getAccountId(),0);
        } else {
            //更新token
            Customer customer = customerMapper.findByAccountId(githubCustomer.getAccountId());
            customerMapper.updateToken(customer.getCustomerId(), githubCustomer.getToken());
        }
        return customerMapper.getCustomerId(githubCustomer.getToken());
    }

    //通过token找到用户
    public Customer findByToken(String token) {
        return customerMapper.findByToken(token);
    }

    float queryBalance(BigInteger cu_id){
        return customerMapper.queryBalance(cu_id);
    }
    public int updateBalance(BigInteger cu_id,float balance){
        return customerMapper.updateBalance(cu_id,balance);
    }
    public void addGithubCustomer( GithubCustomer githubCustomer){
        githubCustomerMapper.addGithubCustomer(githubCustomer.getAccountId(),githubCustomer.getName(),githubCustomer.getToken());
    }
}
