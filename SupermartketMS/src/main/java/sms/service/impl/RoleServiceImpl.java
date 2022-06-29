package sms.service.impl;

import myssm.util.MapperUtil;
import org.slf4j.LoggerFactory;
import sms.mapper.RoleMapper;
import sms.pojo.Role;
import sms.pojo.User;
import sms.service.RoleService;
import sms.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: RoleServiceImpl
 * @Description: 角色服务实现类
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/19 11:22
 */
public class RoleServiceImpl implements RoleService {
    private final RoleMapper roleMapper;
    private UserService userService;

    private static final org.slf4j.Logger LOGGER =
            LoggerFactory.getLogger(RoleServiceImpl.class);

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public RoleServiceImpl() {
        roleMapper = MapperUtil.getProxy(RoleMapper.class);
    }

    @Override
    public List<Role> getAllRoles(Integer shopId) {
        List<Role> roles = roleMapper.selectAll(shopId);
        LOGGER.info("查询所有角色, 超市id: " + shopId);
        return roles;
    }

    @Override
    public Role getRole(Integer roleId) {
        Role role = roleMapper.selectById(roleId);
        LOGGER.info("查询角色 id: " + roleId);
        return role;
    }

    @Override
    public Role addRole(Role role) {
        Integer id = roleMapper.add(role);
        LOGGER.info("添加角色 id: " + role.getId() + " 角色名称: " + role.getName());
        return getRole(role.getId());
    }

    @Override
    public void delRole(Integer roleId) {
        List<User> users = userService.getUserByRole(roleId);
        for (User user : users) {
            userService.delUser(user.getId());
        }
        roleMapper.del(roleId);
        LOGGER.info("删除角色 id: " + roleId);
    }

    @Override
    public Role authorize(Map<String, Boolean> auth, Integer roleId) {
        return null; // TODO
    }
}
