package sms.service;

import sms.pojo.Product;

import java.util.List;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: ProductService
 * @Description: 商品服务类
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/16 17:58
 */
public interface ProductService {
    /**
     * @Description: 添加商品
     * @param product 商品详细信息
     * @return: sms.pojo.Product
     * @Author: Zirui Qiao
     * @Date: 2022/6/17 14:28
     */
    Product addProduct(Product product);

    /**
     * @Description: 将商品放入回收站
     * @param productId 商品id
     * @return: sms.pojo.Product
     * @Author: Zirui Qiao
     * @Date: 2022/6/17 14:30
     */
    Product putToRecycleBin(Integer productId);

    /**
     * @Description: 将商品永久删除
     * @param productId 商品id
     * @return: void
     * @Author: Zirui Qiao
     * @Date: 2022/6/17 14:31
     */
    void removeProduct(Integer productId);

    /**
     * @Description: 将回收站中的商品恢复
     * @param productId 商品id
     * @return: sms.pojo.Product
     * @Author: Zirui Qiao
     * @Date: 2022/6/17 14:30
     */
    Product recoverProduct(Integer productId);

    /**
     * @Description: 卖出商品
     * @param saleAmount 卖出数量
     * @return: sms.pojo.Product
     * @Author: Zirui Qiao
     * @Date: 2022/6/17 14:33
     */
    Product saleProduct(Integer saleAmount);

    /**
     * @Description: 更新商品信息
     * @param product 商品详细信息
     * @return: sms.pojo.Product
     * @Author: Zirui Qiao
     * @Date: 2022/6/17 14:49
     */
    Product updateProduct(Product product);

    /**
     * @Description: 查询所有不在回收站里的商品
     * @param shopId 超市id
     * @return: java.util.List<sms.pojo.Product>
     * @Author: Zirui Qiao
     * @Date: 2022/6/17 14:54
     */
    List<Product> getAllProducts(Integer shopId);

    /**
     * @Description: 查询所有在回收站里的商品
     * @param shopId 超市id
     * @return: java.util.List<sms.pojo.Product>
     * @Author: Zirui Qiao
     * @Date: 2022/6/17 14:53
     */
    List<Product> getAllDeleted(Integer shopId);

    /**
     * @Description: 根据商品id查询商品
     * @param productId 商品id
     * @return: sms.pojo.Product
     * @Author: Zirui Qiao
     * @Date: 2022/6/17 14:55
     */
    Product getProduct(Integer productId);
}
