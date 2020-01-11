package com.boot.liqian.service.serviceImpl;

import com.boot.liqian.mapper.DeleteMapper;
import com.boot.liqian.mapper.GetMapper;
import com.boot.liqian.mapper.SaveMapper;
import com.boot.liqian.mapper.UpdateMapper;
import com.boot.liqian.model.*;
import com.boot.liqian.service.BuyerService;
import com.boot.liqian.service.MedinceManageService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    GetMapper getMapper;
    @Autowired
    UpdateMapper updateMapper;
    @Autowired
    DeleteMapper deleteMapper;
    @Autowired
    SaveMapper saveMapper;


    @Override
    public List<Medince> getMedinceINfo(Integer pn) {
        PageHelper.startPage(pn, 5);// 后面紧跟的这个查询就是分页查询
        List<Medince> medinces = getMapper.getAllMedince();

        for(Medince m:medinces){
           MedinceRedemption medinceRedemption=getMapper.getMRByID(m.getMedinceId());
            m.setMedinceReNu(medinceRedemption.getNumber());
            MedinceType medinceType=getMapper.getMedinceTypeByType(String.valueOf(m.getMedinceType()));
            m.setMedinceTypeCotent(medinceType.getCotent());
        }
        return medinces;

    }

    @Override
    public Medince getMedinceById(String id) {
        Medince medince=getMapper.getMedinceByMI(id);
        return medince;
    }

    @Override
    public void addSail(String mi, String buyerId, Integer buyNu) {
        Medince medince=getMapper.getMedinceByMI(mi);
        Sail sail=new Sail();
        String tiStamp = String.valueOf(Calendar.getInstance().getTimeInMillis());
        String buyId = String.valueOf(tiStamp.substring(tiStamp.length() - 6, tiStamp.length()));
        sail.setBuyId(buyId);
        sail.setUserId(buyerId);
        sail.setMedinceId(mi);
        sail.setMedinceName(medince.getMedinceName());
        sail.setMedinceType(medince.getMedinceType());
        sail.setNumber(buyNu);
        Double count=buyNu*medince.getPrice();
        sail.setCount(count);
        Date ss = new Date();
        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format0.format(ss.getTime());//这个就是把时间戳经过处理得到期望格式的时间
        sail.setBuyTime(ss);
        saveMapper.addSail(sail);
        MedinceRedemption medinceRedemption=getMapper.getMRByID(mi);
        Buyer buyer=new Buyer();
        buyer.setUserId(buyerId);
        int a= (int) Math.round(medinceRedemption.getNumber());
        buyer.setIntegral(a*buyNu);
        updateMapper.updateBuyerByBI(buyer);
        medince.setRestNumber(medince.getRestNumber()-buyNu);
        updateMapper.updatemedincerestNumberByMI(medince);
    }

    @Override
    public List<Medince> getMedinceByName(Integer pn,String name) {
        PageHelper.startPage(pn, 5);// 后面紧跟的这个查询就是分页查询
        List<Medince> medinces =getMapper.getMedinceWithName(name);
        for(Medince m:medinces){
            MedinceRedemption medinceRedemption=getMapper.getMRByID(m.getMedinceId());
            m.setMedinceReNu(medinceRedemption.getNumber());
            MedinceType medinceType=getMapper.getMedinceTypeByType(String.valueOf(m.getMedinceType()));
            m.setMedinceTypeCotent(medinceType.getCotent());
        }
        return medinces;
    }

    @Override
    public List<Sail> getSailByID(String ID) {
        return getMapper.getSailByID(ID);
    }

    @Override
    public void returnOrder(String buyID, String userID, String medinceID) {

       Buyer buyer= getMapper.getBuyerById(userID);
       MedinceRedemption medinceRedemption=getMapper.getMRByID(medinceID);
       Sail sail=getMapper.getSailByBuyID(buyID);
        int a= (int) Math.round(buyer.getIntegral()-sail.getNumber()*medinceRedemption.getNumber());
       buyer.setIntegral(a);
       updateMapper.updateBuyerByBI(buyer);
       Medince medince=getMapper.getMedinceByMN(medinceID);
       medince.setRestNumber(medince.getRestNumber()+sail.getNumber()*medinceRedemption.getNumber());
       updateMapper.updatemedincerestNumberByMI(medince);
       Return r=new Return();
        String tiStamp = String.valueOf(Calendar.getInstance().getTimeInMillis());
        String returnId = String.valueOf(tiStamp.substring(tiStamp.length() - 6, tiStamp.length()));
        r.setReturnId(returnId);
        r.setMedinceId(medinceID);
        User user=getMapper.getUserByUserId(userID);
        r.setReturnName(user.getUserName());
        r.setConnector(userID);
        Date ss = new Date();
        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format0.format(ss.getTime());//这个就是把时间戳经过处理得到期望格式的时间
        r.setReturnTime(time);
        saveMapper.addRetur(r);

        deleteMapper.deleteSailInfoBySI(buyID);
    }

    @Override
    public Buyer getJifenByID(String id) {

        return getMapper.getBuyerById(id);
    }
}
