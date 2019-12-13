package com.threeDays.dao;


import com.threeDays.POJO.LittleGoods;

import java.util.List;

/**
 * @ClassNameLittleGoodsMapper
 * @Date2019-12-1320:53
 **/
public interface LittleGoodsMapper {
    List<LittleGoods> findAll();
}
