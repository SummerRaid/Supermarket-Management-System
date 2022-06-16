package sms.mapper;

import sms.pojo.Role;
import sms.pojo.User;

import java.util.List;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: UserMapper
 * @Description: TODO
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/16 12:20
 */
public interface UserMapper {
    /**
     * @Description: 查询所有用户
     * @return: java.util.List<sms.pojo.User>
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 13:47
     */
    List<User> selectAll();

    /**
     * @Description: 根据角色查询用户
     * @param role 角色
     * @return: java.util.List<sms.pojo.User>
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 13:48
     */
    List<User> selectByRole(Role role);

    /**
     * @Description: 根据用户id查询用户
     * @param id 用户id
     * @return: sms.pojo.User
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 13:49
     */
    User selectById(int id);

    /**
     * @Description: 添加用户 返回id
     * @param user 用户
     * @return: java.lang.Integer
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 13:49
     */
    Integer add(User user);
}
