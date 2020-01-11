package com.boot.liqian.service.serviceImpl;

import com.boot.liqian.mapper.DeleteMapper;
import com.boot.liqian.mapper.GetMapper;
import com.boot.liqian.mapper.SaveMapper;
import com.boot.liqian.mapper.UpdateMapper;
import com.boot.liqian.model.*;
import com.boot.liqian.service.InventoryManageService;
import com.boot.liqian.service.MedinceSaileManageService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

@Service
public class InventoryManageServiceImpl implements InventoryManageService {
    @Autowired
    GetMapper getMapper;
    @Autowired
    UpdateMapper updateMapper;
    @Autowired
    DeleteMapper deleteMapper;
    @Autowired
    SaveMapper saveMapper;


    @Override
    public List<Medince> getMedinces(Integer pn) {
        PageHelper.startPage(pn, 5);// 后面紧跟的这个查询就是分页查询
        List<Medince> medinces = getMapper.getAllMedince();

        for (Medince m : medinces) {
            Productor productor = getMapper.getProductByPI(m.getProductorId());
            m.setProductName(productor.getProductorName());
            MedinceType medinceType = getMapper.getMedinceTypeByType(String.valueOf(m.getMedinceType()));
            m.setMedinceTypeCotent(medinceType.getCotent());
        }
        return medinces;
    }

    @Override
    public List<Medince> getLittleMedince() {
        List<Medince>medinceList=  getMapper.getLittleMedince();


        return medinceList;

    }

    @Override
    public List<Medince> searchMedinceByName_IM(Integer pn,String name) {
        PageHelper.startPage(pn, 5);// 后面紧跟的这个查询就是分页查询
        List<Medince>medinces=getMapper.getMedinceWithName(name);
        for(Medince m:medinces){
            Integer mType=m.getMedinceType();
            MedinceType medinceType=getMapper.getMedinceTypeByType(String.valueOf(mType));
            m.setMedinceTypeCotent(medinceType.getCotent());
          Productor p=  getMapper.getProductByPI(m.getProductorId());
          m.setProductName(p.getProductorName());
        }
        return medinces;
    }

    @Override
    public List<Medince> searchMedinceByMI_IM(Integer pn, String mi) {
        PageHelper.startPage(pn, 5);// 后面紧跟的这个查询就是分页查询
        List<Medince>medinces=getMapper.getMedinceWithMI(mi);
        for(Medince m:medinces){
            Productor p=  getMapper.getProductByPI(m.getProductorId());
            m.setProductName(p.getProductorName());
            Integer mType=m.getMedinceType();
            MedinceType medinceType=getMapper.getMedinceTypeByType(String.valueOf(mType));
            m.setMedinceTypeCotent(medinceType.getCotent());

        }
        return medinces;
    }

}
