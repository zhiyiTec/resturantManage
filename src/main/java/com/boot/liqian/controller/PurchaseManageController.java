package com.boot.liqian.controller;

import com.boot.liqian.model.Buyer;
import com.boot.liqian.model.Purchase;
import com.boot.liqian.service.MedinceSaileManageService;
import com.boot.liqian.service.PurchaseManageService;
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
public class PurchaseManageController {
    @Autowired
    PurchaseManageService purchaseManageService;

    @ResponseBody
    @RequestMapping(value = "/getAllPurchaseInfo", method = RequestMethod.POST)
    public Map<String, Object> getAllPurchaseInfo(@RequestParam("pn") Integer pn,
                                                 HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        List<Purchase> purchases = purchaseManageService.getAllPurchase(pn);
        PageInfo page = new PageInfo(purchases, 5);// 5:表示每次只显示5页的导航菜单
        map.put("results", page);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/deletePurchase", method = RequestMethod.POST)
    public Map<String, Object> deletePurchase(@RequestParam("pI") String pI,
                                                  HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        purchaseManageService.deletePurchaseByPI(pI);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/deletePurchases", method = RequestMethod.POST)
    public Map<String, Object> deletePurchases(@RequestParam("id") String id,
                                              HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        String re[]=id.split("-");
        purchaseManageService.deletePurchasesByPI(re);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/searchPurchaseByName", method = RequestMethod.POST)
    public Map<String, Object> searchPurchaseByName (@RequestParam("pn") Integer pn,@RequestParam("name") String name,
                                               HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        List<Purchase> purchases = purchaseManageService.getPurchaseByName(pn,name);
        PageInfo page = new PageInfo(purchases, 5);// 5:表示每次只显示5页的导航菜单
        map.put("results", page);
        return map;
    }

}
