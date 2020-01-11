package com.boot.liqian.service.serviceImpl;

import com.boot.liqian.mapper.DeleteMapper;
import com.boot.liqian.mapper.GetMapper;
import com.boot.liqian.mapper.SaveMapper;
import com.boot.liqian.mapper.UpdateMapper;
import com.boot.liqian.model.Medince;
import com.boot.liqian.model.MedinceType;
import com.boot.liqian.model.Productor;
import com.boot.liqian.model.Purchase;
import com.boot.liqian.service.MedinceManageService;
import com.boot.liqian.service.PurchaseManageService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PurchaseManageServiceImpl implements PurchaseManageService {
    @Autowired
    GetMapper getMapper;
    @Autowired
    UpdateMapper updateMapper;
    @Autowired
    DeleteMapper deleteMapper;
    @Autowired
    SaveMapper saveMapper;


    @Override
    public List<Purchase> getAllPurchase(Integer pn) {
        PageHelper.startPage(pn, 5);// 后面紧跟的这个查询就是分页查询
        List<Purchase> purchases = getMapper.getAllPurchase();

        for(Purchase p:purchases){
            MedinceType medinceType=getMapper.getMedinceTypeByType(String.valueOf(p.getMedinceType()));
            p.setMedinceTypeCotent(medinceType.getCotent());
            Productor productor= getMapper.getProductByPI(p.getProductorId());
            p.setProductorName(productor.getProductorName());
           Medince medince= getMapper.getMedinceByMN(p.getMedinceId());
           p.setMedinceName(medince.getMedinceName());
        }
        return purchases;
    }

    @Override
    public void deletePurchaseByPI(String purchaseId) {
        deleteMapper.deletePurchaseBiPI(purchaseId);
    }

    @Override
    public void deletePurchasesByPI(String[] purchaseId) {
        for(String id:purchaseId){
            deleteMapper.deletePurchaseBiPI(id);
        }
    }

    @Override
    public List<Purchase> getPurchaseByName(Integer pn,String id) {
        PageHelper.startPage(pn, 5);// 后面紧跟的这个查询就是分页查询
        List<Purchase> purchases = getMapper.getPurchaseByID(id);

        for(Purchase p:purchases){
            MedinceType medinceType=getMapper.getMedinceTypeByType(String.valueOf(p.getMedinceType()));
            p.setMedinceTypeCotent(medinceType.getCotent());
            Productor productor= getMapper.getProductByPI(p.getProductorId());
            p.setProductorName(productor.getProductorName());
            Medince medince= getMapper.getMedinceByMN(p.getMedinceId());
            p.setMedinceName(medince.getMedinceName());
        }
        return purchases;
    }
}
