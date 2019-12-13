package com.threeDays.POJO;

import java.math.BigInteger;

public class Seller {
    private BigInteger seller_id;//卖家id
    private String seller_tel;//卖家电话
    private String seller_address;//卖家地址
    private String seller_name;//卖家姓名

    public BigInteger getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(BigInteger seller_id) {
        this.seller_id = seller_id;
    }

    public String getSeller_tel() {
        return seller_tel;
    }

    public void setSeller_tel(String seller_tel) {
        this.seller_tel = seller_tel;
    }

    public String getSeller_address() {
        return seller_address;
    }

    public void setSeller_address(String seller_address) {
        this.seller_address = seller_address;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }
}
