package com.boot.liqian.service;

import com.boot.liqian.model.*;

import java.util.List;

public interface MedinceSaileManageService {
    /**
     * 获取所有客户信息
     * @return
     */
    public List<Buyer> getAllBuyers(Integer pn);

    /**
     * 通过id获取buyer对应的信息
     * @param id
     * @return
     */
    public Buyer getByerById(String id);

    /**
     * 通过id删除user表，以及buyer表中的信息
     * @param id
     */
    public void deleteBuyerAndUser(String id);

    /**
     * 通过id更新user表中的name以及buyer表中的信息
     */
    public void updateUserNameAndBuyerById(String id,String name,String count);

    /**
     * 批量删除user和byer
     * @param
     */
    public void deleteByersAndUsersById(String ids[]);

    /**
     * 通过id修改buyer的会员卡密码
     * @param id
     * @param newPass
     */
    public void modifyBuyerMPById(String id,String newPass);

    /**
     * 判断用户是否有会员卡
     * 1:未注册
     * 0：已经注册
     * @param id
     * @return
     */
    public Integer judgeBuyerIsMemebership(String id);

    /**
     * 判断号码是否已经注册
     * 0:未注册
     * 1:已经注册
     * @param phone
     * @return
     */
    public Integer phoneIsInUser(String phone);

    /**
     * 向user表以及buyer表添加新用户
     * @param id
     * @param name
     * @param pass
     * @param ic
     */
    public  void addUserAndBuyer(String id,String name,String pass,String ic);

    /**
     * 通过姓名获取buyer
     * @param name
     * @return
     */
    public List<Buyer> getBuyerByName(String name);

    /**
     * 通过电话获取buyer
     * @param phone
     * @return
     */
    public List<Buyer> getBuyerByPhone(String phone);

    /**
     * 获取积分列表中的内容
     * @return
     */
    public List<Redemption> getAwardList(String id);

    /**
     * 通过名字获取对应的积分数量
     * @return
     */
    public Redemption getAwardByName(String name);

    /**
     * 通过id修改buyer的积分数量
     *
     */
    public  void updateBuyerNuByID(String id,String nu);

    /**
     * 会员充值功能
     * 0:添加充值记录失败
     * 1：添加充值信息失败
     *
     * 2：添加成功
     * @param rechagerecord
     * @return
     */
    public Integer recharge(Rechagerecord rechagerecord);

    /**
     * 实现用户的消费功能
     * @param spend
     * @return
     */
    public Integer spend(Spend spend);

    /**
     * 用过用户id获取账户额度信息
     * @param id
     * @return
     */
    public Recharge getCountInfo(String id);
}
