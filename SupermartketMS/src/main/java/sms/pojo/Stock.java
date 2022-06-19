package sms.pojo;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: Stock
 * @Description: 超市库存实体类
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/17 18:20
 */
public class Stock {
    private Integer id;          // 商品id
    private Double salePrice;    // 出售价
    private Integer saleAmount;  // 销售数量
    private Shop shop;           // 对应的超市
    private Integer stockAmount; // 库存数量

    public Stock() {
    }

    public Stock(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
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

    public Integer getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(Integer stockAmount) {
        this.stockAmount = stockAmount;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", salePrice=" + salePrice +
                ", saleAmount=" + saleAmount +
                ", shop=" + shop +
                ", stockAmount=" + stockAmount +
                '}';
    }
}
