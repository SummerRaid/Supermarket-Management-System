package sms.service;

import sms.pojo.Order;

import java.util.List;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: OrderService
 * @Description: 订单服务类
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
     * @Description: 撤销订单
     * @param orderId 订单id
     * @return: void
     * @Author: Zirui Qiao
     * @Date: 2022/6/17 14:58
     */
    void cancelOrder(Integer orderId);
}
