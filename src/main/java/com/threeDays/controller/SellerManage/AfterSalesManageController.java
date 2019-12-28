package com.threeDays.controller.SellerManage;

import com.threeDays.POJO.AfterSales;
import com.threeDays.service.AfterSalesService;
import com.threeDays.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.List;

/**
 * 售后管理
 */
@Controller
public class AfterSalesManageController {
    @Autowired
    private AfterSalesService afterSalesService;
    @Autowired
    private OrderService orderService;

    /**
     * 到商品管理界面
     * （不确定是否要这个界面的网页，放着吧，不用就不用了）
     */
    @RequestMapping("/AfterSales")
    public String AfterSales() {
        return "redirect:/AfterSales";
    }


    /**
     * 通过商家id返回售后表
     */
    @PostMapping("/AfterSales/findALLAftersales")
    @ResponseBody
    public List<AfterSales> findALLAftersales(HttpServletRequest request) {
        BigInteger seller_id = (BigInteger) request.getSession().getAttribute("Seller_id");
        return afterSalesService.findAftersalesBySellerId(seller_id);
    }

    /**
     * 更新售后状态，成功1，失败0
     */
    @GetMapping("/AfterSales/changeAfterSalesStatus")
    @ResponseBody
    public String changeAfterSalesStatus(BigInteger after_id, int status, HttpServletRequest request) {
        BigInteger seller_id = (BigInteger) request.getSession().getAttribute("Seller_id");
        if (!afterSalesService.findAfterSalesById(after_id).getSeller_id().equals(seller_id)) {
            return "非所属售后id";
        }
        orderService.changeStatus(afterSalesService.findAfterSalesById(after_id).getOrder_id(),status);
        return afterSalesService.changeAfterSalesStatus(after_id, status);
    }

    /**
     * 更新卖家理由
     */
    @GetMapping("/AfterSales/updateSellerExcuse")
    @ResponseBody
    public String updateSellerExcuse(BigInteger after_id, String seller_excuse, HttpServletRequest request) {
        BigInteger seller_id = (BigInteger) request.getSession().getAttribute("Seller_id");
        AfterSales afterSales = afterSalesService.findAfterSalesById(after_id);
        if (!afterSales.getSeller_id().equals(seller_id)) {
            return "非所属售后id";
        }
        BigInteger orderid = afterSales.getOrder_id();
        return afterSalesService.updateSellerExcuse(orderid, seller_excuse);
    }

}
