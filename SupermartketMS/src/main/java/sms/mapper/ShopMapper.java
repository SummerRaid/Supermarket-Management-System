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
     * @return: sms.pojo.Shop
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 19:08
     */
    Shop selectById(int id);
}
