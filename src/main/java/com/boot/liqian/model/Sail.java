package com.boot.liqian.model;

import java.util.Date;

public class Sail {
    private String buyId;
    private String userId;
    private String userName;
    private String medinceId;
    private String medinceName;
    private Integer id;
    private Integer medinceType;
    private String medinceTypeCotent;
    private Integer number;
    private Double count;
    private Date buyTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMedinceTypeCotent() {
        return medinceTypeCotent;
    }

    public void setMedinceTypeCotent(String medinceTypeCotent) {
        this.medinceTypeCotent = medinceTypeCotent;
    }

    public String getBuyId() {
        return buyId;
    }

    public void setBuyId(String buyId) {
        this.buyId = buyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMedinceId() {
        return medinceId;
    }

    public void setMedinceId(String medinceId) {
        this.medinceId = medinceId;
    }

    public String getMedinceName() {
        return medinceName;
    }

    public void setMedinceName(String medinceName) {
        this.medinceName = medinceName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMedinceType() {
        return medinceType;
    }

    public void setMedinceType(Integer medinceType) {
        this.medinceType = medinceType;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }
}
