package sms.service;

import sms.pojo.Role;

import java.util.List;
import java.util.Map;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: RoleService
 * @Description: 角色服务接口
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/16 17:58
 */
public interface RoleService {
    /**
     * @Description: 获取某超市的所有角色
     * @param shopId 超市id
     * @return: java.util.List<sms.pojo.Role>
     * @Author: Zirui Qiao
     * @Date: 2022/6/17 11:35
     */
    List<Role> getAllRoles(Integer shopId);

    /**
     * @Description: 根据角色id获取角色
     * @param roleId 角色id
     * @return: sms.pojo.Role
     * @Author: Zirui Qiao
     * @Date: 2022/6/17 11:36
     */
    Role getRole(Integer roleId);

    /**
     * @Description: 增加角色
     * @param role 角色详细信息
     * @return: sms.pojo.Role
     * @Author: Zirui Qiao
     * @Date: 2022/6/17 11:37
     */
    Role addRole(Role role);

    /**
     * @Description: 删除角色
     * @param roleId 角色id
     * @return: void
     * @Author: Zirui Qiao
     * @Date: 2022/6/17 11:38
     */
    void delRole(Integer roleId);

    /**
     * @Description: 对角色进行授权
     * @param auth 权限map
     * @param roleId 角色id
     * @return: sms.pojo.Role
     * @Author: Zirui Qiao
     * @Date: 2022/6/17 11:40
     */
    Role authorize(Map<String, Boolean> auth, Integer roleId);
}
