package com.boot.liqian.service.serviceImpl;

import com.boot.liqian.mapper.DeleteMapper;
import com.boot.liqian.mapper.GetMapper;

import com.boot.liqian.mapper.SaveMapper;
import com.boot.liqian.mapper.UpdateMapper;
import com.boot.liqian.model.Redemption;


import com.boot.liqian.service.PrizeManageService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PrizeManageServiceImpl implements PrizeManageService {
    @Autowired
    GetMapper getMapper;
    @Autowired
    UpdateMapper updateMapper;
    @Autowired
    DeleteMapper deleteMapper;
    @Autowired
    SaveMapper saveMapper;
    @Override
    public List<Redemption> getAllRedemption(Integer pn) {
        PageHelper.startPage(pn, 5);// 后面紧跟的这个查询就是分页查询
        List<Redemption> redemptions=getMapper.getRedemption();

        return redemptions;
    }

    @Override
    public Redemption getRedemtionById(Integer id) {
        Redemption re=getMapper.getRedemptionById(id);
        return re;
    }

    @Override
    public Integer updateRedemption(Redemption redemption) {
        return updateMapper.updatePrizeInfo(redemption);
    }

    @Override
    public Integer deletePrizeById(Integer id) {

        return deleteMapper.deletePrizeById(id);
    }

    @Override
    public Integer addPrize(Redemption redemption) {
        return saveMapper.addPrize(redemption);
    }

    @Override
    public Integer deletePrizes(List<Integer> ids) {
        return deleteMapper.deletePrizes(ids);
    }

    @Override
    public List<Redemption> searchPrizesByName(String name) {
        PageHelper.startPage(1, 5);// 后面紧跟的这个查询就是分页查询
        List<Redemption> re= getMapper.getPrizesByName(name);
        return re;
    }
}
