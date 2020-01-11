package com.boot.liqian.model;

public class Buyer {
    private String userId;
    private String membershipCard;
    private String membershipPassword;
    private Integer integral;
    private String buyerName;
    private Double cumoney;
    private Double sunmoney;

    public Double getCumoney() {
        return cumoney;
    }

    public void setCumoney(Double cumoney) {
        this.cumoney = cumoney;
    }

    public Double getSunmoney() {
        return sunmoney;
    }

    public void setSunmoney(Double sunmoney) {
        this.sunmoney = sunmoney;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMembershipCard() {
        return membershipCard;
    }

    public void setMembershipCard(String membershipCard) {
        this.membershipCard = membershipCard;
    }

    public String getMembershipPassword() {
        return membershipPassword;
    }

    public void setMembershipPassword(String membershipPassword) {
        this.membershipPassword = membershipPassword;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }
}
