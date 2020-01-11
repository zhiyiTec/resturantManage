package com.boot.liqian.mapper;

import com.boot.liqian.model.User;

import java.util.List;

public interface DeleteMapper {
    public void deleteUserByUserId(String userId);//通过用户id删除user用户

    public void deleteProductByPI(String productorId);//通过productId删除对应的供应商

    public void deleteMedinceByMI(String medinceId);//通过medinceId删除medince

    public void deleteBuyerById(String userId);//通过userId删除buyer

    public void deleteSailInfoBySI(String buyId);//通过buyId删除sail信息

    public void deletePurchaseBiPI(String purchaseId);//通过id删除purchase

    public void deleteReturnInfoByPI(String returnId);//通过id删除return

    public Integer deletePrizeById(Integer id);//通过奖品id删除一个奖品

    public Integer deletePrizes(List<Integer> ids);//批量删除奖品
}
