package sms.pojo;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: Role
 * @Description: 角色实体类
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/14 15:58
 */
public class Role {
    private Integer id;      // 角色id
    private String name;     // 角色名称
    private String remark;   // 角色备注
    private String authority;// 角色权限
    private Shop shop;       // 对应的超市

    public Role() {
    }

    public Role(String name, String remark, String authority, Shop shop) {
        this.name = name;
        this.remark = remark;
        this.authority = authority;
        this.shop = shop;
    }

    public Role(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", authority='" + authority + '\'' +
                ", shop=" + shop +
                '}';
    }
}
