package com.boot.liqian.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.boot.liqian.model.User;
import com.boot.liqian.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	@Autowired
    LoginService loginService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @ResponseBody
    @RequestMapping("/judgePasswordToken")
    public Map<String, Object> judgePasswordToken(@RequestParam("userId") String userId,@RequestParam("passwordToken") String passwordToken,
                                                  HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        Integer re=loginService.judgePT(userId,passwordToken);
        map.put("status",re);
        return map;
    }
	/**
	 * 修改密码
	 * 
	 * @param userId
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/modifyPassword")
	public Map<String, Object> getUserInfo(@RequestParam("userId") String userId,@RequestParam("newPassword") String newPassword,
                                           HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
		Map<String, Object> map = new HashMap<String, Object>();
		loginService.modifyPassword(userId,newPassword);

		return map;
	}
    /**
     * 登录
     *
     * @param
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Map<String, Object> login(@RequestParam("userId") String userId,@RequestParam("password") String password,
                                     HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        HttpSession session=request.getSession();
        User user=new User();
        user.setUserId(userId);
        user.setToken(session.getId());

        Map<String, Object> map = new HashMap<String, Object>();
        Integer loginRe=loginService.comfirmUser(userId,password);
        User user1=loginService.getUserByUI(userId);
        map.put("loginRe",loginRe);
        if(user1!=null){
            map.put("user",user1);
            Integer up_re=loginService.updateUserTken(user);
            if(up_re==0)  logger.error("token更新失败");
            else logger.info("token更新成功");

        }else{
            map.put("user",0);
        }

        return map;
    }
    /**
     * 判断用户token
     *re:
     * 0：token验证成功
     * 1：token验证失败
     * 2：用户未注册
     * @param
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/judgeToken",method = RequestMethod.POST)
    public Map<String, Object> judgeToken(@RequestParam("userId") String userId,
                                     HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        HttpSession session=request.getSession();
        Map<String, Object> map = new HashMap<String, Object>();
        Integer re=loginService.judgeToken(userId,session.getId());
        map.put("re",re);
        return map;
    }
}
