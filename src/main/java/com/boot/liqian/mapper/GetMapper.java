package com.boot.liqian.mapper;

import com.boot.liqian.model.*;

import java.util.List;

public interface GetMapper {
    public User getUserByUserId(String userId);//通过用户id获取这个用户在User表中的所有信息
    public List<User> getAllKindsUsers(Integer userType);//通过userType获取所有user所有该类型的用户
    public List<User> getAllUserByName(String userName);//通过姓名模糊匹配用户
    public List<Productor> getAllProduct();//获取供应商列表
    public Productor getProductByPI(String productorId);//通过productId获取对应的product信息
    public List<Productor> getProductorByPN(String productorName);//通过供应商名获取供应商信息
    public List<Productor> getProductorBycN(String connector);//通过联系人获取供应商信息
    public List<Medince> getAllMedince();//获取药品信息
    public Medince getMedinceByMI(String medinceId);//通过药品id获取药品信息
    public List<MedinceType> getMedinceType();//获取药品类型
    public List<MedinceType> getMTByMN(String cotent);//通过medinceCotent获取type
    public MedinceType getMedinceTypeByType(String type);//通过type获取对应的描述
    public  Medince getMedinceByMN(String medinceId);//通过medinceId获取Medince
    public  Productor getProductByPN(String productorName);//通过productorName获取Productor
    public  List<Medince> getMedinceWithName(String medinceName);//通过药品名模糊查询
    public  List<Medince> getMedinceWithMI(String medinceId);//通过药品编码模糊查询
    public List<Buyer> getAllBuyers();//获取所有的客户信息
    public Buyer getBuyerById(String userId);//通过userId获取buyer表中对应的信息
    public List<Buyer> getBuyersById(String userId);//通过userId获取迷糊查询buyer表中对应的信息
    public List<Redemption> getAllAwardList();//获取所有的积分列表内容
    public Redemption getAwardByName(String award);//通过名字获取对应的积分数量
    public List<Medince> getLittleMedince();//查询所有库存剩余量<10的药品
    public List<Sail> getSailInfo();//获取所有的销售信息
    public List<Sail> getSsailByName(String medinceName);//通过药品名模糊查询
    public List<Sail> getSsailByID(String medinceId);//通过药品ID模糊查询
    public List<Sail> getSailByID(String buyerId);//通过用户ID精准查询
    public Sail getSailByBuyID(String buyId);//通过BuyID精准查询
    public List<Purchase> getAllPurchase();//获取所有进货信息
    public List<Purchase> getPurchaseByID(String medinceId);//通过药品ID查询
    public List<Return> getALlReturnInfo();//查询所有退货信息
    public List<Return> getReturnInfoByID(String medinceId);//通过药品ID查询所模糊查询Return
    public MedinceRedemption getMRByID(String medinceId);//通过药品id获取对应的积分
    public List<Redemption> getRedemption();//获取所有的奖品
    public Redemption getRedemptionById(Integer id);//通过奖品id获取对应的信息
    public List<Redemption> getPrizesByName(String award);//通过奖品名模糊查询奖品
}
