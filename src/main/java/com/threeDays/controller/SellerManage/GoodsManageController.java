package com.threeDays.controller.SellerManage;

import com.threeDays.POJO.LittleGoods;
import com.threeDays.service.BigGoodsService;
import com.threeDays.service.LittleGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Controller
public class GoodsManageController {
    @Autowired
    private BigGoodsService bigGoodsService;
    @Autowired
    private LittleGoodsService littleGoodsService;

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
     */
    @PostMapping("/GoodsManage/updateBigGoods")
    @ResponseBody
    public BigInteger updateBigGoods(BigInteger bigGoodsId, String goodsName, String brand, HttpServletRequest request) {
        BigInteger seller_id = (BigInteger) request.getSession().getAttribute("Seller_id");
        return bigGoodsService.updateBigGoods(bigGoodsId, seller_id, goodsName, brand);
    }

    /**
     * 删除一类商品
     * 成功原有商品id
     * 失败返回-1：商品号非该商家号所有的商品
     */
    @PostMapping("/GoodsManage/deleteBigGoods")
    @ResponseBody
    public BigInteger deleteBigGoods(BigInteger bigGoodsId, HttpServletRequest request) {
        BigInteger seller_id = (BigInteger) request.getSession().getAttribute("Seller_id");
        return bigGoodsService.deleteBigGoods(bigGoodsId, seller_id);
    }

    /**
     * 添加新的配置newLittleGoods
     * 参数：bigGoodsId，edition，goodsPrice
     * 成功返回新加入的littlegoodsid
     * 失败返回-1：无此商品请添加商品
     * 失败返回-2：已有此配置
     */
    @PostMapping("/GoodsManage/addNewLittleGoods")
    @ResponseBody
    public BigInteger addNewLittleGoods(BigInteger bigGoodsId, String edition, float goodsPrice, HttpServletRequest request) {
        BigInteger seller_id = (BigInteger) request.getSession().getAttribute("Seller_id");
        return littleGoodsService.addNewLittleGoods(bigGoodsId, edition, seller_id, goodsPrice);
    }

    /**
     * 寻找具体商品配置列表
     */
    @PostMapping("/GoodsManage/findLittleGoods")
    @ResponseBody
    public List<LittleGoods> findLittleGoods(BigInteger bigGoodsId) {
        return littleGoodsService.findLittleGoods(bigGoodsId);
    }

    /**
     * 删除原有某个配置
     * 成功返回1
     * 错误返回0
     */
    @PostMapping("/GoodsManage/deleteLittleGoodsById")
    @ResponseBody
    public int deleteLittleGoodsById(BigInteger littleGoodsId) {
        return littleGoodsService.deleteLittleGoodsById(littleGoodsId);
    }

    /**
     * 修改某个配置
     * 成功返回1
     * 错误返回0
     */
    @PostMapping("/GoodsManage/updateLittleGoods")
    @ResponseBody
    public int updateLittleGoods(String edition, BigInteger liitlegoodsid, HttpServletRequest request) {
        BigInteger seller_id = (BigInteger) request.getSession().getAttribute("Seller_id");
        return littleGoodsService.updateLittleGoods(edition, seller_id, liitlegoodsid);
    }

    /**
     * 获取某个商品价格范围
     */
    @PostMapping("/GoodsManage/minPrice")
    @ResponseBody
    public int minPrice(BigInteger bigGoodsId, HttpServletRequest request) {
        BigInteger seller_id = (BigInteger) request.getSession().getAttribute("Seller_id");
        return littleGoodsService.minPrice(bigGoodsId, seller_id);
    }

    @PostMapping("/GoodsManage/maxPrice")
    @ResponseBody
    public int maxPrice(BigInteger bigGoodsId, HttpServletRequest request) {
        BigInteger seller_id = (BigInteger) request.getSession().getAttribute("Seller_id");
        return littleGoodsService.maxPrice(bigGoodsId, seller_id);
    }

    /**
     * 修改商品价格
     * 成功返回1
     * 失败返回0
     */
    @PostMapping("/GoodsManage/updateLittleGoodsPrice")
    @ResponseBody
    public int updateLittleGoodsPrice(BigInteger littleGoodsId, float goodsPrice, HttpServletRequest request) {
        BigInteger seller_id = (BigInteger) request.getSession().getAttribute("Seller_id");
        LittleGoods littleGoods = littleGoodsService.findLittleGoodsById(littleGoodsId);
        return littleGoodsService.updateLittleGoodsPrice(littleGoods.getEdition(), goodsPrice, seller_id, littleGoods.getBigGoodsId());
    }
}












