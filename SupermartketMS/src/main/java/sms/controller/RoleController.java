package sms.controller;

import myssm.util.StringUtil;
import sms.pojo.Role;
import sms.pojo.Shop;
import sms.pojo.User;
import sms.service.RoleService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: RoleController
 * @Description: TODO
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/20 18:19
 */
public class RoleController {

    private RoleService roleService;

    /**
     * @Description: 添加新角色
     * @param name 角色名称
     * @param remark 备注
     * @param session session 用于获取shopId
     * @return: java.lang.String
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 18:30
     */
    public String addRole(String name, String remark, HttpSession session) {
        Integer shopId = (Integer) session.getAttribute("shopId");
        Role role = new Role(name, remark, "000000000", new Shop(shopId));

        roleService.addRole(role);

        return "";
    }

    /**
     * @Description: 删除角色
     * @param roleId 角色id
     * @param session session 用于获取当前用户
     * @return: java.lang.String
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 18:34
     */
    public String delRole(Integer roleId, HttpSession session) {
        User currUser = (User) session.getAttribute("currUser");
        if(currUser.getRole().getId() != roleId) {
            roleService.delRole(roleId);   // 删除当前角色会bug
        }

        return "";
    }

    /**
     * @Description: 更改角色权限
     * @param roleId 角色id
     * @param power 权限 TODO
     * @return: java.lang.String
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 18:37
     */
    public String editRole(Integer roleId, String power) {
        roleService.authorize(new HashMap<>(), roleId);

        return "";
    }

    /**
     * @Description: 获取当前超市的所有角色
     * @param session session 用于获取超市id
     * @return: java.lang.String
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 18:38
     */
    public String getAllRoles(HttpSession session) {
        Integer shopId = (Integer) session.getAttribute("shopId");
        List<Role> allRoles = roleService.getAllRoles(shopId);

        return "json:" + StringUtil.toJsonString(allRoles);
    }
}
