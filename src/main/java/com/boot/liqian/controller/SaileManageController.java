package com.boot.liqian.controller;

import com.boot.liqian.model.Buyer;
import com.boot.liqian.model.Sail;
import com.boot.liqian.service.MedinceSaileManageService;
import com.boot.liqian.service.SaileManageService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SaileManageController {
    @Autowired
    SaileManageService saileManageService;

    @ResponseBody
    @RequestMapping(value = "/getSaileInfo_SM", method = RequestMethod.POST)
    public Map<String, Object> getAllMedinceInfo(@RequestParam("pn") Integer pn,
                                                 HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        List<Sail> sails = saileManageService.getSailInfo(pn);
        PageInfo page = new PageInfo(sails, 5);// 5:表示每次只显示5页的导航菜单
        map.put("results", page);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/deleteSailInfo_SM", method = RequestMethod.POST)
    public Map<String, Object> deleteSailInfo_SM(@RequestParam("sI") String sI,
                                                 HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        saileManageService.deleteSailByID(sI);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/deleteSails_SM", method = RequestMethod.POST)
    public Map<String, Object> deleteSails_SM(@RequestParam("id") String id,
                                                 HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        String re[]=id.split("-");
        saileManageService.deleteSailsByID(re);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/searchSailByName", method = RequestMethod.POST)
    public Map<String, Object> searchSailByName(@RequestParam("pn") Integer pn,@RequestParam("name") String name,
                                              HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        List<Sail> sails = saileManageService.getSailByName(pn,name);
        PageInfo page = new PageInfo(sails, 5);// 5:表示每次只显示5页的导航菜单
        map.put("results", page);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/searchSailByMI", method = RequestMethod.POST)
    public Map<String, Object> searchbyID_SM(@RequestParam("pn") Integer pn,@RequestParam("MI") String MI,
                                                HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        List<Sail> sails = saileManageService.getSailByID(pn,MI);
        PageInfo page = new PageInfo(sails, 5);// 5:表示每次只显示5页的导航菜单
        map.put("results", page);
        return map;
    }
}
