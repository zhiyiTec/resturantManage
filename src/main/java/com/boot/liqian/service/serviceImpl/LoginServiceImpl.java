package com.boot.liqian.service.serviceImpl;

import com.boot.liqian.mapper.GetMapper;
import com.boot.liqian.mapper.UpdateMapper;
import com.boot.liqian.model.User;
import com.boot.liqian.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    GetMapper getMapper;
    @Autowired
    UpdateMapper updateMapper;
    @Override
    public void modifyPassword(String userId,String newPassword) {
        User user1=new User();
        user1.setUserId(userId);
        user1.setPassword(newPassword);
        updateMapper.updateUserPassByUserId(user1);




    }

    @Override
    public Integer judgePT(String userId, String passwordToken) {
        Integer re=null;
        User user=getMapper.getUserByUserId(userId);
        if(user==null){
            re=0;//没有这个用户
        }else if(passwordToken.equals(user.getPasswordToken())){
            re=1;//表示验证通过
        }else{
            re=2;//表示密保验证失败
        }
        return re;
    }

    @Override
    public Integer comfirmUser(String userId, String password) {
        Integer re=0;//表示密码验证未通过
        User user=getMapper.getUserByUserId(userId);
        if(user==null){
            re=1;//表示没有这个用户
        }else  if(password.equals(user.getPassword())){
            re=2;//表示密码验证通过
        }
        return re;
    }

    @Override
    public User getUserByUI(String userId) {
        return getMapper.getUserByUserId(userId);
    }
}
