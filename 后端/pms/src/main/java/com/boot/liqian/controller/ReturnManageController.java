package com.boot.liqian.controller;

import com.boot.liqian.model.Purchase;
import com.boot.liqian.model.Return;
import com.boot.liqian.service.PurchaseManageService;
import com.boot.liqian.service.ReturnManageService;
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
public class ReturnManageController {
    @Autowired
    ReturnManageService returnManageService;

    @ResponseBody
    @RequestMapping(value = "/getAllReturnInfo", method = RequestMethod.POST)
    public Map<String, Object> getAllReturnInfo(@RequestParam("pn") Integer pn,
                                                HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        List<Return> returns = returnManageService.getAllReturnInfo(pn);
        PageInfo page = new PageInfo(returns, 5);// 5:表示每次只显示5页的导航菜单
        map.put("results", page);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteReturn", method = RequestMethod.POST)
    public Map<String, Object> deleteReturn(@RequestParam("pI") String pI,
                                            HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        returnManageService.deleteReturnById(pI);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/deleteReturns", method = RequestMethod.POST)
    public Map<String, Object> deleteReturns(@RequestParam("id") String id,
                                            HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        String re[]=id.split("-");
        returnManageService.deleteReturnsById(re);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/searchReturnByIDs", method = RequestMethod.POST)
    public Map<String, Object> searchReturnByIDs(@RequestParam("pn") Integer pn,@RequestParam("name") String name,
                                             HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        List<Return> returns = returnManageService.getReturnByID(pn,name);
        PageInfo page = new PageInfo(returns, 5);// 5:表示每次只显示5页的导航菜单
        map.put("results", page);
        return map;
    }


}
