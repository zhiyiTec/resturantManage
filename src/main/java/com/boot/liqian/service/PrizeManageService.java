package com.boot.liqian.service;

import com.boot.liqian.model.Redemption;

import java.util.List;

public interface PrizeManageService {
    /**
     * 获取所有的奖品信息
     * @return
     */
    public List<Redemption> getAllRedemption(Integer pn);

    /**
     * 通过Id获取对应的奖品信息
     * @param id
     * @return
     */
    public Redemption getRedemtionById(Integer id);

    /**
     * 更新奖品信息
     * @param redemption
     * @return
     */
    public Integer updateRedemption(Redemption redemption);

    /**
     * 通过id删除信息
     * @param id
     * @return
     */
    public Integer deletePrizeById(Integer id);

    /**
     * 添加奖品
     * @param redemption
     * @return
     */
    public Integer addPrize(Redemption redemption);

    /**
     * 批量删除奖品
     * @param ids
     * @return
     */
    public Integer deletePrizes(List<Integer> ids);

    public List<Redemption> searchPrizesByName(String name);
}
