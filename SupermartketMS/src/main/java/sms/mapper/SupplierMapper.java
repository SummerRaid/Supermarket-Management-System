package sms.mapper;

import sms.pojo.Supplier;

import java.util.List;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: SupplierMapper
 * @Description: 供应商映射接口
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/16 12:19
 */
public interface SupplierMapper {
    /**
     * @Description: 查询所有供应商
     * @param shopId 超市id
     * @return: java.util.List<sms.pojo.Supplier>
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 13:44
     */
    List<Supplier> selectAll(int shopId);

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
     * @param supplier 供应商(包含详细信息)
     * @return: java.lang.Integer
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 13:45
     */
    Integer add(Supplier supplier);

    /**
     * @Description: 删除供应商
     * @param id 供应商id
     * @return: void
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 18:45
     */
    void del(int id);

    /**
     * @Description: 更新供应商信息
     * @param supplier 供应商(包含详细信息)
     * @return: java.lang.Integer
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 18:46
     */
    Integer update(Supplier supplier);
}
