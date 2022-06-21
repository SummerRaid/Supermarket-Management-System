package sms.mapper;

import sms.pojo.Shop;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: ShopMapper
 * @Description: 超市映射接口
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/16 12:19
 */
public interface ShopMapper {
    /**
     * @Description: 根据超市id查询
     * @param id 超市id
     * @return: sms.pojo.Shop 精确
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 19:08
     */
    Shop selectById(int id);

    /**
     * @Description: 更新超市的收入
     * @param income 超市收入
     * @param shopId 超市id
     * @return: void
     * @Author: Zirui Qiao
     * @Date: 2022/6/19 17:25
     */
    void updateIncome(Integer shopId, Double income);

    /**
     * @Description: 更新超市支出
     * @param outcome 超市支出
     * @param shopId 超市id
     * @return: void
     * @Author: Zirui Qiao
     * @Date: 2022/6/19 17:26
     */
    void updateOutcome(Integer shopId, Double outcome);
}
