package com.boot.liqian.service.serviceImpl;

import com.boot.liqian.mapper.DeleteMapper;
import com.boot.liqian.mapper.GetMapper;
import com.boot.liqian.mapper.SaveMapper;
import com.boot.liqian.mapper.UpdateMapper;
import com.boot.liqian.model.*;
import com.boot.liqian.service.MedinceSaileManageService;
import com.boot.liqian.service.SaileManageService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

@Service
public class SaileManageServiceImpl implements SaileManageService {
    @Autowired
    GetMapper getMapper;
    @Autowired
    UpdateMapper updateMapper;
    @Autowired
    DeleteMapper deleteMapper;
    @Autowired
    SaveMapper saveMapper;


    @Override
    public List<Sail> getSailInfo(Integer pn) {
        PageHelper.startPage(pn, 5);// 后面紧跟的这个查询就是分页查询
        List<Sail> sails = getMapper.getSailInfo();

        for (Sail s : sails) {
            MedinceType medinceType = getMapper.getMedinceTypeByType(String.valueOf(s.getMedinceType()));
            s.setMedinceTypeCotent(medinceType.getCotent());

            User user=getMapper.getUserByUserId(s.getUserId());
            s.setUserName(user.getUserName());
        }
        return sails;

    }

    @Override
    public void deleteSailByID(String id) {
        deleteMapper.deleteSailInfoBySI(id);
    }

    @Override
    public void deleteSailsByID(String ids[]) {
        for(String id:ids){
            deleteMapper.deleteSailInfoBySI(id);
        }
    }

    @Override
    public List<Sail> getSailByName(Integer pn,String name) {
        PageHelper.startPage(pn, 5);// 后面紧跟的这个查询就是分页查询
        List<Sail> sails = getMapper.getSsailByName(name);

        for (Sail s : sails) {

            MedinceType medinceType = getMapper.getMedinceTypeByType(String.valueOf(s.getMedinceType()));

            User user=getMapper.getUserByUserId(s.getUserId());
            s.setMedinceTypeCotent(medinceType.getCotent());
            s.setUserName(user.getUserName());
        }
        return sails;

    }

    @Override
    public List<Sail> getSailByID(Integer pn, String ID) {
        PageHelper.startPage(pn, 5);// 后面紧跟的这个查询就是分页查询
        List<Sail> sails = getMapper.getSsailByID(ID);

        for (Sail s : sails) {
            MedinceType medinceType = getMapper.getMedinceTypeByType(String.valueOf(s.getMedinceType()));
            User user=getMapper.getUserByUserId(s.getUserId());


            s.setMedinceTypeCotent(medinceType.getCotent());
            s.setUserName(user.getUserName());
        }
        return sails;

    }
}
