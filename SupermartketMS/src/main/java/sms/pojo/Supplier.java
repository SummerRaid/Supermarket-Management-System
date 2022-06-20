package sms.pojo;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: Supplier
 * @Description: 供应商实体类
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/14 15:57
 */
public class Supplier {
    private Integer id;           // 供应商id
    private String address;       // 供应商地址
    private String name;          // 供应商名称
    private String contactPerson; // 供应商联系人
    private String contact;       // 供应商联系方式
    private String remark;        // 供应商备注
    private Shop shop;        // 对应的超市

    public Supplier() {
    }

    public Supplier(String address, String name, String contactPerson, String contact, String remark, Shop shop) {
        this.address = address;
        this.name = name;
        this.contactPerson = contactPerson;
        this.contact = contact;
        this.remark = remark;
        this.shop = shop;
    }

    public Supplier(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
