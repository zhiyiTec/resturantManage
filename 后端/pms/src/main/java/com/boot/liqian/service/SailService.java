package com.boot.liqian.service;

import com.boot.liqian.model.Productor;
import com.boot.liqian.model.User;

import java.util.List;

public interface SailService {
    /**
     * 获取所有的productor
     * @return
     */
    public List<Productor> getProduct(Integer pn);

    /**
     * 修改product表
     * @param pi
     * @param pn
     * @param cn
     * @param cp
     */
    public  void updateProductorByPI(String pi,String pn,String cn,String cp);

    /**
     * 删除供应商
     * @param pi
     */
    public void deleteProduct(String pi);

    /**
     * 批量删除供应商
     * @param pi
     */
    public void deleteProductors(String pi[]);

    /**
     * 添加供应商
     * @param pN
     * @param cN
     * @param cP
     */
    public void addProductor(String pN,String cN,String cP);

    /**
     * 通过供应商名获取供应商
     * @param pn
     * @return
     */
    public List<Productor> getProductorByPN(Integer pn,String pN);
    /**
     * 通过联系人获取供应商
     * @param pn
     * @return
     */
    public List<Productor> getProductorByCN(Integer pn,String cN);
}
