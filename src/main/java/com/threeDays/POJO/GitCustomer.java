package com.threeDays.POJO;

import com.fasterxml.jackson.databind.node.BigIntegerNode;

import java.math.BigInteger;

/**
 * @ClassNameGitCustomer
 * @Date2019-12-223:47
 **/
public class GitCustomer {
    private BigInteger id;
    private String name;
    private String accountId;
    private String token;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
