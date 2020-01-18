package com.boot.liqian.mapper;

import com.boot.liqian.model.*;

public interface UpdateMapper {
    public void updateUserPassByUserId(User user);//通过用户id更新这个用户在User表中的密码
    public void updateUserInfoByUserId(User user);//通过自增id更新这个用户在User表中的所有信息
    public void updateProductorInfoByPI(Productor productor);//通过productorId来更新productor表中的所有信息
    public void updateMedinceByMI(Medince medince);//通过mdeincrIn更新medince\
    public void updateBuyerByBI(Buyer buyer);//通过userId更新buyer表中的积分

    public void updateUserNameByBI(User user);//通过userId更新user表中的name
    public  void updateBuyerMPByID(Buyer buyer);//通过id修改buyer的会员卡密码
    public  void updateBuyerNuByID(Buyer buyer);//通过id修改buyer的积分数量

    public void updatemedincerestNumberByMI(Medince medince);//通过medinceID更新medince的库存量
    public Integer updatePrizeInfo(Redemption redemption);//更新奖品信息

    public Integer updateRecharge(Recharge recharge);//更新充值记录
    public Integer updateUserByUserId(User user);//通过userid更新user表
}
