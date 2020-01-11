package com.boot.liqian.service.serviceImpl;

import com.boot.liqian.mapper.DeleteMapper;
import com.boot.liqian.mapper.GetMapper;
import com.boot.liqian.mapper.SaveMapper;
import com.boot.liqian.mapper.UpdateMapper;
import com.boot.liqian.model.User;
import com.boot.liqian.service.LoginService;
import com.boot.liqian.service.SuperManageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SuperManageServiceImpl implements SuperManageService {
    @Autowired
    GetMapper getMapper;
    @Autowired
    UpdateMapper updateMapper;
    @Autowired
    DeleteMapper deleteMapper;
    @Autowired
    SaveMapper saveMapper;
    @Override
    public List<User> getAllSailers(Integer pn) {
        // 表示从第pn查，每一页显示10条数据
        PageHelper.startPage(pn, 5);// 后面紧跟的这个查询就是分页查询
        List<User> sailers=getMapper.getAllKindsUsers(1);

        return sailers;
    }

    @Override
    public void updateSailer(Integer id, String name, String phone, String pass,String passToken) {
        User user=new User();
        user.setId(id);
        user.setUserName(name);
        user.setUserId(phone);
        user.setPassword(pass);
        user.setPasswordToken(passToken);
        updateMapper.updateUserInfoByUserId(user);
    }

    @Override
    public void deleteSailer(String userId) {
        deleteMapper.deleteUserByUserId(userId);
    }

    @Override
    public void addSailer(String name, String phone, String pass, String passToken) {
        User user=new User();
        user.setUserName(name);
        user.setPasswordToken(passToken);
        user.setUserId(phone);
        user.setPassword(pass   );
        user.setUserType(1);
        saveMapper.saveUser(user);
    }

    @Override
    public void deleteSailers(String[] phones) {
        for(String p:phones){
            deleteMapper.deleteUserByUserId(p);
        }
    }

    @Override
    public List<User> getUserByName(String userName) {
        PageHelper.startPage(1, 10);// 后面紧跟的这个查询就是分页查询
        return getMapper.getAllUserByName(userName);
    }
}
