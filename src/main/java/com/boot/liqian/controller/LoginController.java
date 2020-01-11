package com.boot.liqian.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boot.liqian.model.User;
import com.boot.liqian.service.LoginService;
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
        Map<String, Object> map = new HashMap<String, Object>();
        Integer loginRe=loginService.comfirmUser(userId,password);
        User user=loginService.getUserByUI(userId);
        map.put("loginRe",loginRe);
        if(user!=null){
            map.put("user",user);
        }else{
            map.put("user",0);
        }

        return map;
    }
	
}
