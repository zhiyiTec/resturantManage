package com.boot.liqian.service.serviceImpl;

import com.boot.liqian.mapper.DeleteMapper;
import com.boot.liqian.mapper.GetMapper;
import com.boot.liqian.mapper.SaveMapper;
import com.boot.liqian.mapper.UpdateMapper;
import com.boot.liqian.model.*;
import com.boot.liqian.service.PurchaseManageService;
import com.boot.liqian.service.ReturnManageService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnManageServiceImpl implements ReturnManageService {
    @Autowired
    GetMapper getMapper;
    @Autowired
    UpdateMapper updateMapper;
    @Autowired
    DeleteMapper deleteMapper;
    @Autowired
    SaveMapper saveMapper;

    @Override
    public List<Return> getAllReturnInfo(Integer pn) {
        PageHelper.startPage(pn, 5);// 后面紧跟的这个查询就是分页查询
        List<Return> returns = getMapper.getALlReturnInfo();

        for(Return r:returns){
            Medince medince= getMapper.getMedinceByMN(r.getMedinceId());
            r.setMedinceName(medince.getMedinceName());
        }
        return returns;

    }

    @Override
    public void deleteReturnById(String id) {
        deleteMapper.deleteReturnInfoByPI(id);
    }

    @Override
    public void deleteReturnsById(String[] ids) {
        for(String id:ids){
            deleteMapper.deleteReturnInfoByPI(id);
        }
    }

    @Override
    public List<Return> getReturnByID(Integer pn,String id) {
        PageHelper.startPage(pn, 5);// 后面紧跟的这个查询就是分页查询
        List<Return> returns = getMapper.getReturnInfoByID(id);

        for(Return r:returns){
            Medince medince= getMapper.getMedinceByMN(r.getMedinceId());
            r.setMedinceName(medince.getMedinceName());
        }
        return returns;
    }
}
