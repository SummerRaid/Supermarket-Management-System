package sms.service.impl;

import myssm.util.CalcUtil;
import myssm.util.MapperUtil;
import org.slf4j.LoggerFactory;
import sms.mapper.OrderDetailMapper;
import sms.mapper.OrderMapper;
import sms.mapper.StockMapper;
import sms.pojo.*;
import sms.service.OrderService;
import sms.service.ProductService;
import sms.service.ShopService;
import sms.service.SupplierService;

import java.util.Date;
import java.util.List;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: OrderServiceImpl
 * @Description: 订单服务实现类
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/19 11:22
 */
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderDetailMapper orderDetailMapper;
    private final StockMapper stockMapper;
    private ShopService shopService;

    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

    private static final org.slf4j.Logger LOGGER =
            LoggerFactory.getLogger(OrderServiceImpl.class);

    public OrderServiceImpl() {
        LOGGER.debug("OrderServiceImpl 初始化中。。。");
        orderMapper = MapperUtil.getProxy(OrderMapper.class);
        stockMapper = MapperUtil.getProxy(StockMapper.class);
        orderDetailMapper = MapperUtil.getProxy(OrderDetailMapper.class);
        LOGGER.debug("OrderServiceImpl 初始化完成！");
    }

    @Override
    public List<Order> getAllOrders(Integer shopId) {
        List<Order> orders = orderMapper.selectAll(shopId);
        LOGGER.debug("查询超市的所有订单 超市id: " + shopId);
        return orders;
    }

    @Override
    public List<Order> getOrdersByUser(Integer userId) {
        List<Order> orders = orderMapper.selectByUser(userId);
        LOGGER.debug("查询用户的所有订单 用户id: " + userId);
        return orders;
    }

    @Override
    public List<Order> getOrdersBySupplier(Integer supplierId) {
        List<Order> orders = orderMapper.selectBySupplier(supplierId);
        LOGGER.debug("查询供应商的所有订单 供应商id: " + supplierId);
        return orders;
    }

    @Override
    public List<Order> getOrderByProduct(Integer productId) {
        List<Order> orders = orderMapper.selectByProduct(productId);
        LOGGER.debug("查询商品的所有订单 商品id: " + productId);
        return orders;
    }

    @Override
    public List<Order> getOrderByOrder(Order order, Integer shopId) {
        List<Order> orders = orderMapper.selectByOrder(order, shopId);
        LOGGER.debug("查询商品的所有订单 商品: " + order + "超市id: " + shopId);
        return orders;
    }

    @Override
    public Order getOrder(Integer orderId) {
        Order order = orderMapper.selectById(orderId);
        LOGGER.debug("查询订单 id: " + orderId);
        return order;
    }

    @Override
    public Order addOrder(Order order) {
        Integer id = orderMapper.add(order);
        orderDetailMapper.add(order.getOrderDetail());
        LOGGER.debug("添加新订单，订单号：" + order.getId());
        return getOrder(order.getId());
    }

    @Override
    public Order payOrder(Integer orderId) {
        Order order = getOrder(orderId);
        order.getOrderDetail().setStatus(1);
        order.getOrderDetail().setPayDate(new Date());
        orderDetailMapper.update(order.getOrderDetail());
        LOGGER.debug("订单 id: " + orderId + "已付款");

        Stock stock = order.getProduct().getStock();
        stock.setStockAmount(stock.getStockAmount() + order.getOrderDetail().getAmount());
        stockMapper.update(stock);
        LOGGER.debug("商品库存 id: " + stock.getId() + " 更新");

        Shop shop = order.getSupplier().getShop();
        shopService.addOutcome(shop.getId(), order.getOrderDetail().getPayMoney());
        return order;
    }

    @Override
    public void cancelOrder(Integer orderId) {
        LOGGER.debug("尝试撤销订单 id: " + orderId);
        Order order = getOrder(orderId);
        if(order.getOrderDetail().getStatus() == 2) {
            LOGGER.debug("订单未付款，可以撤销");
            orderDetailMapper.del(orderId);
            orderMapper.del(orderId);
            LOGGER.debug("订单撤销成功 id: " + orderId);
        } else {
            LOGGER.debug("订单已经付款，不可以撤销");
            return;
        }
    }

    @Override
    public void setOrderProduct(Integer orderId, Product product) {
        Order order = getOrder(orderId);
        order.setProduct(product);
        orderMapper.update(order);
        String result = "null";
        if (product != null) {
            result = product.getName();
        }
        LOGGER.debug("将订单 id: " + orderId + " 中的商品设置为：" + result);
    }

    @Override
    public void setOrderUser(Integer orderId, User user) {
        Order order = getOrder(orderId);
        order.setUser(user);
        orderMapper.update(order);
        String result = "null";
        if (user != null) {
            result = user.getUname();
        }
        LOGGER.debug("将订单 id: " + orderId + " 中的用户设置为：" + result);
    }

    @Override
    public void setOrderSupplier(Integer orderId, Supplier supplier) {
        Order order = getOrder(orderId);
        order.setSupplier(supplier);
        orderMapper.update(order);
        String result = "null";
        if (supplier != null) {
            result = supplier.getName();
        }
        LOGGER.debug("将订单 id: " + orderId + " 中的供应商设置为：" + result);
    }
}
