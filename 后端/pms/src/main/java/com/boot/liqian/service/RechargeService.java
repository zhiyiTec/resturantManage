package com.boot.liqian.service;

import com.boot.liqian.model.Rechagerecord;
import com.boot.liqian.model.Recharge;

import java.util.List;

public interface RechargeService {
    /**
     * 获取所有的充值信息
     * @param pn
     * @return
     */
    public List<Recharge> getAllRecharge(Integer pn);

    /**
     * 删除指定的充值信息以及充值记录
     * @param userId
     * @return
     */
    public Integer deleteRechargeInfo(String userId);

    /**
     * 通过用户id获取其所有的用户信息
     * @param userid
     * @return
     */
    public List<Rechagerecord> getAllRechargeRecords(String userid);
}
