package sms.service.impl;

import myssm.util.CalcUtil;
import myssm.util.MapperUtil;
import org.slf4j.LoggerFactory;
import sms.mapper.ShopMapper;
import sms.pojo.Shop;
import sms.service.ShopService;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: ShopServiceImpl
 * @Description: TODO
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/19 17:47
 */
public class ShopServiceImpl implements ShopService {
    private final ShopMapper shopMapper;

    private static final org.slf4j.Logger LOGGER =
            LoggerFactory.getLogger(ShopServiceImpl.class);

    public ShopServiceImpl() {
        shopMapper = MapperUtil.getProxy(ShopMapper.class);
    }

    @Override
    public void addIncome(Integer shopId, Double income) {
        Shop shop = getShopById(shopId);
        Double oldIncome = shop.getIncome();
        Double newIncome = CalcUtil.addDoubles(oldIncome, income);
        shopMapper.updateIncome(shopId, newIncome);
        LOGGER.debug("超市 id: " + shopId + " 收入更新: " + oldIncome + "->" + newIncome);
    }

    @Override
    public void addOutcome(Integer shopId, Double outcome) {
        Shop shop = getShopById(shopId);
        Double oldOutcome = shop.getOutcome();
        Double newOutcome = CalcUtil.addDoubles(oldOutcome, outcome);
        shopMapper.updateOutcome(shopId, newOutcome);
        LOGGER.debug("超市 id: " + shopId + " 支出更新: " + oldOutcome + "->" + newOutcome);

    }

    @Override
    public Shop getShopById(Integer shopId) {
        Shop shop = shopMapper.selectById(shopId);
        LOGGER.debug("查询超市 id: " + shopId);
        return shop;
    }
}
