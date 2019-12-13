package com.threeDays.POJO;

import java.math.BigInteger;

public class Deliver {
    private BigInteger deliver_id;
    private BigInteger seller_id;
    private BigInteger order_id;
    private BigInteger express;

    public BigInteger getDeliver_id() {
        return deliver_id;
    }

    public void setDeliver_id(BigInteger deliver_id) {
        this.deliver_id = deliver_id;
    }

    public BigInteger getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(BigInteger seller_id) {
        this.seller_id = seller_id;
    }

    public BigInteger getOrder_id() {
        return order_id;
    }

    public void setOrder_id(BigInteger order_id) {
        this.order_id = order_id;
    }

    public BigInteger getExpress() {
        return express;
    }

    public void setExpress(BigInteger express) {
        this.express = express;
    }
}
