package com.boot.liqian.model;

public class Return {
    private Integer id;
    private String returnId;
    private String medinceId;
    private String medinceName;
    private String returnName;
    private String connector;
    private String returnTime;

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

    public String getReturnId() {
        return returnId;
    }

    public void setReturnId(String returnId) {
        this.returnId = returnId;
    }

    public String getMedinceId() {
        return medinceId;
    }

    public void setMedinceId(String medinceId) {
        this.medinceId = medinceId;
    }

    public String getReturnName() {
        return returnName;
    }

    public void setReturnName(String returnName) {
        this.returnName = returnName;
    }

    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }
}
