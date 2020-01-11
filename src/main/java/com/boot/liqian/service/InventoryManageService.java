package com.boot.liqian.service;

import com.boot.liqian.model.Buyer;
import com.boot.liqian.model.Medince;
import com.boot.liqian.model.Redemption;

import java.util.List;

public interface InventoryManageService {
    /**
     * 获取药品的全部信息
     * @return
     */
    public List<Medince> getMedinces(Integer pn);

    /**
     * 获取库存剩余量<10的药品信息
     * @return
     */
    public List<Medince> getLittleMedince();

    /**
     * 按药品名查询
     * @param name
     * @return
     */
    public List<Medince> searchMedinceByName_IM(Integer pn,String name);

    /**
     * 按药品编码查询
     * @param mi
     * @return
     */
    public List<Medince> searchMedinceByMI_IM(Integer pn,String mi);
}
