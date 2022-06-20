package sms.controller;

import myssm.util.StringUtil;
import sms.pojo.Shop;
import sms.pojo.Supplier;
import sms.service.SupplierService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: SupplierController
 * @Description: 供应商控制器
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/20 18:41
 */
public class SupplierController {
    private SupplierService supplierService;

    /**
     * @Description: 添加新供应商
     * @param address 地址
     * @param name 名称
     * @param contactPerson 联系人
     * @param contact 联系电话
     * @param remark 备注
     * @param session session 用于获取超市id
     * @return: java.lang.String
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 18:46
     */
    public String addSupplier(String address, String name, String contactPerson, String contact,
                              String remark, HttpSession session){
        Integer shopId = (Integer) session.getAttribute("shopId");
        Supplier supplier = new Supplier(address, name, contactPerson, contact, remark, new Shop(shopId));

        supplierService.addSupplier(supplier);

        return "";
    }

    /**
     * @Description: 删除供应商
     * @param id 供应商id
     * @return: java.lang.String
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 18:47
     */
    public String delSupplier(Integer id) {
        supplierService.delSupplier(id);

        return "";
    }

    /**
     * @Description: 修改供应商
     * @param id 供应商id
     * @param address 地址
     * @param name 名称
     * @param contactPerson 联系人
     * @param contact 联系电话
     * @param remark 备注
     * @param session session 用于获取超市id
     * @return: java.lang.String
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 18:46
     */
    public String editSupplier(Integer id, String address, String name, String contactPerson,
                               String contact, String remark, HttpSession session) {
        Integer shopId = (Integer) session.getAttribute("shopId");
        Supplier supplier = new Supplier(address, name, contactPerson, contact, remark, new Shop(shopId));
        supplier.setId(id);

        supplierService.updateSupplier(supplier);

        return "";
    }

    /**
     * @Description: 获取当前超市所有供应商
     * @param session session 用于获取超市id
     * @return: java.lang.String
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 18:49
     */
    public String getAllSupplier(HttpSession session) {
        Integer shopId = (Integer) session.getAttribute("shopId");
        List<Supplier> allSuppliers = supplierService.getAllSuppliers(shopId);

        return "json:" + StringUtil.toJsonString(allSuppliers);
    }

    /**
     * @Description: 根据供应商名称 获取当前超市供应商
     * @param session session 用于获取超市id
     * @return: java.lang.String
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 18:49
     */
    public String getSupplierByName(String name, HttpSession session) {
        Integer shopId = (Integer) session.getAttribute("shopId");
        List<Supplier> allSuppliers = supplierService.getSupplierByName(name, shopId);

        return "json:" + StringUtil.toJsonString(allSuppliers);
    }
}
