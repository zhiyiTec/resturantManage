package com.boot.liqian.model;

import java.util.Date;

public class Purchase {
    private String purchaseId;
    private String medinceId;
    private String medinceName;
    private String productorId;
    private String productorName;
    private String purchaseTime;
    private Integer id;
    private Integer medinceType;
    private String medinceTypeCotent;

    public String getMedinceName() {
        return medinceName;
    }

    public void setMedinceName(String medinceName) {
        this.medinceName = medinceName;
    }

    public String getProductorName() {
        return productorName;
    }

    public void setProductorName(String productorName) {
        this.productorName = productorName;
    }

    public String getMedinceTypeCotent() {
        return medinceTypeCotent;
    }

    public void setMedinceTypeCotent(String medinceTypeCotent) {
        this.medinceTypeCotent = medinceTypeCotent;
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getMedinceId() {
        return medinceId;
    }

    public void setMedinceId(String medinceId) {
        this.medinceId = medinceId;
    }

    public String getProductorId() {
        return productorId;
    }

    public void setProductorId(String productorId) {
        this.productorId = productorId;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
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
}
