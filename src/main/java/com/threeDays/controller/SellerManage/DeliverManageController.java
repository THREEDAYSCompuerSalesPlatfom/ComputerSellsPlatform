package com.threeDays.controller.SellerManage;

import com.threeDays.POJO.Deliver;
import com.threeDays.POJO.Order;
import com.threeDays.Utils.Delivery.deliverPojo.deliverpojo;
import com.threeDays.Utils.Delivery.deliverPojo.lichen;
import com.threeDays.Utils.Delivery.queryDelivery;
import com.threeDays.service.DeliverService;
import com.threeDays.service.OrderService;
import com.threeDays.service.finalOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * 发货管理接口
 */
@Controller
public class DeliverManageController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private DeliverService deliverService;
    @Autowired
    private finalOrderService finalOrderService;

    /**
     * 到商品管理界面
     * （不确定是否要这个界面的网页，放着吧，不用就不用了）
     */
    @RequestMapping("/DeliverManage")
    public String DeliverManage() {
        return "redirect:/DeliverManage";
    }

    /**
     * 根据订单状态返回订单列表
     */
    @PostMapping("/DeliverManage/findOrderByStatus")
    @ResponseBody
    public List<Order> findOrderByStatus(int order_status, HttpServletRequest request) {
        BigInteger seller_id = (BigInteger) request.getSession().getAttribute("Seller_id");
        return orderService.findOrderByStatusANDSeller(order_status, seller_id);
    }

    /**
     * 根据order_id返回deliver对象
     */
    @PostMapping("/DeliverManage/findDeliverById")
    @ResponseBody
    public Deliver findDeliverById(BigInteger order_id) {
        return deliverService.findDeliverById(order_id);
    }

    /**
     * 插入物流单
     * 返回失败或成功信息字符串
     */
    @GetMapping("/DeliverManage/insertExpress")
    @ResponseBody
    public String insertExpress(BigInteger order_id, String express, HttpServletRequest request,Model model) {

         return deliverService.insertExpress(order_id, express);
    }

    /**
     * 更新物流单号
     * 返回失败或成功信息字符串
     */
    @PostMapping("/DeliverManage/updateExpress")

    public String updateExpress(BigInteger order_id, String express, HttpServletRequest request) {
        if (!orderService.findSellerByOrderId(order_id).equals((BigInteger) request.getSession().getAttribute("Seller_id")))
            return "不是你的订单";
        return deliverService.updateExpress(order_id, express);
    }

    /**
     * 删除物流单号
     * 返回失败或成功信息字符串
     */
    @PostMapping("/DeliverManage/deleteExpress")
    @ResponseBody
    public String deleteExpress(BigInteger order_id, HttpServletRequest request) {
        if (!orderService.findSellerByOrderId(order_id).equals((BigInteger) request.getSession().getAttribute("Seller_id")))
            return "不是你的订单";
        return deliverService.deleteExpress(order_id);
    }

    /**
     * 加入卖家回复
     * 返回失败或成功信息字符串
     */
    @GetMapping("/DeliverManage/insertReply")
    @ResponseBody
    public String insertReply(BigInteger order_id, String reply,HttpServletRequest request,Model model) {

        return orderService.insertReply(order_id, reply);
    }

    /**
     * 返回订单中的商品id（不包含数量）
     */
    @PostMapping("/DeliverManage/findGoodsIdByOrderId")
    @ResponseBody
    public List<BigInteger> findGoodsIdByOrderId(BigInteger order_id) {
        return orderService.findGoodsIdByOrderId(order_id);
    }

    /**
     * 返回订单中的商品id以及数量
     * 可能前端没办法在不知道key的情况下处理map中的数据，可以结合上面的方法获得key，再通过下面方法获取value
     */
    @PostMapping("/DeliverManage/findGoodsNumByOrderId")
    @ResponseBody
    public Map<BigInteger, Integer> findGoodsNumByOrderId(BigInteger order_id) {
        return orderService.findGoodsNumByOrderId(order_id);
    }

    /**
     * 查快递
     * 返回一堆json
     *示范：
     * com：
     * 中通快递	ZTO
     * 申通快递	STO
     * 圆通速递	YTO
     *
     * num：单号
     */
    @GetMapping("/DeliverManage/queryDelivery")
    public String query(String com, String num, Model model) {
        System.out.println(com+"     "+num);
        deliverpojo deliverpojo =queryDelivery.kuaidiniaoquery(com, num);
        String s=new String("");
        for(lichen r:deliverpojo.getTraces()){
            String date=r.getAcceptTime()+"\n";
            String station=r.getAcceptStation()+"\n";
            s=s+date+station;
        }
        model.addAttribute("lichenlist",deliverpojo.getTraces());
        return "kuaidi";
    }

    @RequestMapping("/deliver")
    public String getfinaldeliverbySeler(Model model,HttpServletRequest request){
        BigInteger Seller_id = (BigInteger) request.getSession().getAttribute("Seller_id");
        model.addAttribute("list",finalOrderService.getfinaldeliverbySeler(Seller_id,0));
        return "seller/deliver";
    }
    @RequestMapping("/comment")
    public String getcomment(Model model,HttpServletRequest request){
        BigInteger Seller_id = (BigInteger) request.getSession().getAttribute("Seller_id");
        model.addAttribute("list",finalOrderService.getfinaldeliverbySeler(Seller_id,2));
        return "seller/comment";
    }
    @RequestMapping("/backgoods")
    public String backgoods(Model model,HttpServletRequest request){
        BigInteger Seller_id = (BigInteger) request.getSession().getAttribute("Seller_id");
        model.addAttribute("list",finalOrderService.getfinaldeliverbySeler(Seller_id,3));
        return "seller/backgoods";
    }
}
