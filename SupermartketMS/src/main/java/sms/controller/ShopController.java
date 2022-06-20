package sms.controller;

import myssm.util.StringUtil;
import sms.pojo.Shop;
import sms.service.ShopService;

import javax.servlet.http.HttpSession;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: ShopController
 * @Description: 超市控制器
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/20 18:52
 */
public class ShopController {
    private ShopService shopService;

    public String getShop(HttpSession session) {
        Integer shopId = (Integer) session.getAttribute("shopId");
        Shop shop = shopService.getShopById(shopId);

        return "json:" + StringUtil.toJsonString(shop);
    }
}
