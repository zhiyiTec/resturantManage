package com.boot.liqian.service.serviceImpl;
import com.boot.liqian.mapper.DeleteMapper;
import com.boot.liqian.mapper.GetMapper;
import com.boot.liqian.mapper.SaveMapper;
import com.boot.liqian.mapper.UpdateMapper;
import com.boot.liqian.model.Productor;
import com.boot.liqian.model.User;
import com.boot.liqian.service.GetService;
import com.boot.liqian.service.SailService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class SailServiceImpl implements SailService {
    @Autowired
    GetMapper getMapper;
    @Autowired
    UpdateMapper updateMapper;
    @Autowired
    DeleteMapper deleteMapper;
    @Autowired
    SaveMapper saveMapper;
    @Override
    public List<Productor> getProduct(Integer pn) {
        // 表示从第pn查，每一页显示10条数据
        PageHelper.startPage(pn, 5);// 后面紧跟的这个查询就是分页查询
        List<Productor> productors=getMapper.getAllProduct();

        return productors;
    }

    @Override
    public void updateProductorByPI(String pi, String pn, String cn, String cp) {
        Productor productor=new Productor();
        productor.setProductorId(pi);
        productor.setProductorName(pn);
        productor.setConnector(cn);
        productor.setConPhone(cp);
        updateMapper.updateProductorInfoByPI(productor);
    }

    @Override
    public void deleteProduct(String pi) {
        deleteMapper.deleteProductByPI(pi);
    }

    @Override
    public void deleteProductors(String[] pi) {
        for(String p:pi){
            deleteMapper.deleteProductByPI(p);
        }
    }

    @Override
    public void addProductor(String pN, String cN, String cP) {
        String tiStamp = String.valueOf(Calendar.getInstance().getTimeInMillis());
        String pI = tiStamp.substring(tiStamp.length() - 4, tiStamp.length());
        Productor productor=new Productor();
        productor.setProductorId(pI);
        productor.setProductorName(pN);
        productor.setConnector(cN);
        productor.setConPhone(cP);
        saveMapper.addProductor(productor);
    }

    @Override
    public List<Productor> getProductorByPN(Integer pn,String pN) {
        PageHelper.startPage(pn, 5);// 后面紧跟的这个查询就是分页查询
        List<Productor> productors=getMapper.getProductorByPN(pN);

        return productors;
    }

    @Override
    public List<Productor> getProductorByCN(Integer pn, String cN) {
        PageHelper.startPage(pn, 5);// 后面紧跟的这个查询就是分页查询
        List<Productor> productors=getMapper.getProductorBycN(cN);

        return productors;
    }
}
