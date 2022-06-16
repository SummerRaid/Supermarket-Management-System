package sms.mapper;

import sms.pojo.Order;
import sms.pojo.Product;
import sms.pojo.User;

import java.util.List;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: OrderMapper
 * @Description: TODO
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/16 12:19
 */
public interface OrderMapper {
    /**
     * @Description: 查询所有订单
     * @return: java.util.List<sms.pojo.Order>
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 12:28
     */
    List<Order> selectAll();

    /**
     * @Description: 查询某用户创建的所有订单
     * @param user 某用户
     * @return: java.util.List<sms.pojo.Order>
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 12:29
     */
    List<Order> selectByUser(User user);

    /**
     * @Description: 查询某商品的所有订单
     * @param product 某商品
     * @return: java.util.List<sms.pojo.Order>
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 12:30
     */
    List<Order> selectByProduct(Product product);

    /**
     * @Description: 根据id查询订单
     * @param id 订单id
     * @return: sms.pojo.Order
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 12:31
     */
    Order selectById(int id);

    /**
     * @Description: 添加订单返回id
     * @param order 订单
     * @return: java.lang.Integer
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 12:32
     */
    Integer add(Order order);
}
