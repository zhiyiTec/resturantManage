package com.boot.liqian.service.serviceImpl;

import com.boot.liqian.mapper.DeleteMapper;
import com.boot.liqian.mapper.GetMapper;
import com.boot.liqian.mapper.SaveMapper;
import com.boot.liqian.mapper.UpdateMapper;
import com.boot.liqian.model.*;
import com.boot.liqian.service.MedinceManageService;
import com.boot.liqian.service.MedinceSaileManageService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.swing.plaf.basic.BasicButtonUI;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

@Service
public class MedinceSaileManageServiceImpl implements MedinceSaileManageService {
    @Autowired
    GetMapper getMapper;
    @Autowired
    UpdateMapper updateMapper;
    @Autowired
    DeleteMapper deleteMapper;
    @Autowired
    SaveMapper saveMapper;


    @Override
    public List<Buyer> getAllBuyers(Integer pn) {
        PageHelper.startPage(pn, 5);// 后面紧跟的这个查询就是分页查询
        List<Buyer> buyers = getMapper.getAllBuyers();

        for (Buyer b : buyers) {
            User user = getMapper.getUserByUserId(b.getUserId());

            b.setBuyerName(user.getUserName());

        }
        return buyers;
    }

    @Override
    public Buyer getByerById(String id) {
        Buyer buyer=getMapper.getBuyerById(id);
        User user = getMapper.getUserByUserId(id);
        buyer.setBuyerName(user.getUserName());
        return buyer;
    }

    @Override
    public void deleteBuyerAndUser(String id) {
        deleteMapper.deleteBuyerById(id);
        deleteMapper.deleteUserByUserId(id);
    }

    @Override
    public void updateUserNameAndBuyerById(String id, String name, String count) {
        Buyer bu=getMapper.getBuyerById(id);
        Integer integral=bu.getIntegral();
        if(integral==null)integral=0;
        Double sunmoney=bu.getSunmoney();
        if(sunmoney==null)sunmoney=0.;


        bu.setCumoney(Double.valueOf(count));
        Double cuMoney=Double.valueOf(count);
        sunmoney+=cuMoney;
        bu.setSunmoney(sunmoney);
        int a= (int) Math.round(cuMoney);
        integral+=a;
        bu.setIntegral(integral);
        updateMapper.updateBuyerByBI(bu);
        User user=new User();
        user.setUserId(id);
        user.setUserName(name);
        updateMapper.updateUserNameByBI(user);
    }

    @Override
    public void deleteByersAndUsersById(String ids[]) {
        for(String id:ids){
            deleteMapper.deleteBuyerById(id);
            deleteMapper.deleteUserByUserId(id);
        }
    }

    @Override
    public void modifyBuyerMPById(String id, String newPass) {
        Buyer buyer=new Buyer();
        buyer.setUserId(id);
        buyer.setMembershipPassword(newPass);
        updateMapper.updateBuyerMPByID(buyer);
    }

    @Override
    public Integer judgeBuyerIsMemebership(String id) {
        Integer re=null;
        Buyer buyer=getMapper.getBuyerById(id);
        if(buyer==null){
            re=1;//说明没有注册
        }else{
            re=0;
        }
        return re;
    }

    @Override
    public Integer phoneIsInUser(String phone) {
        User user=getMapper.getUserByUserId(phone);
        Integer re=null;
        if(user==null){
            re=0;//说明未注册
        }else{
            re=1;//说明已经注册
        }
        return re;
    }

    @Override
    public void addUserAndBuyer(String id, String name, String pass, String ic) {
        User user=new User();
        user.setUserId(id);
        user.setUserName(name);
        user.setPassword(pass);
        user.setUserType(2);
        user.setPasswordToken(ic);
        saveMapper.saveUser(user);
        Buyer buyer=new Buyer();
        buyer.setUserId(id);
        String tiStamp = String.valueOf(Calendar.getInstance().getTimeInMillis());
        String mID = String.valueOf(tiStamp.substring(tiStamp.length() - 6, tiStamp.length()));
        buyer.setMembershipCard(mID);
        buyer.setMembershipPassword(pass);
        buyer.setIntegral(0);
        saveMapper.addBuyer(buyer);
    }

    @Override
    public List<Buyer> getBuyerByName(String name) {
        PageHelper.startPage(1, 50000);// 后面紧跟的这个查询就是分页查询
        List<User> users=getMapper.getAllUserByName(name);
        List<Buyer>buyers=new LinkedList<Buyer>();
        for(User u:users){
            String userId=u.getUserId();
            Buyer buyer=getMapper.getBuyerById(userId);
            buyer.setBuyerName(u.getUserName());
            buyers.add(buyer);
        }
        return buyers;
    }

    @Override
    public List<Buyer> getBuyerByPhone(String phone) {
        List<Buyer>buyers=getMapper.getBuyersById(phone);
        List<Buyer>buyerList=new LinkedList<Buyer>();
        for(Buyer buyer:buyers){
            User user=getMapper.getUserByUserId(buyer.getUserId());
            Buyer buyer1=new Buyer();
            buyer1.setIntegral(buyer.getIntegral());
            buyer1.setMembershipCard(buyer.getMembershipCard());
            buyer1.setUserId(buyer.getUserId());
            buyer1.setBuyerName(user.getUserName());
            buyerList.add(buyer1);
        }
        return buyerList;
    }

    @Override
    public List<Redemption> getAwardList(String id) {
       Buyer buyer= getMapper.getBuyerById(id);
        Integer integral=buyer.getIntegral();
        List<Redemption> redemptionList=getMapper.getAllAwardList();
        List<Redemption> redemptions=new LinkedList<>();
        for (Redemption re:redemptionList){
            if(re.getNumber()<integral)redemptions.add(re);
        }
        return redemptions;
    }

    @Override
    public Redemption getAwardByName(String name) {
        return getMapper.getAwardByName(name);
    }

    @Override
    public void updateBuyerNuByID(String id, String nu) {
        Buyer buyer=new Buyer();
        buyer.setUserId(id);
        buyer.setIntegral(Integer.valueOf(nu));
        updateMapper.updateBuyerNuByID(buyer);
    }


}
