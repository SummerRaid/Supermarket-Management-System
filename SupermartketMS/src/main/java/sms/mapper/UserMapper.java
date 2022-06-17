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
     * @Description: 根据用户id查询用户
     * @param id 用户id
     * @return: sms.pojo.User
     * @Author: Zirui Qiao
     * @Date: 2022/6/16 13:49
     */
    User selectById(int id);

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
}
