package com.boot.liqian.model;

public class Productor {
    private Integer id;
    private String productorId;
    private String productorName;
    private String connector;
    private String conPhone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductorId() {
        return productorId;
    }

    public void setProductorId(String productorId) {
        this.productorId = productorId;
    }

    public String getProductorName() {
        return productorName;
    }

    public void setProductorName(String productorName) {
        this.productorName = productorName;
    }

    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }

    public String getConPhone() {
        return conPhone;
    }

    public void setConPhone(String conPhone) {
        this.conPhone = conPhone;
    }
}
