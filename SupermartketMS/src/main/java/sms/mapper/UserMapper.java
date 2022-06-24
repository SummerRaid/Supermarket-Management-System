package sms.mapper;

import sms.pojo.Role;
import sms.pojo.User;

import java.util.List;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: UserMapper
 * @Description: 用户映射接口
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/16 12:20
 */
public interface UserMapper {
    /**
     * @Description: 根据超市id 查询所有用户
     * @param shopId 超市id
     * @return: java.util.List<sms.pojo.User>
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 13:47
     */
    List<User> selectAll(int shopId);

    /**
     * @Description: 根据角色id 查询所有用户
     * @param roleId 角色id
     * @return: java.util.List<sms.pojo.User> 精确
     * @Author: Zirui Qiao
     * @Date: 2022/6/17 18:40
     */
    List<User> selectByRole(int roleId);

    /**
     * @Description: 根据用户id查询用户
     * @param id 用户id
     * @return: sms.pojo.User 精确
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 13:49
     */
    User selectById(int id);

    /**
     * @Description: 根据用户名获取用户
     * @param uname 用户名
     * @return: sms.pojo.User 精确
     * @Author: Zirui Qiao
     * @Date: 2022/6/19 16:28
     */
    User selectByName(String uname);

    /**
     * @Description: 添加用户 返回id
     * @param user 用户(包含详细信息)
     * @return: java.lang.Integer
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 13:49
     */
    Integer add(User user);

    /**
     * @Description: 删除用户
     * @param id 用户id
     * @return: void
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 18:41
     */
    void del(int id);

    /**
     * @Description: 更新用户信息
     * @param user 用户(包含详细信息)
     * @return: java.lang.Integer
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 18:42
     */
    Integer update(User user);

    /**
     * @Description: 根据roleId删除user
     * @param roleId roleID
     * @return: void
     * @Author: Zirui Qiao
     * @Date: 2022/6/24 16:02
     */
    void delByRoleId(Integer roleId);
}
