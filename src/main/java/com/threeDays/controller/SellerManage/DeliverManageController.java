package com.threeDays.controller.SellerManage;

import com.threeDays.POJO.Deliver;
import com.threeDays.POJO.Order;
import com.threeDays.service.DeliverService;
import com.threeDays.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.List;

/**
 * 发货管理接口
 */
@Controller
public class DeliverManageController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private DeliverService deliverService;

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
    @PostMapping("/DeliverManage/insertExpress")
    @ResponseBody
    public String insertExpress(BigInteger order_id, String express, HttpServletRequest request) {
        if (!orderService.findSellerByOrderId(order_id).equals((BigInteger) request.getSession().getAttribute("Seller_id")))
            return "不是你的订单";
        return deliverService.insertExpress(order_id, express);
    }

    /**
     * 更新物流单号
     * 返回失败或成功信息字符串
     */
    @PostMapping("/DeliverManage/updateExpress")
    @ResponseBody
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


}
