package sms.mapper;

import sms.pojo.Supplier;

import java.util.List;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: SupplierMapper
 * @Description: TODO
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/16 12:19
 */
public interface SupplierMapper {
    /**
     * @Description: 查询所有供应商
     * @return: java.util.List<sms.pojo.Supplier>
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 13:44
     */
    List<Supplier> selectAll();

    /**
     * @Description: 根据供应商id查询供应商
     * @param id 供应商id
     * @return: sms.pojo.Supplier
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 13:44
     */
    Supplier selectById(int id);

    /**
     * @Description: 添加供应商 返回id
     * @param supplier 供应商
     * @return: java.lang.Integer
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 13:45
     */
    Integer add(Supplier supplier);
}
