package sms.pojo;

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
    private Integer model; // 商品型号
    private Integer type;  // 商品类型
    private String name;   // 商品名称
    private String unit;   // 计算商品的单位
    private Double salePrice; // 出售价
    private String remark; // 备注
    private Order order;   // 关联商品的订单
    private Integer saleAmount; // 销售数量
    private Shop shop;        // 对应的超市
    private Integer deleted;  // 是否被删除

    public Product() {}

    public Product(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
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

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(Integer saleAmount) {
        this.saleAmount = saleAmount;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
