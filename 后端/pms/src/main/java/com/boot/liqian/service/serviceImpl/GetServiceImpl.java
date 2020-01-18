package com.boot.liqian.service.serviceImpl;
import com.boot.liqian.mapper.GetMapper;
import com.boot.liqian.model.Productor;
import com.boot.liqian.model.User;
import com.boot.liqian.service.GetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetServiceImpl implements GetService {
    @Autowired
    GetMapper getMapper;
    @Override
    public User getUserInfoByUserId(String userId) {
        User user=getMapper.getUserByUserId(userId);
        return user;
    }

    @Override
    public Productor getProductById(String pi) {
        return getMapper.getProductByPI(pi);
    }
}
