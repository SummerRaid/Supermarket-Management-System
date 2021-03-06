package sms.pojo;

import java.util.Date;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: OrderDetail
 * @Description: 订单详情实体类
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/17 18:15
 */
public class OrderDetail {
    private Integer id;       // 订单详情id
    private Double payMoney;  // 支付金额
    private Date payDate;     // 支付日期
    private Integer status;   // 支付状态 [1:支付，2:未支付]
    private Date createDate;  // 订单创建时间
    private String remark;    // 订单备注
    private Integer amount;   // 订单的商品数量
    private Double price;     // 订单的商品价格

    public OrderDetail() {
    }

    public OrderDetail(Double payMoney, Integer status, Date createDate, String remark, Integer amount, Double price) {
        this.payMoney = payMoney;
        this.status = status;
        this.createDate = createDate;
        this.remark = remark;
        this.amount = amount;
        this.price = price;
    }

    public OrderDetail(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", payMoney=" + payMoney +
                ", payDate=" + payDate +
                ", status=" + status +
                ", createDate=" + createDate +
                ", remark='" + remark + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}
