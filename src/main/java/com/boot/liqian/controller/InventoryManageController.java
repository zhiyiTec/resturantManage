package com.boot.liqian.controller;

import com.boot.liqian.model.Buyer;
import com.boot.liqian.model.Medince;
import com.boot.liqian.service.InventoryManageService;
import com.boot.liqian.service.MedinceSaileManageService;
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
public class InventoryManageController {
    @Autowired
    InventoryManageService inventoryManageService;

    @ResponseBody
    @RequestMapping(value = "/getMedinceInfo_IM", method = RequestMethod.POST)
    public Map<String, Object> getMedinceInfo_IM(@RequestParam("pn") Integer pn,
                                                 HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        List<Medince> medinces = inventoryManageService.getMedinces(pn);
        PageInfo page = new PageInfo(medinces, 5);// 5:表示每次只显示5页的导航菜单
        map.put("results", page);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getLittleMedince", method = RequestMethod.POST)
    public Map<String, Object> getMedinceInfo_IM(
                                                 HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("littleMedince",inventoryManageService.getLittleMedince());
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/searchMedinceByName_IM", method = RequestMethod.POST)
    public Map<String, Object> searchMedinceByName_IM(@RequestParam("pn") Integer pn,@RequestParam("name") String name,
            HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        List<Medince> medinces = inventoryManageService.searchMedinceByName_IM(pn,name);
        PageInfo page = new PageInfo(medinces, 5);// 5:表示每次只显示5页的导航菜单
        map.put("results", page);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/searchMedinceByMI_IM", method = RequestMethod.POST)
    public Map<String, Object> searchMedinceByMI_IM(@RequestParam("pn") Integer pn,@RequestParam("MI") String MI,
                                                      HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        List<Medince> medinces = inventoryManageService.searchMedinceByMI_IM(pn,MI);
        PageInfo page = new PageInfo(medinces, 5);// 5:表示每次只显示5页的导航菜单
        map.put("results", page);
        return map;
    }
}
