package com.boot.liqian.service.serviceImpl;

import com.boot.liqian.mapper.DeleteMapper;
import com.boot.liqian.mapper.GetMapper;
import com.boot.liqian.mapper.SaveMapper;
import com.boot.liqian.mapper.UpdateMapper;
import com.boot.liqian.model.*;
import com.boot.liqian.service.MedinceManageService;
import com.boot.liqian.service.SailService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MedinceManageServiceImpl implements MedinceManageService {
    @Autowired
    GetMapper getMapper;
    @Autowired
    UpdateMapper updateMapper;
    @Autowired
    DeleteMapper deleteMapper;
    @Autowired
    SaveMapper saveMapper;


    @Override
    public List<Medince> getAllMedinces(Integer pn) {
        PageHelper.startPage(pn, 5);// 后面紧跟的这个查询就是分页查询
        List<Medince> medinces = getMapper.getAllMedince();
        for(Medince m:medinces){
            MedinceType medinceType=getMapper.getMedinceTypeByType(String.valueOf(m.getMedinceType()));
            m.setMedinceTypeCotent(medinceType.getCotent());
        }

        return medinces;
    }

    @Override
    public Medince getMedinceByMI(String mi) {
        return getMapper.getMedinceByMI(mi);
    }

    @Override
    public void updateMedince(String mi, String mn, String bn, String rn, String fun, String pr) {
        Medince medince = new Medince();
        medince.setMedinceId(mi);
        medince.setMedinceName(mn);
        medince.setBuyNumber(Double.valueOf(bn));
        Double restNu=Double.valueOf(bn)+Double.valueOf(rn);
        medince.setRestNumber(restNu);
        medince.setFunctional(fun);
        medince.setPrice(Double.valueOf(pr));
        updateMapper.updateMedinceByMI(medince);
    }

    @Override
    public void delteMedince(String mi) {
        deleteMapper.deleteMedinceByMI(mi);
    }

    @Override
    public void delteMedinces(String mis[]) {
        for (String mi : mis) {
            deleteMapper.deleteMedinceByMI(mi);
        }
    }

    @Override
    public List<MedinceType> getMedinceType() {

        return getMapper.getMedinceType();
    }

    @Override
    public List<Productor> getAllPro() {

        return getMapper.getAllProduct();
    }

    @Override
    public Integer judgeMedinceByMI(String mi) {
        Integer re = null;
        Medince medince = getMapper.getMedinceByMN(mi);
        if (medince == null) {
            re = 0;//表示没有重复
        } else {
            re = 1;
        }
        return re;
    }

    @Override
    public void addMedince(String oT,String purchase,String mI, String cotent, String medinceName, Double buyNumber, String productTime, String functional, Double price, String pN,String medinceReNU) {

        List<MedinceType> medinceTypes = getMapper.getMTByMN(cotent);
        Integer ty = null;

        for (MedinceType type : medinceTypes) {
            ty = type.getType();
        }


        Double restNumber = buyNumber;


        Medince medince1 = new Medince();
        Productor productor = getMapper.getProductByPN(pN);
        String pI = productor.getProductorId();
        medince1.setMedinceId(mI);

        medince1.setMedinceName(medinceName);
        medince1.setMedinceType(ty);
        medince1.setBuyNumber(buyNumber);
        medince1.setRestNumber(restNumber);
        medince1.setProductTime(productTime);
        medince1.setFunctional(functional);
        medince1.setProductorId(pI);
        medince1.setPrice(price);
        medince1.setOverTime(Double.valueOf(oT));
        medince1.setPurchasePrice(Double.valueOf(purchase));
        saveMapper.addMdince(medince1);


        Purchase  purchase1=new Purchase();
        String tiStamp = String.valueOf(Calendar.getInstance().getTimeInMillis());
        String purchaseId = String.valueOf(tiStamp.substring(tiStamp.length() - 6, tiStamp.length()));
        purchase1.setPurchaseId(purchaseId);
        purchase1.setMedinceId(mI);
        purchase1.setMedinceType(ty);
        purchase1.setProductorId(pI);
        Date ss = new Date();
        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format0.format(ss.getTime());//这个就是把时间戳经过处理得到期望格式的时间

        purchase1.setPurchaseTime(time);
        saveMapper.addPurchase(purchase1);
        MedinceRedemption medinceRedemption=new MedinceRedemption();
        medinceRedemption.setMedinceId(mI);
        medinceRedemption.setNumber(Double.valueOf(medinceReNU));
        saveMapper.addMedinceRedemption(medinceRedemption);
    }

    @Override
    public Integer judgeMedinceType(String mc) {
        Integer re=null;
        List<MedinceType>medinceTypes=getMapper.getMTByMN(mc);
        if(CollectionUtils.isEmpty(medinceTypes)){
            re=0;//说明该类别存在
        }else{
            re=1;//说明该类别不存在
        }
        return re;
    }

    @Override
    public void addMedinceType(String cotent) {
        String tiStamp = String.valueOf(Calendar.getInstance().getTimeInMillis());
        Integer type = Integer.valueOf(tiStamp.substring(tiStamp.length() - 4, tiStamp.length()));
        MedinceType medinceType=new MedinceType();
        medinceType.setType(type);
        medinceType.setCotent(cotent);
        saveMapper.addMdinceType(medinceType);
    }

    @Override
    public List<Medince> searchMedinceByName(String name) {
        PageHelper.startPage(1, 500);// 后面紧跟的这个查询就是分页查询
        return getMapper.getMedinceWithName(name);
    }

    @Override
    public List<Medince> searchMedinceByMI(String mi) {
        PageHelper.startPage(1, 500);// 后面紧跟的这个查询就是分页查询
        return getMapper.getMedinceWithMI(mi);
    }
}
