package com.boot.liqian.controller;

import com.boot.liqian.model.Medince;
import com.boot.liqian.model.Productor;
import com.boot.liqian.model.User;
import com.boot.liqian.service.GetService;
import com.boot.liqian.service.LoginService;
import com.boot.liqian.service.MedinceManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommonController {
	@Autowired
    GetService getService;
    @Autowired
    MedinceManageService medinceManageService;
    /**
     * 通过userId获取user在user表中的信息
     * @param userId
     * @param response
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUser",method = RequestMethod.POST)
    public Map<String, Object> judgePasswordToken(@RequestParam("userId") String userId, HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        User user=getService.getUserInfoByUserId(userId);
        map.put("user",user);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getProduct",method = RequestMethod.POST)
    public Map<String, Object> getProduct(@RequestParam("productorId") String productorId, HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        Productor productor=getService.getProductById(productorId);
        map.put("productor",productor);
        return map;
    }


    @ResponseBody
    @RequestMapping(value = "/getMedince",method = RequestMethod.POST)
    public Map<String, Object> getMedince(@RequestParam("mI") String mI, HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        Medince medince=medinceManageService.getMedinceByMI(mI);
        map.put("medince",medince);

        return map;
    }
}
