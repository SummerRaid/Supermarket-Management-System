package sms.service;

import sms.pojo.Order;
import sms.pojo.Product;
import sms.pojo.Supplier;
import sms.pojo.User;

import java.util.List;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: OrderService
 * @Description: 订单服务接口
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/16 17:58
 */
public interface OrderService {
    /**
     * @Description: 获取某超市所有订单
     * @param shopId 超市id
     * @return: java.util.List<sms.pojo.Order>
     * @Author: Zirui Qiao
     * @Date: 2022/6/17 14:59
     */
    List<Order> getAllOrders(Integer shopId);

    /**
     * @Description: 根据用户获取订单列表
     * @param userId 用户id
     * @return: java.util.List<sms.pojo.Order>
     * @Author: Zirui Qiao
     * @Date: 2022/6/19 16:13
     */
    List<Order> getOrdersByUser(Integer userId);

    /**
     * @Description: 根据供应商获取订单列表
     * @param supplierId 供应商id
     * @return: java.util.List<sms.pojo.Order>
     * @Author: Zirui Qiao
     * @Date: 2022/6/19 17:06
     */
    List<Order> getOrdersBySupplier(Integer supplierId);

    /**
     * @Description: 根据商品获取订单列表
     * @param productId 商品id
     * @return: java.util.List<sms.pojo.Order>
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 14:13
     */
    List<Order> getOrderByProduct(Integer productId);

    /**
     * @Description: 根据商品名称查询 订单
     * @param order 订单实体类
     * @param shopId 超市id
     * @return: java.util.List<sms.pojo.Order>
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 16:03
     */
    List<Order> getOrderByOrder(Order order, Integer shopId);

    /**
     * @Description: 根据订单id获取订单
     * @param orderId 订单id
     * @return: sms.pojo.Order
     * @Author: Zirui Qiao
     * @Date: 2022/6/17 14:59
     */
    Order getOrder(Integer orderId);

    /**
     * @Description: 添加订单
     * @param order 订单详细信息
     * @return: sms.pojo.Order
     * @Author: Zirui Qiao
     * @Date: 2022/6/17 14:59
     */
    Order addOrder(Order order);

    /**
     * @Description: 订单付款
     * @param orderId 订单id
     * @return: sms.pojo.Order
     * @Author: Zirui Qiao
     * @Date: 2022/6/19 17:39
     */
    Order payOrder(Integer orderId);

    /**
     * @Description: 撤销订单
     * @param orderId 订单id
     * @return: void
     * @Author: Zirui Qiao
     * @Date: 2022/6/17 14:58
     */
    void cancelOrder(Integer orderId);

    /**
     * @Description: 设置订单的商品
     * @param orderId 订单id
     * @param product 商品
     * @return: void
     * @Author: Zirui Qiao
     * @Date: 2022/6/19 16:03
     */
    void setOrderProduct(Integer orderId, Product product);

    /**
     * @Description: 设置订单用户
     * @param orderId 订单id
     * @param user 用户
     * @return: void
     * @Author: Zirui Qiao
     * @Date: 2022/6/19 16:03
     */
    void setOrderUser(Integer orderId, User user);

    /**
     * @Description: 设置订单供应商
     * @param orderId 订单id
     * @param supplier 供应商
     * @return: void
     * @Author: Zirui Qiao
     * @Date: 2022/6/19 17:02
     */
    void setOrderSupplier(Integer orderId, Supplier supplier);
}
