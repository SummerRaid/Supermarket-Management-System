package sms.service.impl;

import myssm.util.MapperUtil;
import myssm.util.StringUtil;
import org.slf4j.LoggerFactory;
import sms.mapper.UserMapper;
import sms.pojo.Order;
import sms.pojo.User;
import sms.service.OrderService;
import sms.service.UserService;

import java.util.List;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: UserServiceImpl
 * @Description: 用户服务实现类
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/19 11:22
 */
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private OrderService orderService;

    private static final org.slf4j.Logger LOGGER =
            LoggerFactory.getLogger(UserServiceImpl.class);

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public UserServiceImpl() {
        userMapper = MapperUtil.getProxy(UserMapper.class);
    }

    @Override
    public User login(String uname, String pwd) {
        User user = userMapper.selectByName(uname);
        User result = null;
        if (user != null) {
            if (pwd.equals(user.getPwd())) {
                LOGGER.info("登录成功！");
                result = user;
            } else {
                LOGGER.error("登录失败，密码错误");
            }
        } else {
            LOGGER.error("登录失败，用户不存在");
        }

        return result;
    }

    @Override
    public User register(User user) {
        User result = null;
        if (StringUtil.isNotEmpty(user.getUname())) {
            User user1 = userMapper.selectByName(user.getUname());
            if (user1 == null) {
                LOGGER.info("用户不存在，可以注册");
                userMapper.add(user);
                LOGGER.info("注册成功");
                result = user;
            } else {
                LOGGER.error("用户已经存在，注册失败");
            }
        } else {
            LOGGER.error("用户名为空，注册失败");
        }
        return result;
    }

    @Override
    public User getUser(String uname) {
        User user = userMapper.selectByName(uname);
        LOGGER.info("获取用户 用户名: " + uname);
        return user;
    }

    @Override
    public List<User> getAllUsers(int shopId) {
        List<User> users = userMapper.selectAll(shopId);
        LOGGER.info("获取超市 id: " + shopId + " 的所有用户");
        return users;
    }

    @Override
    public User updateUser(User user) {
        Integer id = userMapper.update(user);
        User result = userMapper.selectById(user.getId());
        LOGGER.info("更新用户 id: " + id + " 用户名： " + result.getUname());
        return result;
    }

    @Override
    public void delUser(Integer userId) {
        List<Order> orders = orderService.getOrdersByUser(userId);
        for (Order order : orders) {
            orderService.setOrderUser(order.getId(), null);
        }
        userMapper.del(userId);
        LOGGER.info("删除用户 id: " + userId);
    }

    @Override
    public List<User> getUserByRole(Integer roleId) {
        List<User> users = userMapper.selectByRole(roleId);
        LOGGER.info("获取角色 id: " + roleId + " 的所有用户");
        return users;
    }
}
