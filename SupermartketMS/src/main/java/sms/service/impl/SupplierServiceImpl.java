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

    public SupplierServiceImpl() {
        supplierMapper = MapperUtil.getProxy(SupplierMapper.class);
    }

    @Override
    public Supplier addSupplier(Supplier supplier) {
        Integer id = supplierMapper.add(supplier);
        LOGGER.debug("添加供应商 id: " + supplier.getId() + " 厂商: " + supplier.getName());
        return getSupplier(id);
    }

    @Override
    public Supplier getSupplier(Integer supplierId) {
        Supplier supplier = supplierMapper.selectById(supplierId);
        LOGGER.debug("查询供应商 id: " + supplier.getId());
        return supplier;
    }

    @Override
    public List<Supplier> getAllSuppliers(Integer shopId) {
        List<Supplier> suppliers = supplierMapper.selectAll(shopId);
        LOGGER.debug("查询超市 id: " + shopId + " 的所有供应商");
        return suppliers;
    }

    @Override
    public Supplier updateSupplier(Supplier supplier) {
        Integer id = supplierMapper.update(supplier);
        LOGGER.debug("更新供应商 id: " + id);
        return getSupplier(id);
    }

    @Override
    public void delSupplier(Integer supplierId) {
        List<Order> orders = orderService.getOrdersBySupplier(supplierId);
        for (Order order : orders) {
            orderService.setOrderSupplier(order.getId(), null);
        }
        LOGGER.debug("删除供应商 id: " + supplierId);
        supplierMapper.del(supplierId);
    }
}
