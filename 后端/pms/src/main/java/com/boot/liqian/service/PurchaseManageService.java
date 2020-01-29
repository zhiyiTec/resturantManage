package com.boot.liqian.service;

import com.boot.liqian.model.Medince;
import com.boot.liqian.model.Purchase;
import com.boot.liqian.model.Spend;
import com.boot.liqian.model.SpendDetail;

import java.util.List;

public interface PurchaseManageService {
    public List<Spend> getAllPurchase(Integer pn);

    public void deletePurchaseByPI(String purchaseId);

    public void deletePurchasesByPI(String purchaseId[]);

    public List<Purchase> getPurchaseByName(Integer pn,String id);

    /**
     * 通过用户Id获取对应的消费详情
     * @param userId
     * @return
     */
    public  List<SpendDetail> getSpendDetailById(String userId);
}
