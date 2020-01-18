package com.boot.liqian.model;

import java.util.Date;

public class Spend {
    private Integer id;
    private String userid;
    private Date spentime;
    private Double money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Date getSpentime() {
        return spentime;
    }

    public void setSpentime(Date spentime) {
        this.spentime = spentime;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
