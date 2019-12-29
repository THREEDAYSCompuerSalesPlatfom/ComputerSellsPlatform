package com.threeDays.controller.littleGoodsController;

import com.threeDays.POJO.LittleGoods;
import com.threeDays.service.LittleGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassNameLittleGoodsController
 * @Date2019-12-231:11
 **/
@Controller
public class LittleGoodsController {
    @Autowired
    LittleGoodsService littleGoodsService;
    @RequestMapping("/littlerGoods")
    public String getEdition(Model model, @RequestParam("bigGoodsId")BigInteger bigGoodsId){
        List<String> edition=littleGoodsService.getEdition(bigGoodsId);
        List<LittleGoods>littleGoods=littleGoodsService.findLittleGoods(bigGoodsId);
        Map<BigInteger, Float> price=new HashMap<>();
        for(LittleGoods littleGoods1:littleGoods){
            price.put(littleGoods1.getLittleGoodsId(),littleGoods1.getGoodsPrice());
        }
        model.addAttribute("edition",edition);
        model.addAttribute("littleGoods",littleGoods);
        model.addAttribute("price", price);
        return "littleGoods";
    }
    @RequestMapping("/getPrice")
    @ResponseBody
    public float getPrice(@RequestParam("bigGoodsId")BigInteger bigGoodsId,
                           @RequestParam("edition")String edition){
       return littleGoodsService.findLittleGoodsById(littleGoodsService.getLittleGoodsId(edition,bigGoodsId)).getGoodsPrice();
    }
}
