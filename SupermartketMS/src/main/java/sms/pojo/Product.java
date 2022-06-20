package sms.pojo;

import java.util.List;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: Product
 * @Description: 商品实体类
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/14 15:56
 */
public class Product {
    private Integer id;    // 商品id
    private String type;  // 商品类型
    private String name;   // 商品名称
    private String unit;   // 计算商品的单位
    private String remark; // 备注
    //private List<Order> order;   // 关联商品的订单
    private Integer deleted;  // 是否被删除
    private Stock stock;      // 对应超市库存

    public Product() {
        deleted = 0;
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", remark='" + remark + '\'' +
                ", deleted=" + deleted +
                ", stock=" + stock +
                "}\n";
    }
}
