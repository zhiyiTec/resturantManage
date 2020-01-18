package com.boot.liqian.service;

import com.boot.liqian.model.User;

import java.util.List;

public interface SuperManageService {
    /**
     * 获取所有的sailers
     * @param
     * @return
     */
    public List<User> getAllSailers(Integer pn);

    /**
     * 通过id,更新user的name,phone,pass
     */
    public void updateSailer(Integer id,String name,String phone,String pass,String passToken);

    /**
     * 通过userId删除用户
     */
    public void deleteSailer(String userId);

    /**
     * 添加销售
     * @param name
     * @param phone
     * @param pass
     * @param passToken
     */
    public void addSailer(String name,String phone,String pass,String passToken);

    /**
     * 批量删除销售人员
     * @param phones
     */
    public void deleteSailers(String phones[]);

    /**
     * 通过姓名模糊匹配sailor
     * @return
     */
    public List<User> getUserByName(String userName);
}
