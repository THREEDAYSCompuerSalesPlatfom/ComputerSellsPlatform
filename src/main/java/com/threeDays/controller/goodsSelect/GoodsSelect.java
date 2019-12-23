package com.threeDays.controller.goodsSelect;

import com.threeDays.POJO.BigGoods;
import com.threeDays.dao.BigGoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassNameGoodsSelect
 * @Date2019-12-2116:37
 **/
@Controller
public class GoodsSelect {
    @Autowired
    BigGoodsMapper bigGoodsMapper;
    @RequestMapping(value = "/selectbrand")
    public String selectBrand(Model model,@RequestParam("brand")String brand){
        List<BigGoods> bigGoodsList=bigGoodsMapper.getBigGoodsByBrand(brand);
        model.addAttribute("brandResult",bigGoodsList);
        return "redirect:/selectbrand";//重定向搜索筛选结果界面
    }
}
