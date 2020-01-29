package com.boot.liqian.mapper;

import com.boot.liqian.model.*;

public interface SaveMapper {
    public void saveUser(User user);//添加用户
    public void addProductor(Productor productor);//添加供应商
    public void addMdince(Medince medince);//添加药物
    public void addMdinceType(MedinceType medinceType);//添加药物类别
    public void addBuyer(Buyer buyer);//添加buyer
    public void addPurchase(Purchase purchase);//添加购物
    public void addMedinceRedemption(MedinceRedemption medinceRedemption);//添加药品积分
    public void addSail(Sail sail);//添加用户购买记录
    public void addRetur(Return r);//添加用户退款记录
    public Integer addPrize(Redemption redemption);//添加一件奖品信息
    public Integer addRecharge(Recharge recharge);//添加充值信息
    public Integer addRechargeRecord(Rechagerecord rechagerecord);//添加充值记录
    public Integer addSpend(Spend spend);//添加用户消费记录
    public Integer addSpenddetail(SpendDetail spendDetail);//添加用户详细消费记录
}
