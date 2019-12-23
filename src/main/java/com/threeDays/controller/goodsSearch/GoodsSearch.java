package com.threeDays.controller.goodsSearch;

import com.threeDays.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;

/**
 * @ClassNameGoodsSearch
 * @Date2019-12-2116:36
 **/
@Controller
public class GoodsSearch {
    @Autowired
    private SearchService searchService;

    /**
     * 牛逼的模糊搜索（zwx快叫爸爸）
     * 返回：符合的商品id列表
     */
    @GetMapping("searchGoods")
    @ResponseBody
    public BigInteger[] searchGoods(@RequestParam("name") String name) {
        return searchService.search(name);
    }
}
