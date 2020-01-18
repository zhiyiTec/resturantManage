package com.boot.liqian.service;

import com.boot.liqian.model.User;

public interface LoginService {
    /**
     * 修改密码
     */
    public void modifyPassword(String userId,String newPassword);

    /**
     * 判断密保是否正确
     * @param userId
     * @param passwordToken
     * @return
     */
    public Integer judgePT(String userId,String passwordToken);
    /**
     * 判断用户的密码是否正确
     * @param userId
     * @param password
     * @return
     */
    public Integer comfirmUser(String userId,String password);

    /**
     * 通过用户id获取该用户在User表中所有的信息
     * @param userId
     * @return
     */
    public User getUserByUI(String userId);

    /**
     * 通过用户id更新用户的token
     * @param user
     * @return
     */
    public Integer updateUserTken(User user);

    /**
     * 判断用户token
     * 0：token验证成功
     * 1：token验证失败
     * 2：用户未注册
     * @return
     */
    public Integer judgeToken(String userId,String token);
}
