package sms.service.impl;

import myssm.util.MapperUtil;
import org.slf4j.LoggerFactory;
import sms.mapper.SupplierMapper;
import sms.pojo.Order;
import sms.pojo.Supplier;
import sms.service.OrderService;
import sms.service.SupplierService;

import java.util.List;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: SupplierServiceImpl
 * @Description: 供应商服务实现类
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/19 11:22
 */
public class SupplierServiceImpl implements SupplierService {

    private final SupplierMapper supplierMapper;
    private OrderService orderService;

    private static final org.slf4j.Logger LOGGER =
            LoggerFactory.getLogger(SupplierServiceImpl.class);

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public SupplierServiceImpl() {
        supplierMapper = MapperUtil.getProxy(SupplierMapper.class);
    }

    @Override
    public Supplier addSupplier(Supplier supplier) {
        Integer id = supplierMapper.add(supplier);
        if(supplier != null && supplier.getId() != null)
            LOGGER.info("添加供应商 id: " + supplier.getId() + " 厂商: " + supplier.getName());
        return getSupplier(supplier.getId());
    }

    @Override
    public Supplier getSupplier(Integer supplierId) {
        Supplier supplier = supplierMapper.selectById(supplierId);
        if(supplier != null && supplier.getId() != null)
            LOGGER.info("查询供应商 id: " + supplier.getId());
        return supplier;
    }

    @Override
    public List<Supplier> getAllSuppliers(Integer shopId) {
        List<Supplier> suppliers = supplierMapper.selectAll(shopId);
        LOGGER.info("查询超市 id: " + shopId + " 的所有供应商");
        return suppliers;
    }

    @Override
    public List<Supplier> getSupplierByName(String name, Integer shopId) {
        List<Supplier> suppliers = supplierMapper.selectByName(name, shopId);
        LOGGER.info("查询超市 id: " + shopId + " 中 供应商 名字: " + name);
        return suppliers;
    }

    @Override
    public Supplier updateSupplier(Supplier supplier) {
        Integer id = supplierMapper.update(supplier);
        if(supplier != null && supplier.getId() != null) {
            LOGGER.info("更新供应商 id: " + supplier.getId());
            return getSupplier(supplier.getId());
        }
        return null;
    }

    @Override
    public void delSupplier(Integer supplierId) {
        List<Order> orders = orderService.getOrdersBySupplier(supplierId);
        for (Order order : orders) {
            orderService.setOrderSupplier(order.getId(), null);
        }
        LOGGER.info("删除供应商 id: " + supplierId);
        supplierMapper.del(supplierId);
    }
}
