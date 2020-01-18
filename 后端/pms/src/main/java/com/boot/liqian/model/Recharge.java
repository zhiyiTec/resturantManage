package com.boot.liqian.model;

import java.util.Date;

public class Recharge {
    private Integer id;
    private String userid;

    private Double restmoney;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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



    public Double getRestmoney() {
        return restmoney;
    }

    public void setRestmoney(Double restmoney) {
        this.restmoney = restmoney;
    }
}
