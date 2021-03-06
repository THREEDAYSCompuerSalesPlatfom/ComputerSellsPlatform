package com.threeDays.service;

import com.threeDays.POJO.BigGoods;
import com.threeDays.POJO.Order;
import com.threeDays.POJO.Ordergoods;
import com.threeDays.Utils.SortUtils.LiitleGoodsSortBySeller;
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
    @Autowired
    private LittleGoodsService littleGoodsService;

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
     * 从购物车的一切商品直接生成对应各自商家的订单
     * 每个插入新的order（默认订单状态为0）,同时往ordergoods里插入商品
     * 返回新order的order_id列表，因为可能存在多个商家的商品，会生成很多订单号
     */
    public List<BigInteger> CreateNewOrder(BigInteger cu_id, Map</*商品id*/BigInteger,/*数量*/ Integer> Cartmap) {
        Set<BigInteger> set = Cartmap.keySet();
        BigInteger[] littlegoodsidlist = (BigInteger[]) set.toArray();
        new LiitleGoodsSortBySeller().QuickSort(littlegoodsidlist, 0, littlegoodsidlist.length - 1);//通过排序让同一个商家的商品紧挨在一起
        List<BigInteger> orderidlist = new ArrayList<>();//返回的新order的order_id列表
        BigInteger sellerid = littleGoodsService.findSellerById(littlegoodsidlist[0]);
        Map<BigInteger, Integer> ordergoodsmap = new HashMap<>();//单订单中的商品信息
        for (BigInteger liitlegoodsid : littlegoodsidlist) {
            if (littleGoodsService.findSellerById(liitlegoodsid).equals(sellerid)) {
                ordergoodsmap.put(liitlegoodsid, Cartmap.get(liitlegoodsid));
            } else {
                Order order = new Order();
                order.setCu_id(cu_id);
                order.setOrder_status(0);
                BigInteger orderid = insertOrderANDGoods(order, ordergoodsmap);//插入新的order
                orderidlist.add(orderid);//在返回的list中加入新的orderid
                ordergoodsmap.clear();
            }

        }
        return orderidlist;
    }

   //下面这个别用，请用上面的方法
    public BigInteger insertOrderANDGoods(Order order, Map</*商品id*/BigInteger,/*数量*/ Integer> map) {
        if (map.isEmpty()) {
            return new BigInteger("-2");
        }
        float prize = 0;
        order.setPrize(prize);
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
            prize = prize + littleGoodsService.findLittleGoodsById(littlegoods_id).getGoodsPrice() * number;
            if (ordergoodsMapper.insertOrderGoods(ordergoods) == 0) {
                orderMapper.deleteOrder(order_id);//删除已经插入的order
                ordergoodsMapper.deleteOrderGoods(order_id);//删除已经插入的Ordergoods
                return new BigInteger("-1");
            }
        }
        orderMapper.updatePrize(order_id, prize);


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
            ordergoodsMapper.deleteOrderGoods(order_id);//删除ordergoods
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
        Ordergoods[] list = ordergoodsMapper.findOrderGoodsByOrderId(order_id);
        List<BigInteger> goods = new ArrayList<>();
        for (Ordergoods ordergoods : list) {
            BigInteger goods_id = ordergoods.getLittlegoods_id();
            goods.add(goods_id);
        }
        return goods;
    }

    /**
     * 返回订单中的商品id以及数量
     * 可能前端没办法在不知道key的情况下处理map中的数据，可以结合上面的方法获得key，再通过下面方法获取value
     */
    public Map<BigInteger, Integer> findGoodsNumByOrderId(BigInteger order_id) {
        Ordergoods[] list = ordergoodsMapper.findOrderGoodsByOrderId(order_id);
        Map<BigInteger, Integer> map = new HashMap();
        //Iterator<Ordergoods> iterator = list.iterator();
        for (Ordergoods ordergoods : list) {
            // Ordergoods ordergoods = iterator.next();
            map.put(ordergoods.getLittlegoods_id(), ordergoods.getNumber());
        }
        return map;
    }

    /**
     * 通过订单返回卖家id
     * 返回-1：无此订单
     */
    public BigInteger findSellerByOrderId(BigInteger order_id) {
        if (orderMapper.findOrderById(order_id) == null) {
            return new BigInteger("-1");
        }
        Ordergoods[] list = ordergoodsMapper.findOrderGoodsByOrderId(order_id);
        // Iterator iterator=list.iterator();
        Ordergoods ordergoods = list[0];
        System.out.println(ordergoods.getLittlegoods_id());
        BigInteger liitlegoods_id = ordergoods.getLittlegoods_id();
        return littleGoodsService.findSellerById(liitlegoods_id);

    }

    public List<Order> findOrderByStatusANDSeller(int order_status, BigInteger seller_id) {
        return orderMapper.findOrderByStatusANDSeller(order_status, seller_id);
    }
}
