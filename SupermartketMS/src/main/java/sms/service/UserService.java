package sms.service;

import sms.pojo.User;

import java.util.List;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: UserService
 * @Description: 用户服务接口
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/16 17:59
 */
public interface UserService {

    /**
     * @Description: 用户登录
     * @param uname 用户名
     * @param pwd 密码
     * @return: sms.pojo.User
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 18:13
     */
    User login(String uname, String pwd);

    /**
     * @Description: 用户注册
     * @param user 用户信息
     * @return: sms.pojo.User
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 18:11
     */
    User register(User user);

    /**
     * @Description: 根据用户名获取用户
     * @param uname 用户名
     * @return: sms.pojo.User
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 18:09
     */
    User getUser(String uname);

    /**
     * @Description: 获取所有用户
     * @param shopId 超市id
     * @return: java.util.List<sms.pojo.User>
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 18:15
     */
    List<User> getAllUsers(int shopId);

    /**
     * @Description: 更新用户
     * @param user 用户详细信息
     * @return: java.lang.Integer
     * @Author: Zirui Qiao
     * @Date: 2022/6/17 11:24
     */
    User updateUser(User user);

    /**
     * @Description: 根据用户id删除用户
     * @param userId 用户id
     * @return: void
     * @Author: Zirui Qiao
     * @Date: 2022/6/17 11:25
     */
    void delUser(Integer userId);

    /**
     * @Description: 根据角色获取用户
     * @param roleId 角色id
     * @return: java.util.List<sms.pojo.User>
     * @Author: Zirui Qiao
     * @Date: 2022/6/19 16:00
     */
    List<User> getUserByRole(Integer roleId);
}
