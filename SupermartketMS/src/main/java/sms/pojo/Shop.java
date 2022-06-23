package sms.pojo;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: shop
 * @Description: 商店实体类
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/14 15:52
 */
public class Shop {
    private Integer id;
    private Double income;  // 总收入
    private Double outcome; // 总花销

    public Shop() {
    }

    public Shop(Integer id) {
        this.id = id;
    }

    public Shop(Double income, Double outcome) {
        this.income = income;
        this.outcome = outcome;
    }

    public Shop(Integer id, Double income, Double outcome) {
        this.id = id;
        this.income = income;
        this.outcome = outcome;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getOutcome() {
        return outcome;
    }

    public void setOutcome(Double outcome) {
        this.outcome = outcome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", income=" + income +
                ", outcome=" + outcome +
                '}';
    }
}
