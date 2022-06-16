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
    private User user;        // 创建订单的用户
    private Product product;  // 订单关联的商品
    private Supplier supplier;// 订单的供应商
    private String orderNO;   // 订单号(全球唯一码)
    private Double payMoney;  // 支付金额
    private Date payDate;     // 支付日期
    private Integer status;   // 支付状态
    private Date createDate;  // 订单创建时间
    private String remark;    // 订单备注
    private Integer amount;   // 订单的商品数量
    private Double price;     // 订单的商品价格

    public Order() {
    }

    public Order(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getOrderNO() {
        return orderNO;
    }

    public void setOrderNO(String orderNO) {
        this.orderNO = orderNO;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
