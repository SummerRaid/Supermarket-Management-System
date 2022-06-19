package sms.service.impl;

import myssm.util.MapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sms.mapper.OrderMapper;
import sms.mapper.ProductMapper;
import sms.mapper.StockMapper;
import sms.pojo.Order;
import sms.pojo.Product;
import sms.pojo.Stock;
import sms.service.OrderService;
import sms.service.ProductService;

import java.util.List;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: ProductServiceImpl
 * @Description: 商品服务实现类
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/19 11:21
 */
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final StockMapper stockMapper;
    private OrderService orderService;
    private static final org.slf4j.Logger LOGGER =
            LoggerFactory.getLogger(ProductServiceImpl.class);

    public ProductServiceImpl() {
        LOGGER.debug("ProductServiceImpl 初始化中。。。");
        productMapper = MapperUtil.getProxy(ProductMapper.class);
        stockMapper = MapperUtil.getProxy(StockMapper.class);
        LOGGER.debug("ProductServiceImpl 初始化完成！");
    }

    @Override
    public Product addProduct(Product product) {
        Integer productId = productMapper.add(product);
        stockMapper.add(product.getStock());
        LOGGER.debug("添加商品 id: " + productId + " 商品名称: " + product.getName());
        return getProduct(productId);
    }

    @Override
    public Product putToRecycleBin(Integer productId) {
        Product product = getProduct(productId);
        if(product.getDeleted() == 0) {
            product.setDeleted(1);
            productMapper.update(product);
            LOGGER.debug("将商品放入回收站 id: " + productId);
        } else {
            LOGGER.debug("商品 id: " + productId + " 已经在回收站中");
        }
        return product;
    }

    @Override
    public void removeProduct(Integer productId) {
        productMapper.del(productId);
        LOGGER.debug("删除商品 id: " + productId);
        stockMapper.del(productId);
        LOGGER.debug("删除商品对应库存");
        List<Order> orders = getProduct(productId).getOrder();
        for (Order order : orders) {
            orderService.setOrderProduct(order.getId(), null);
        }
    }

    @Override
    public Product recoverProduct(Integer productId) {
        Product product = getProduct(productId);
        if(product.getDeleted() == 1) {
            product.setDeleted(0);
            productMapper.update(product);
            LOGGER.debug("将商品从回收站中取出");
        } else {
            LOGGER.debug("商品 id: " + productId + " 已经在回收站外");
        }
        return product;
    }

    @Override
    public Product saleProduct(Integer productId, Integer saleAmount) {
        LOGGER.debug("尝试卖出商品: " + productId);
        Product product = getProduct(productId);
        Stock stock = product.getStock();
        Integer originalAmount = stock.getSaleAmount();
        Integer stockAmount = stock.getStockAmount();
        if (stockAmount - originalAmount - saleAmount >= 0) {
            LOGGER.debug("商品数量合理，可以卖出");
            stock.setSaleAmount(saleAmount + originalAmount);
            product.setStock(stock);
            stockMapper.update(stock);
            LOGGER.debug("卖出" + product.getName() + saleAmount + product.getUnit());
        } else {
            LOGGER.info("商品数量不合理：\n剩余: " +
                    (stockAmount - originalAmount) +
                    product.getUnit() +
                    "尝试卖出: " + saleAmount + product.getUnit());
        }
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        Stock stock = product.getStock();
        stockMapper.update(stock);
        productMapper.update(product);
        LOGGER.debug("更新商品：" + product.getId());

        return getProduct(product.getId());
    }

    @Override
    public List<Product> getAllProducts(Integer shopId) {
        List<Product> products = productMapper.selectAll(shopId, false);
        LOGGER.debug("查询所有  不在  回收站里的商品, 超市id：" + shopId);
        return products;
    }

    @Override
    public List<Product> getAllDeleted(Integer shopId) {
        List<Product> products = productMapper.selectAll(shopId, true);
        LOGGER.debug("查询所有  在  回收站里的商品, 超市id：" + shopId);
        return products;
    }

    @Override
    public Product getProduct(Integer productId) {
        Product product = productMapper.selectById(productId);
        LOGGER.debug("查询商品：" + productId);
        return product;
    }
}
