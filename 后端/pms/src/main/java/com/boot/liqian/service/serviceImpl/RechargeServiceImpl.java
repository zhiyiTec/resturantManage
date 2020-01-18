package com.boot.liqian.service.serviceImpl;

import com.boot.liqian.mapper.DeleteMapper;
import com.boot.liqian.mapper.GetMapper;
import com.boot.liqian.mapper.SaveMapper;
import com.boot.liqian.mapper.UpdateMapper;
import com.boot.liqian.model.*;
import com.boot.liqian.service.BuyerService;
import com.boot.liqian.service.RechargeService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class RechargeServiceImpl implements RechargeService {
    @Autowired
    GetMapper getMapper;
    @Autowired
    UpdateMapper updateMapper;
    @Autowired
    DeleteMapper deleteMapper;
    @Autowired
    SaveMapper saveMapper;


    @Override
    public List<Recharge> getAllRecharge(Integer pn) {
        PageHelper.startPage(pn, 5);// 后面紧跟的这个查询就是分页查询
        List<Recharge> recharges = getMapper.getAllRecharges();

        for (Recharge b : recharges) {
            User user = getMapper.getUserByUserId(b.getUserid());

            b.setName(user.getUserName());

        }
        return recharges;
    }

    @Override
    public Integer deleteRechargeInfo(String userId) {
        Integer re=deleteMapper.deleteRecharge(userId);
        return re;
    }

    @Override
    public List<Rechagerecord> getAllRechargeRecords(String userid) {
        return getMapper.getAllRechargeRecordsById(userid);
    }
}
