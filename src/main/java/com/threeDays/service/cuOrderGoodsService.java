package com.threeDays.service;

import com.threeDays.POJO.*;
import com.threeDays.Utils.SortUtils.BigintegerSort;
import com.threeDays.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;

import java.util.List;

/**
 * @ClassNamecuOrderGoodsService
 * @Date2019-12-2817:21
 **/
@Service
public class cuOrderGoodsService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    LittleGoodsMapper littleGoodsMapper;
    @Autowired
    BigGoodsMapper bigGoodsMapper;
    @Autowired
    SellerMapper sellerMapper;
    @Autowired
    OrderService orderService;
    @Autowired
    OrdergoodsMapper ordergoodsMapper;

    public List<cuOrderGoods> getAll(BigInteger customerId) {
        List<BigInteger> orderIdList = orderMapper.findOrderIdByCustomer(customerId);
        List<cuOrderGoods> cuOrderGoodsList = new ArrayList<>();

        for (BigInteger orderId : orderIdList) {
            List<ShowGoods> showGoods = new ArrayList<>();
            float allPrize = 0;
            List<BigInteger> littleGoodsIdList = orderService.findGoodsIdByOrderId(orderId);
            for (BigInteger i : littleGoodsIdList) {
                int num = orderService.getGoodsNum(orderId, i);
                float price = littleGoodsMapper.findLittleGoodsById(i).getGoodsPrice();
                LittleGoods littleGoods = littleGoodsMapper.findLittleGoodsById(i);
                BigInteger bigGoodsId=littleGoods.getBigGoodsId();
                BigGoods bigGoods=bigGoodsMapper.getBigGoods(bigGoodsId);
                allPrize = allPrize + price * num;
                showGoods.add(new ShowGoods(orderId, littleGoods, bigGoods, num));
            }
            cuOrderGoods cuOrderGoods = new cuOrderGoods();
            cuOrderGoods.setOrderId(orderId);
            cuOrderGoods.setDate(orderMapper.findOrderById(orderId).getDate());
            cuOrderGoods.setAllPrize(allPrize);
            cuOrderGoods.setShowGoods(showGoods);
            cuOrderGoods.setSeller(sellerMapper.findSellerById(orderService.findSellerByOrderId(orderId)));
            cuOrderGoods.setStatus(orderMapper.findOrderById(orderId).getOrder_status());
            cuOrderGoodsList.add(cuOrderGoods);
        }
        return cuOrderGoodsList;
    }
}
