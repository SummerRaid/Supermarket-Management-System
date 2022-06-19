package sms.service;

import sms.pojo.Shop;
import sms.pojo.Supplier;

import java.util.List;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: SupplierService
 * @Description: 供应商服务接口
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/16 17:59
 */
public interface SupplierService {
    /**
     * @Description: 添加供应商
     * @param supplier 供应商详细信息
     * @return: sms.pojo.Supplier
     * @Author: Zirui Qiao
     * @Date: 2022/6/17 11:27
     */
    Supplier addSupplier(Supplier supplier);

    /**
     * @Description: 根据 供应商id 查询供应商
     * @param supplierId 供应商id
     * @return: sms.pojo.Supplier
     * @Author: Zirui Qiao
     * @Date: 2022/6/17 11:28
     */
    Supplier getSupplier(Integer supplierId);

    /**
     * @Description: 获取某超市所有的供应商
     * @param shopId 超市id
     * @return: java.util.List<sms.pojo.Supplier>
     * @Author: Zirui Qiao
     * @Date: 2022/6/17 11:30
     */
    List<Supplier> getAllSuppliers(Integer shopId);

    /**
     * @Description: 更新供应商信息
     * @param supplier 供应商详细信息
     * @return: sms.pojo.Supplier
     * @Author: Zirui Qiao
     * @Date: 2022/6/17 11:33
     */
    Supplier updateSupplier(Supplier supplier);

    /**
     * @Description: 根据 供应商id 删除供应商
     * @param supplierId 供应商id
     * @return: void
     * @Author: Zirui Qiao
     * @Date: 2022/6/17 11:33
     */
    void delSupplier(Integer supplierId);
}
