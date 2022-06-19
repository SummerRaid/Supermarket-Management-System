package sms.service;

import sms.pojo.Shop;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: ShopService
 * @Description: TODO
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/19 17:45
 */
public interface ShopService {
    /**
     * @Description: 更新超市的收入
     * @param shopId 超市id
     * @param income 超市新收入
     * @return: void
     * @Author: Zirui Qiao
     * @Date: 2022/6/19 17:46
     */
    void addIncome(Integer shopId, Double income);

    /**
     * @Description: 更新超市的支出
     * @param shopId 超市id
     * @param outcome 超市新支出
     * @return: void
     * @Author: Zirui Qiao
     * @Date: 2022/6/19 17:46
     */
    void addOutcome(Integer shopId, Double outcome);

    /**
     * @Description: 根据id获取超市
     * @param shopId 超市id
     * @return: sms.pojo.Shop
     * @Author: Zirui Qiao
     * @Date: 2022/6/19 17:57
     */
    Shop getShopById(Integer shopId);
}
