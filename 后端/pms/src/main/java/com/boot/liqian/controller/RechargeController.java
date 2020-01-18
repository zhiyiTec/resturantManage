package com.boot.liqian.controller;

import com.boot.liqian.model.Buyer;
import com.boot.liqian.model.Rechagerecord;
import com.boot.liqian.model.Recharge;
import com.boot.liqian.service.MedinceSaileManageService;
import com.boot.liqian.service.RechargeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RechargeController {
    @Autowired
    RechargeService rechargeService;

    @ResponseBody
    @RequestMapping(value = "/getAllRecharge", method = RequestMethod.POST)
    public Map<String, Object> getAllRecharge(@RequestParam("pn") Integer pn,
                                                 HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        List<Recharge> buyers = rechargeService.getAllRecharge(pn);
        PageInfo page = new PageInfo(buyers, 5);// 5:表示每次只显示5页的导航菜单
        map.put("results", page);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/deleteRecharge", method = RequestMethod.POST)
    public Map<String, Object> getAllRecharge(@RequestParam("sI") String sI,
                                              HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        Integer re=rechargeService.deleteRechargeInfo(sI);
        map.put("re", re);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/getRechargeRecord", method = RequestMethod.POST)
    public Map<String, Object> getRechargeRecord(@RequestParam("id") String id,
                                              HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        List<Rechagerecord>rechagerecords=rechargeService.getAllRechargeRecords(id);
        map.put("re",rechagerecords);
        return map;
    }

}
