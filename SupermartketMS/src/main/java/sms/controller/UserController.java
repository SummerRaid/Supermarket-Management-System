package sms.controller;

import myssm.util.StringUtil;
import sms.pojo.Role;
import sms.pojo.User;
import sms.service.RoleService;
import sms.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
    private RoleService roleService;

    public String index(){
        return "";
    }

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
            return "index";
        }
        return "login";
    }

    /**
     * @Description: 注册用户
     * @param verifyCode 验证码
     * @param roleId 角色id
     * @param uname 用户名
     * @param pwd 密码
     * @param tname 真实姓名
     * @param tel 电话
     * @param address 地址
     * @param session session
     * @param resp resp
     * @return: java.lang.String
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 18:16
     */
    public String register(String verifyCode, Integer roleId, String uname, String pwd, String tname, String tel,
                           String address, HttpSession session, HttpServletResponse resp) throws IOException {
        Object ksObj = session.getAttribute("KAPTCHA_SESSION_KEY");
        if(!verifyCode.equals(ksObj)) {
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("<script language='javascript'>alert('验证码错误');" +
                    "window.location.href='page.do?operate=page&page=user/regist';" +
                    "</script>");
            return null;
        } else {
            if(ksObj.equals(verifyCode)) {
                Role role = roleService.getRole(roleId);
                User user = new User(role, uname, pwd, tname, tel, address);
                userService.register(user);
                return "user/login";
            }
        }
        return "user/login";
    }

    /**
     * @Description: 修改用户
     * @param roleId 角色id
     * @param uname 用户名
     * @param pwd 密码
     * @param tname 真实姓名
     * @param tel 电话
     * @param address 地址
     * @param id id
     * @return: java.lang.String
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 18:16
     */
    public String editUser(Integer roleId, String uname, String pwd, String tname, String tel,
                           String address, Integer id) {
        Role role = roleService.getRole(roleId);
        User user = new User(role, uname, pwd, tname, tel, address);
        user.setId(id);
        userService.updateUser(user);

        return "";
    }

    /**
     * @Description: 删除用户
     * @param userId 用户id
     * @return: java.lang.String
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 18:16
     */
    public String delUser(Integer userId) {
        userService.delUser(userId);

        return "";
    }
    /**
     * @Description: 获取所有用户
     * @param session session，用于获取超市id
     * @return: java.lang.String
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 18:16
     */
    public String getAllUsers(HttpSession session) {
        Integer shopId = (Integer) session.getAttribute("shopId");
        List<User> allUsers = userService.getAllUsers(shopId);

        return "json:" + StringUtil.toJsonString(allUsers);
    }

    /**
     * @Description: 用户名查询
     * @param name 用户名
     * @return: java.lang.String
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 18:15
     */
    public String getUsersByName(String name) {
        User user = userService.getUser(name);

        return "json:" + StringUtil.toJsonString(user);
    }
}
