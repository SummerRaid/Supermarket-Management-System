package sms.controller;

import myssm.util.StringUtil;
import sms.pojo.*;
import sms.service.OrderService;
import sms.service.ProductService;
import sms.service.SupplierService;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: OrderController
 * @Description: 订单控制器
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/20 13:14
 */
public class OrderController {
    private OrderService orderService;
    private SupplierService supplierService;
    private ProductService productService;

    /**
     * @Description: 订单主页面处理器
     * @return: java.lang.String
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 15:23
     */
    public String index() {
        // 跳转到 orderPage.html
        return "orderPage";
    }

    /**
     * @Description: 获取当前超市的所有订单
     * @param session session
     * @return: java.lang.String
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 16:53
     */
    public String getAllOrders(HttpSession session) {
        Integer shopId = (Integer) session.getAttribute("shopId");
        List<Order> allOrders = orderService.getAllOrders(shopId);

        return "json:" + StringUtil.toJsonString(allOrders);
    }

    /**
     * @Description: 获取当前用户的所有订单
     * @param session session
     * @return: java.lang.String
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 16:52
     */
    public String getMyOrders(HttpSession session) {
        User user = (User) session.getAttribute("currUser");
        if (user != null) {
            List<Order> orders = orderService.getOrdersByUser(user.getId());
            return "json:" + StringUtil.toJsonString(orders);
        }
        return "";
    }

    /**
     * @Description: 搜索订单
     * @param name 商品名称 或 供应商名字 或 备注名称
     * @param session session
     * @return: java.lang.String
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 16:52
     */
    public String searchOrders(String name, HttpSession session) {
        Integer shopId = (Integer) session.getAttribute("shopId");
        List<Order> orders = new ArrayList<>();
        Order order1 = new Order();
        order1.setProductName(name);
        List<Order> orderList = orderService.getOrderByOrder(order1, shopId);
        if (orderList != null && orderList.size() > 0) {
            orders = orderList;
        }
        List<Supplier> suppliers = supplierService.getSupplierByName(name, shopId);
        if (suppliers != null && suppliers.size() > 0) {
            for (Supplier supplier : suppliers) {
                orders.addAll(orderService.getOrdersBySupplier(supplier.getId()));
            }
        }
        Order order2 = new Order();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setRemark(name);
        order2.setOrderDetail(orderDetail);
        orderList = orderService.getOrderByOrder(order2, shopId);
        if (orderList != null && orderList.size() > 0) {
            orders.addAll(orderList);
        }

        return "json:" + StringUtil.toJsonString(orders);
    }

    /**
     * @Description: 添加订单
     * @param productId 商品id
     * @param supplierId 供应商id
     * @param payMoney 支付金额
     * @param remark 备注
     * @param amount 商品数量
     * @param price 进货价格
     * @param session session
     * @return: java.lang.String
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 16:50
     */
    public String addOrder(Integer productId, Integer supplierId,
                           Double payMoney, String remark, Integer amount,
                           Double price, HttpSession session) {
        User user = (User) session.getAttribute("currUser");
        Order order = new Order();
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        order.setOrderNO(UUID.randomUUID()+"_"+sdf.format(now));// 全球唯一码
        order.setProduct(new Product(productId));
        order.setSupplier(new Supplier(supplierId));
        order.setUser(user);
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setPayMoney(payMoney);
        orderDetail.setStatus(2);
        orderDetail.setCreateDate(now);
        orderDetail.setRemark(remark);
        orderDetail.setAmount(amount);
        orderDetail.setPrice(price);
        order.setOrderDetail(orderDetail);
        Product product = productService.getProduct(productId);
        order.setProductName(product.getName());

        orderService.addOrder(order);

        return "";
    }

    /**
     * @Description: 取消订单
     * @param orderId 订单号
     * @return: java.lang.String
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 16:50
     */
    public String cancelOrder(Integer orderId) {
        orderService.cancelOrder(orderId);

        return "";
    }

    /**
     * @Description: 支付订单
     * @param orderId 订单号
     * @return: java.lang.String
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 16:50
     */
    public String payOrder(Integer orderId) {
        orderService.payOrder(orderId);

        return "";
    }
}
