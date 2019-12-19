package com.threeDays.service;

import com.threeDays.POJO.BigGoods;
import com.threeDays.POJO.Order;
import com.threeDays.POJO.Ordergoods;
import com.threeDays.dao.OrderMapper;
import com.threeDays.dao.OrdergoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrdergoodsMapper ordergoodsMapper;
    @Autowired
    private DeliverService deliverService;

    /**
     * 根据order_id返回Order对象
     * 查不到返回null
     **/
    public Order findOrderById(BigInteger order_id) {
        return orderMapper.findOrderById(order_id);
    }


    //！！！！！！！！！！！！！！旧的！！！！！！！！！！别用!!!!!!!!!!!!!!!!!!!!!!!

    /**
     * 插入新的order
     * 返回新order的order_id
     * 查不到返回null
     * 插入失败返回-1
     */
    @Deprecated//已经废弃
    public BigInteger insertAllOrder(Order order) {
        if (orderMapper.insertOrder(order) == 0) {
            return new BigInteger("-1");
        }
        return order.getOrder_id();
    }
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    /**
     * 插入新的order
     * 返回新order的order_id
     * 查不到返回null
     * 插入失败返回-1
     * map中没商品返回-2
     */
    public BigInteger insertOrder(Order order, Map</*商品id*/BigInteger,/*数量*/ Integer> map) {
        if (map.isEmpty()) {
            return new BigInteger("-2");
        }
        if (orderMapper.insertOrder(order) == 0) {
            return new BigInteger("-1");
        }
        BigInteger order_id = order.getOrder_id();

        Set keySet = map.keySet();
        Iterator iterator = keySet.iterator();
        while (iterator.hasNext()) {
            BigInteger littlegoods_id = (BigInteger) iterator.next();
            int number = map.get(littlegoods_id);
            Ordergoods ordergoods = new Ordergoods(order_id, littlegoods_id, number);
            if (ordergoodsMapper.insertOrderGoods(ordergoods) == 0) {
                orderMapper.deleteOrder(order_id);//删除已经插入的order
                ordergoodsMapper.deleteOrderGoods(order_id);//删除已经插入的Ordergoods
                return new BigInteger("-1");
            }
        }

        return order.getOrder_id();
    }

    /**
     * !!!!!!!!!!!//不建议使用，订单已经生成，只有评价和回复可以改，更改方法已经另外提供
     * 更新订单信息
     */
    public String updateOrder(Order order) {
        if (order.getOrder_id() == null) {//order对象中order_id没有初始化
            return "没有填写order_id";
        }
        if (orderMapper.findOrderById(order.getOrder_id()) == null) {
            return "没有此订单";
        }
        if (orderMapper.updateOrder(order) == 0) {
            return "失败，数据库错误";
        } else {
            return "更新成功";
        }
    }

    /**
     * 插入买家评价
     */
    public String insertComment(BigInteger order_id, String commment) {
        if (orderMapper.findOrderById(order_id) == null) {
            return "没有此订单";
        }
        if (commment.equals(""))
            return "评论不准为空";
        if (orderMapper.insertComment(order_id, commment) == 0) {
            return "失败，数据库错误";
        } else {
            return "更新成功";
        }
    }

    /**
     * 插入卖家回复
     */
    public String insertReply(BigInteger order_id, String reply) {
        if (orderMapper.findOrderById(order_id) == null) {
            return "没有此订单";
        }
        if (orderMapper.findOrderById(order_id).getComments() == null) {
            return "买家暂未评价，无法回复";
        }
        if (orderMapper.insertReply(order_id, reply) == 0) {
            return "失败，数据库错误";
        } else {
            return "更新成功";
        }
    }

    /**
     * ！！！！！！！！！别删除订单了！！！！！！！！方法是错的，Oredergoods的表未删除
     * ！！！！！！！！！先放着吧这方法，应该用不着！！！！！删除后评价就没了
     * 删除订单
     * 订单状态为6（卖家已经退货）时，买家可以删除订单
     */

    public String deleteOrder(BigInteger order_id) {
        Order order = orderMapper.findOrderById(order_id);
        if (order == null) {
            return "没有此订单";
        }
        if (order.getOrder_status() != 6)
            return "不符合删除订单条件";
        if (orderMapper.deleteOrder(order_id) == 0) {
            return "失败，数据库错误";
        } else {
            deliverService.deleteExpress(order_id);//删除发货单号
            return "删除成功";
        }

    }

    /**
     * 更改订单状态
     */
    public String changeStatus(BigInteger order_id, int order_status) {
        if (order_id == null) {
            return "没有此订单";
        }
        if (order_status < 1 || order_status > 6)
            return "状态码输入无效";
        if (orderMapper.changeStatus(order_id, order_status) == 0) {
            return "失败，数据库错误";
        } else {
            return "更改成功";
        }
    }

    /**
     * 返回订单中的商品id（不包含数量）
     */
    public List<BigInteger> findGoodsIdByOrderId(BigInteger order_id) {
        List<Ordergoods> list = ordergoodsMapper.findOrderGoodsByOrderId(order_id);
        List<BigInteger> goods = new ArrayList<>();
        for (Ordergoods ordergoods : list) {
            BigInteger goods_id = ordergoods.getLittlegoods_id();
            goods.add(goods_id);
        }
        return goods;
    }

    /**
     * 返回订单中的商品id以及数量
     */
    public Map<BigInteger, Integer> findGoodsNumByOrderId(BigInteger order_id) {
        List<Ordergoods> list = ordergoodsMapper.findOrderGoodsByOrderId(order_id);
        Map<BigInteger, Integer> map = new HashMap();
        Iterator<Ordergoods> iterator = list.iterator();
        while (iterator.hasNext()) {
            Ordergoods ordergoods = iterator.next();
            map.put(ordergoods.getLittlegoods_id(), ordergoods.getNumber());
        }
        return map;
    }
}
