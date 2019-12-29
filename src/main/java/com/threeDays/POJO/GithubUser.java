package com.threeDays.POJO;

import java.math.BigInteger;

/**
 * @ClassNameGitHubUser
 * @Date2019-12-221:44
 **/
public class GithubUser {
    private String name;//姓名
    private BigInteger id;//Id
    private String bio;//描述

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
