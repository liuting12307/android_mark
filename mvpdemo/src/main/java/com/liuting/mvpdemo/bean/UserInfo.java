package com.liuting.mvpdemo.bean;

/**
 * 作者:admin on 2020/3/8 15:12
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.mvpdemo.bean
 * TODO:
 */
public class UserInfo extends BaseEntity {
    private String company;
    private String name;

    public UserInfo(String company, String name) {
        this.company = company;
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "company='" + company + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
