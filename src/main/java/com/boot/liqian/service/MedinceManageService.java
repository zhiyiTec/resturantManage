package com.boot.liqian.service;

import com.boot.liqian.model.Medince;
import com.boot.liqian.model.MedinceType;
import com.boot.liqian.model.Productor;

import java.util.List;

public interface MedinceManageService {
    /**
     * 获取所有的药品信息
     * @return
     */
    public List<Medince> getAllMedinces(Integer pn);

    /**
     * 通过药品id获取药品信息
     * @param mi
     * @return
     */
    public Medince getMedinceByMI(String mi);

    /**
     * 更新medince
     */
    public void updateMedince(String mi,String mn,String bn,String rn,String fun,String pr);

    /**
     * 删除medince
     * @param mi
     */
    public void delteMedince(String mi);

    /**
     * 批量删除medince
     * @param mis
     */
    public void delteMedinces(String mis[]);

    /**
     * 获取已经录入的所有商品类型
     * @return
     */
    public List<MedinceType> getMedinceType();

    /**
     * 获取所有的供应商信息
     * @return
     */
    public List<Productor> getAllPro();

    /**
     * 判断药物是否存在
     * @param mi
     * @return
     */
    public Integer judgeMedinceByMI(String mi);
    /**
     * 添加新药物
     * @param mI
     * @param cotent
     * @param medinceName
     * @param buyNumber
     * @param productTime
     * @param functional
     * @param price
     * @param pN
     */
    public void addMedince(String oT,String purchase,String mI,String cotent,String medinceName,Double buyNumber,String productTime,String functional,Double price,String pN,String medinceReNU);

    /**
     * 通过药品类别判断该药品类别是否存在
     * @param mc
     * @return
     */
    public Integer judgeMedinceType(String mc);

    /**
     * 添加药品类别
     */
    public void addMedinceType(String cotent);

    /**
     * 通过药品名查询
     * @param name
     * @return
     */
    public List<Medince> searchMedinceByName(String name);

    /**
     * 通过药品编码查询
     * @param mi
     * @return
     */
    public List<Medince> searchMedinceByMI(String mi);


}
