package sms.mapper;

import sms.pojo.Role;

import java.util.List;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: RoleMapper
 * @Description: TODO
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/16 12:19
 */
public interface RoleMapper {
    /**
     * @Description: 查询所有角色
     * @return: java.util.List<sms.pojo.Role>
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 13:37
     */
    List<Role> selectAll();

    /**
     * @Description: 根据角色id查询角色
     * @param id 角色id
     * @return: sms.pojo.Role
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 13:38
     */
    Role selectById(int id);

    /**
     * @Description: 添加角色 返回id
     * @param role 角色
     * @return: java.lang.Integer
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 13:40
     */
    Integer add(Role role);
}
