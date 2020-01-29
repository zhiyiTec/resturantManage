package com.boot.liqian.model;

import java.util.Date;

/**
 * 用于显示所有的消费用户的消费具体情况
 * 消费时间，消费金额
 */
public class SpendDetail {
    private Integer id;
    private String userId;
    private Date spendtime;
    private Double spendnumber;

    public Date getSpendtime() {
        return spendtime;
    }

    public void setSpendtime(Date spendtime) {
        this.spendtime = spendtime;
    }

    public Double getSpendnumber() {
        return spendnumber;
    }

    public void setSpendnumber(Double spendnumber) {
        this.spendnumber = spendnumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userId;
    }

    public void setUserid(String userId) {
        this.userId = userId;
    }
}
