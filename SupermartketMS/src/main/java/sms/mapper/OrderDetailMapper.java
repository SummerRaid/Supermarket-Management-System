package sms.mapper;

import sms.pojo.*;

import java.util.List;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: OrderDetailMapper
 * @Description: 订单详情映射接口
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/17 18:42
 */
public interface OrderDetailMapper {

    /**
     * @Description: 根据id查询订单
     * @param id 订单id
     * @return: sms.pojo.OrderDetail
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 12:31
     */
    OrderDetail selectById(int id);

    /**
     * @Description: 添加订单详情返回id
     * @param orderDetail 订单详情(包含详细信息)
     * @return: java.lang.Integer
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 12:32
     */
    Integer add(OrderDetail orderDetail);

    /**
     * @Description: 删除订单详情
     * @param id 订单id
     * @return: void
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 19:05
     */
    void del(int id);

    /**
     * @Description: 更新订单详情
     * @param orderDetail 订单详情(包含详细信息)
     * @return: java.lang.Integer
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 19:06
     */
    Integer update(OrderDetail orderDetail);
}
