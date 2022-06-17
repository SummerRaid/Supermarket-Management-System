package sms.pojo;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: User
 * @Description: 用户实体类
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/14 15:57
 */
public class User {
    private Integer id;    // 用户id
    private Role role;     // 用户所属角色
    private String uname;  // 用户 用户名
    private String pwd;    // 用户 密码
    private String tname;  // 用户 真实姓名
    private String tel;    // 用户电话
    private String addr;   // 用户地址
    private Shop shop;        // 对应的超市

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(String uname, String pwd, Shop shop) {
        this.uname = uname;
        this.pwd = pwd;
        this.shop = shop;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}