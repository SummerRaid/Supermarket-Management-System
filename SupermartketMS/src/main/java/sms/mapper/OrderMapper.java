package sms.mapper;

import sms.pojo.Order;
import sms.pojo.Product;
import sms.pojo.Supplier;
import sms.pojo.User;

import java.util.List;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: OrderMapper
 * @Description: 订单映射接口
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/16 12:19
 */
public interface OrderMapper {
    /**
     * @Description: 查询所有订单
     * @param shopId 超市id
     * @return: java.util.List<sms.pojo.Order>
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 12:28
     */
    List<Order> selectAll(int shopId);

    /**
     * @Description: 查询某用户创建的所有订单
     * @param userId 某用户id
ma     * @return: java.util.List<sms.pojo.Order> 精确
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 12:29
     */
    List<Order> selectByUser(int userId);

    /**
     * @Description: 查询某商品的所有订单
     * @param productId 某商品id
     * @return: java.util.List<sms.pojo.Order> 精确
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 12:30
     */
    List<Order> selectByProduct(int productId);

    /**
     * @Description: 查询某供应商的所有订单
     * @param supplierId 某供应商id
     * @return: java.util.List<sms.pojo.Order> 精确
     * @Author: Zirui Qiao
     * @Date: 2022/6/17 18:32
     */
    List<Order> selectBySupplier(int supplierId);

    /**
     * @Description: 根据商品名称 和 超市id 查询 订单
     * @param order 订单实体类
     * @param shopId 超市订单
     * @return: java.util.List<sms.pojo.Order> 模糊
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 16:02
     */
    List<Order> selectByOrder(Order order, Integer shopId);

    /**
     * @Description: 根据id查询订单
     * @param id 订单id
     * @return: sms.pojo.Order 精确
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 12:31
     */
    Order selectById(int id);

    /**
     * @Description: 添加订单返回id
     * @param order 订单(包含详细信息)
     * @return: java.lang.Integer
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 12:32
     */
    Integer add(Order order);

    /**
     * @Description: 删除订单
     * @param id 订单id
     * @return: void
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 19:05
     */
    void del(int id);

    /**
     * @Description: 更新订单信息
     * @param order 订单(包含详细信息)
     * @return: java.lang.Integer
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 19:06
     */
    Integer update(Order order);
}
