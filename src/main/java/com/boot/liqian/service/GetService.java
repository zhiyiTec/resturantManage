package com.boot.liqian.service;

import com.boot.liqian.model.Productor;
import com.boot.liqian.model.User;

public interface GetService {
    /**
     * 通过userId获取user在user表中的信息
     * @param userId
     * @return
     */
    public User getUserInfoByUserId(String userId);

    /**
     * 通过productId获取对应的product信息
     * @param pi
     * @return
     */
    public Productor getProductById(String pi);
}
