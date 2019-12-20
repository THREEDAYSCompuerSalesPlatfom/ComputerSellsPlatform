package com.threeDays.controller.SellerManage;

import com.threeDays.service.BigGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.Map;

@Controller
public class GoodsManageController {
    @Autowired
    private BigGoodsService bigGoodsService;

    /**
     * 到商品管理界面
     * （不确定是否要这个界面的网页，放着吧，不用就不用了）
     */
    @RequestMapping("/GoodsManage")
    public String goodsManage() {
        return "redirect:/GoodsManage";
    }

    /**
     * 分页通过商家id获取商品列表
     * 用PageNum控制返回第几页
     * 一页中有pagesize个商品
     * 通过返回的map.get("list")获取到返回的表,其中list存有biggoods整个对象
     * 通过返回的map.get("Total")获取总页数
     * 通过返回的map.get("PageNum")获取当前页数
     */
    @PostMapping("/GoodsManage/getPAGEBigGoodsBySellerId")
    @ResponseBody
    public Map<String, Object> getPAGEBigGoodsBySellerId(int PageNum, int pagesize, HttpServletRequest request) {
        BigInteger seller_id = (BigInteger) request.getSession().getAttribute("Seller_id");
        return bigGoodsService.getPAGEBigGoodsBySellerId(seller_id, PageNum, pagesize);
    }

    /**
     * 更新商品名称以及品牌
     * 成功返回商品id
     * 失败返回-1：商品号非该商家号所有的商品
     * 参数：bigGoodsId，goodsName，brand
     * */
    @PostMapping("/GoodsManage/updateBigGoods")
    @ResponseBody
    public BigInteger updateBigGoods(BigInteger bigGoodsId,  String goodsName, String brand,HttpServletRequest request){
        BigInteger seller_id = (BigInteger) request.getSession().getAttribute("Seller_id");
        return bigGoodsService.updateBigGoods(bigGoodsId,seller_id,goodsName,brand);
    }

    /**
     * 删除一类商品
     * 成功原有商品id
     * 失败返回-1：商品号非该商家号所有的商品
     * */
    @PostMapping("/GoodsManage/deleteBigGoods")
    @ResponseBody
    public BigInteger deleteBigGoods(BigInteger bigGoodsId,HttpServletRequest request){
        BigInteger seller_id = (BigInteger) request.getSession().getAttribute("Seller_id");
        return bigGoodsService.deleteBigGoods(bigGoodsId,seller_id);
    }
}












