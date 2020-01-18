package com.boot.liqian.service;

import com.boot.liqian.model.Buyer;
import com.boot.liqian.model.Redemption;
import com.boot.liqian.model.Sail;

import java.util.List;

public interface SaileManageService {
    /**
     * 获取所有的销售信息
     * @param pn
     * @return
     */
    public List<Sail> getSailInfo(Integer pn);

    /**
     * 通过id删除销售信息
     * @param id
     */
    public void deleteSailByID(String id);

    /**
     * 通过id批量删除销售信息
     * @param ids
     */
    public void deleteSailsByID(String ids[]);

    /**
     * 通过药品名查询
     * @param name
     * @return
     */
    public List<Sail> getSailByName(Integer pn,String name);
    /**
     * 通过药品ID查询
     * @param
     * @return
     */
    public List<Sail> getSailByID(Integer pn,String ID);
}
