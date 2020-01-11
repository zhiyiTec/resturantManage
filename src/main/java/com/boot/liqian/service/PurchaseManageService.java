package com.boot.liqian.service;

import com.boot.liqian.model.Medince;
import com.boot.liqian.model.Purchase;

import java.util.List;

public interface PurchaseManageService {
    public List<Purchase> getAllPurchase(Integer pn);

    public void deletePurchaseByPI(String purchaseId);

    public void deletePurchasesByPI(String purchaseId[]);

    public List<Purchase> getPurchaseByName(Integer pn,String id);
}
