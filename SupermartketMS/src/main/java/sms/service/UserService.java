package sms.service;

import sms.pojo.User;

import java.util.List;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: UserService
 * @Description: 用户服务类
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
    List<User> getAllUser(int shopId);
}
