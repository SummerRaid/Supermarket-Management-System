package sms.controller;

import sms.pojo.User;
import sms.service.UserService;

import javax.servlet.http.HttpSession;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: UserController
 * @Description: 用户控制器
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/20 13:05
 */
public class UserController {
    private UserService userService;

    /**
     * @Description: 用户登录界面
     * @param uname 用户名
     * @param pwd 密码
     * @param session session
     * @return: java.lang.String 跳转到主页面(登陆成功) 或者 当前页面(登录失败)
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 13:35
     */
    public String login(String uname, String pwd, HttpSession session) {
        User user = userService.login(uname, pwd);
        if(user != null) {
            session.setAttribute("currUser", user);
            session.setAttribute("shopId", user.getRole().getShop().getId());
            session.setAttribute("authority", user.getRole().getAuthority());
            // 跳转到主界面
            return "redirect:mainPage.do";
        }
        return "user/login";
    }
}
