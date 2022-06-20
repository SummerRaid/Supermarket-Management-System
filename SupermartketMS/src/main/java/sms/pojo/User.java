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
    private String address;   // 用户地址

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(String uname, String pwd, Role role) {
        this.uname = uname;
        this.pwd = pwd;
        this.role = role;
    }

    public User(Role role, String uname, String pwd, String tname, String tel, String address) {
        this.role = role;
        this.uname = uname;
        this.pwd = pwd;
        this.tname = tname;
        this.tel = tel;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role=" + role +
                ", uname='" + uname + '\'' +
                ", pwd='" + pwd + '\'' +
                ", tname='" + tname + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
