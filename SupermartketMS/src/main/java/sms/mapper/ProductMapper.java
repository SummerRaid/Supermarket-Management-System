package sms.mapper;

import sms.pojo.Order;
import sms.pojo.Product;

import java.util.List;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: ProductMapper
 * @Description: TODO
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/16 12:19
 */
public interface ProductMapper {
    /**
     * @Description: 查询所有商品
     * @param shopId 超市id
     * @return: java.util.List<sms.pojo.Product>
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 12:35
     */
    List<Product> selectAll(int shopId);

    /**
     * @Description: 根据商品名称查询商品
     * @param name 商品名称
     * @return: java.util.List<sms.pojo.Product>
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 12:36
     */
    List<Product> selectByName(String name);

    /**
     * @Description: 根据订单查询商品
     * @param order 订单
     * @return: sms.pojo.Product
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 12:37
     */
    Product selectByOrder(Order order);

    /**
     * @Description: 根据商品id查询商品
     * @param id 商品id
     * @return: sms.pojo.Product
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 12:38
     */
    Product selectById(int id);

    /**
     * @Description: 添加商品返回id
     * @param product 商品(包含详细信息)
     * @return: java.lang.Integer
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 12:39
     */
    Integer add(Product product);

    /**
     * @Description: 删除商品
     * @param id 商品id
     * @return: void
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 19:03
     */
    void del(int id);

    /**
     * @Description: 更新商品信息
     * @param product 商品(包含详细信息)
     * @return: java.lang.Integer
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 19:03
     */
    Integer update(Product product);
}
