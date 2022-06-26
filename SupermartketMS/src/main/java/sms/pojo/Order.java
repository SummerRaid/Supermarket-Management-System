package sms.pojo;

import java.util.Date;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: Order
 * @Description: 订单实体类
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/14 15:59
 */
public class Order {
    private Integer id;       // 订单id
    private String productName; // 商品名称
    private String orderNO;   // 订单号(全球唯一码)
    private Supplier supplier;// 订单的供应商
    private User user;        // 创建订单的用户
    private Product product;  // 订单关联的商品
    private OrderDetail orderDetail; // 订单详情

    public Order() {
    }

    public Order(Integer id) {
        this.id = id;
    }

    public Order(String productName) {
        this.productName = productName;
    }

    public Order(String productName, String orderNO, Supplier supplier, User user, Product product) {
        this.productName = productName;
        this.orderNO = orderNO;
        this.supplier = supplier;
        this.user = user;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOrderNO() {
        return orderNO;
    }

    public void setOrderNO(String orderNO) {
        this.orderNO = orderNO;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", orderNO='" + orderNO + '\'' +
                ", supplier=" + supplier +
                ", user=" + user +
                ", product=" + product +
                ", orderDetail=" + orderDetail +
                '}';
    }
}
