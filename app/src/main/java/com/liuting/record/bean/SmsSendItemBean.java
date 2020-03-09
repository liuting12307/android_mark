package com.liuting.record.bean;

/**
 * 作者:admin on 2019/12/29 22:34
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.record.bean
 * TODO:
 */
public class SmsSendItemBean {
    private String num;
    private String status;
    private String time;
    private String failedDesc;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFailedDesc() {
        return failedDesc;
    }

    public void setFailedDesc(String failedDesc) {
        this.failedDesc = failedDesc;
    }
}
