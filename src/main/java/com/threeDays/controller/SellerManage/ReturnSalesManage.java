package com.threeDays.controller.SellerManage;

import com.threeDays.POJO.Order;
import com.threeDays.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.List;

@Controller
public class ReturnSalesManage {
    @Autowired
    private OrderService orderService;

    /**
     * 根据订单状态返回订单列表
     * 买家申请退货状态码3
     */
    @PostMapping("/ReturnSalesManage/findOrderByStatus")
    @ResponseBody
    public List<Order> findOrderByStatus(int order_status, HttpServletRequest request) {
        BigInteger seller_id = (BigInteger) request.getSession().getAttribute("Seller_id");
        return orderService.findOrderByStatusANDSeller(order_status, seller_id);
    }

    /**
     * 改变订单的状态
     * 状态码：3退货申请中 4卖家同意退货 5卖家不同意退货6买家已退货
     */
    @PostMapping("/ReturnSalesManage/changeStatus")
    @ResponseBody
    public String changeStatus(BigInteger order_id, int order_status) {
        return orderService.changeStatus(order_id, order_status);
    }
}
