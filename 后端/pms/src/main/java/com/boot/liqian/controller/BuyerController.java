package com.boot.liqian.controller;

import com.boot.liqian.model.Medince;
import com.boot.liqian.model.MedinceType;
import com.boot.liqian.model.Productor;
import com.boot.liqian.service.BuyerService;
import com.boot.liqian.service.MedinceManageService;
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
public class BuyerController {
	@Autowired
    BuyerService buyerService;

    @ResponseBody
    @RequestMapping(value = "/getAllMedinces_BU",method = RequestMethod.POST)
    public Map<String, Object> getAllMedinceInfo(@RequestParam("pn") Integer pn,
                                                  HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        List<Medince> medinces=buyerService.getMedinceINfo(pn);
        PageInfo page = new PageInfo(medinces, 5);// 5:表示每次只显示5页的导航菜单
        map.put("results", page);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getMedinceINfo_buy",method = RequestMethod.POST)
    public Map<String, Object> getMedinceINfo_buy(@RequestParam("mi") String mi,
                                                 HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("medince", buyerService.getMedinceById(mi));
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/addSail_buy",method = RequestMethod.POST)
    public Map<String, Object> addSail_buy(@RequestParam("mi") String mi,@RequestParam("buyerId") String buyerId,@RequestParam("buyNu") Integer buyNu,
                                                  HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        buyerService.addSail( mi,  buyerId,  buyNu);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/searchMedinceName_buy",method = RequestMethod.POST)
    public Map<String, Object> searchMedinceName_buy(@RequestParam("pn") Integer pn,@RequestParam("name") String name,
                                           HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        List<Medince> medinces=buyerService.getMedinceByName(pn,name);
        PageInfo page = new PageInfo(medinces, 5);// 5:表示每次只显示5页的导航菜单
        map.put("results", page);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/searchorder_buy",method = RequestMethod.POST)
    public Map<String, Object> searchorder_buy(@RequestParam("buyerId") String buyerId,
                                                     HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("sails",  buyerService.getSailByID(buyerId));
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/returnorder_buy",method = RequestMethod.POST)
    public Map<String, Object> returnorder_buy(@RequestParam("medinceId") String medinceId,@RequestParam("userID") String userID,@RequestParam("buyID") String buyID,
                                               HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        buyerService.returnOrder(buyID,userID,medinceId);

        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getjfen",method = RequestMethod.POST)
    public Map<String, Object> getjfen(@RequestParam("userID") String userID,
                                               HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("buyer",buyerService.getJifenByID(userID));
        return map;
    }

}
