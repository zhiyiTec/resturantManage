package com.boot.liqian.model;

import java.util.Date;

public class Medince {
    private Integer id;
    private String medinceId;
    private String medinceName;
    private Integer medinceType;
    private String medinceTypeCotent;
    private Double buyNumber;
    private Double restNumber;
    private String productTime;
    private String functional;
    private Double price;
    private String productorId;
    private Double overTime;
    private String productName;
    private Double purchasePrice;
    private Double medinceReNu;//对应的积分

    public Double getMedinceReNu() {
        return medinceReNu;
    }

    public void setMedinceReNu(Double medinceReNu) {
        this.medinceReNu = medinceReNu;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getMedinceTypeCotent() {
        return medinceTypeCotent;
    }

    public void setMedinceTypeCotent(String medinceTypeCotent) {
        this.medinceTypeCotent = medinceTypeCotent;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getOverTime() {
        return overTime;
    }

    public void setOverTime(Double overTime) {
        this.overTime = overTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getProductorId() {
        return productorId;
    }

    public void setProductorId(String productorId) {
        this.productorId = productorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getMedinceType() {
        return medinceType;
    }

    public void setMedinceType(Integer medinceType) {
        this.medinceType = medinceType;
    }

    public Double getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(Double buyNumber) {
        this.buyNumber = buyNumber;
    }

    public Double getRestNumber() {
        return restNumber;
    }

    public void setRestNumber(Double restNumber) {
        this.restNumber = restNumber;
    }

    public String getProductTime() {
        return productTime;
    }

    public void setProductTime(String productTime) {
        this.productTime = productTime;
    }

    public String getFunctional() {
        return functional;
    }

    public void setFunctional(String functional) {
        this.functional = functional;
    }
}
