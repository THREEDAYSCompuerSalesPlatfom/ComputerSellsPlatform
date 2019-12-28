package com.threeDays.POJO;

import com.threeDays.Utils.SortUtils.LiitleGoodsSortBySeller;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassNamecuOrderGoods
 * @Date2019-12-2816:15
 **/
public class cuOrderGoods {
    /**一个用户多个cuOrderGoods
     * 一个cuOrderGoods代表一栏
     * 一栏多个littleGoods num 从littleGoods获取
     * Date为通过Order.getDate获取
     *sellerid通过littleGoods获得进而获取seller
     * goodsprice通过littleGoods获取
     * num通过findGoodsNumByOrderId
     * */
    private Seller seller;
    private Date date;
    private BigInteger orderId;//同orderGoodsId同
    private List<ShowGoods>showGoods;
    private int status;//订单状态

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ShowGoods> getShowGoods() {
        return showGoods;
    }

    public void setShowGoods(List<ShowGoods> showGoods) {
        this.showGoods = showGoods;
    }

    private float allPrize;

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigInteger getOrderId() {
        return orderId;
    }

    public void setOrderId(BigInteger orderId) {
        this.orderId = orderId;
    }



    public float getAllPrize() {
        return allPrize;
    }

    public void setAllPrize(float allPrize) {
        this.allPrize = allPrize;
    }
}
