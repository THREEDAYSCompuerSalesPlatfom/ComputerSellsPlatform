package com.threeDays.controller.buyController;

import com.threeDays.POJO.CartGoods;
import com.threeDays.POJO.Customer;
import com.threeDays.POJO.Goods;
import com.threeDays.POJO.LittleGoods;
import com.threeDays.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassNameBuyController
 * @Date2019-12-231:26
 **/
@Controller
public class BuyController {
    @Autowired
    LittleGoodsService littleGoodsService;
    @Autowired
    Sellerservice sellerservice;
    @Autowired
    CartService cartService;
    @Autowired
    CustomerService customerService;
    @Autowired
    OrderService orderService;
    @Autowired
    CartGoodsService cartGoodsService;
    @Autowired
    GoodsService goodsService;



    @RequestMapping("/product")
    public String product(Model model, BigInteger bigGoodsId, HttpServletRequest httpServletRequest){
        Goods goods=goodsService.getGoods(bigGoodsId);
        httpServletRequest.getSession().setAttribute("Goods",goods);
        return "product.html";
    }
    @RequestMapping("/directBuy")
    public String buyLittleGoods(Model model, @RequestParam("LittleGoodsId") BigInteger littleGoodsId,
                                 HttpServletRequest httpServletRequest) {
        Customer customer = (Customer) httpServletRequest.getSession().getAttribute("customer");
        float money = customer.getBalance();
        LittleGoods littleGoods = littleGoodsService.findLittleGoodsById(littleGoodsId);
        float price= littleGoods.getGoodsPrice();
        if (money<price){
            return "购买失败界面";
        }else{
            customer.setBalance(money-price);
            Map<BigInteger,Integer> littleGoodsMap=new HashMap<>();
            littleGoodsMap.put(littleGoodsId,1);
            List<LittleGoods> littleGoodsList=cartService.getSellerLittleGoods(customer.getCustomerId());
            model.addAttribute("littleGoodsId", littleGoodsId);
            model.addAttribute("sellerId", littleGoods.getSellerId());
            model.addAttribute("sellerName", sellerservice.findSellerById(littleGoods.getSellerId()).getSeller_name());
            model.addAttribute("customerId", customer.getCustomerId());
            model.addAttribute("customerName", customer.getCu_Name());
            model.addAttribute("number",littleGoodsList.size());
            orderService.CreateNewOrder(customer.getCustomerId(),littleGoodsMap);
            return "order";
        }
    }
    @RequestMapping("/buyByCart")
    public String buyByCart(HttpServletRequest httpServletRequest/*,@RequestParam("price")float price*/
                            ,@RequestParam("allLittleGoodsId[]") List<BigInteger>allLittleGoodsId) {
        Customer customer = (Customer) httpServletRequest.getSession().getAttribute("customer");
        float money = customer.getBalance();
        float price=0;
        for(BigInteger littleGoodsId:allLittleGoodsId){
            price=price+littleGoodsService.findLittleGoodsById(littleGoodsId).getGoodsPrice();
        }
        if (money<price){
            return "购买失败界面";
        }else{
            customerService.updateBalance(customer.getCustomerId(),money-price);
            cartService.deleteLittleGoods(customer.getCustomerId(),allLittleGoodsId);
            httpServletRequest.getSession().setAttribute("littleGoodsId",allLittleGoodsId);
            Map<BigInteger,Integer> littleGoodsMap=new HashMap<>();
            for(BigInteger littleId:allLittleGoodsId){
                littleGoodsMap.put(littleId,cartService.getLittleGoodsNum(customer.getCustomerId(),littleId));
            }//产生订单列表
            orderService.CreateNewOrder(customer.getCustomerId(),littleGoodsMap);
            return "购买成功界面";
        }
    }

    @RequestMapping("/buyByCart1")
    public String buy(HttpServletRequest request){
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        BigInteger cuid=customer.getCustomerId();
        List<CartGoods> cartGoodsList=cartGoodsService.findCartGoodsByCuid(cuid);
        Map</*商品id*/BigInteger,/*数量*/ Integer> Cartmap=new HashMap<>();
        for(CartGoods cartGoods:cartGoodsList){
            BigInteger id=cartGoods.getLittlegoodsid();
            int num=cartGoods.getNum();
            Cartmap.put(id,num);

        }
        orderService.CreateNewOrder(cuid,Cartmap);
        cartService.clearCart(cuid);
        return "/index";
    }


}
