package com.boot.liqian.controller;

import com.boot.liqian.model.Buyer;
import com.boot.liqian.model.Medince;
import com.boot.liqian.model.MedinceType;
import com.boot.liqian.model.Productor;
import com.boot.liqian.service.MedinceManageService;
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
public class MedinceSaileManageController {
    @Autowired
    MedinceSaileManageService medinceSaileManageService;

    @ResponseBody
    @RequestMapping(value = "/getAllBuyer", method = RequestMethod.POST)
    public Map<String, Object> getAllMedinceInfo(@RequestParam("pn") Integer pn,
                                                 HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        List<Buyer> buyers = medinceSaileManageService.getAllBuyers(pn);
        PageInfo page = new PageInfo(buyers, 5);// 5:表示每次只显示5页的导航菜单
        map.put("results", page);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getBuyerById", method = RequestMethod.POST)
    public Map<String, Object> getBuyerById(@RequestParam("bI") String bI,
                                            HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        Buyer buyer = medinceSaileManageService.getByerById(bI);
        map.put("buyer", buyer);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBuyer", method = RequestMethod.POST)
    public Map<String, Object> deleteBuyer(@RequestParam("bI") String bI,
                                           HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        medinceSaileManageService.deleteBuyerAndUser(bI);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public Map<String, Object> updateUser(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("cout") String cout,
                                          HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        medinceSaileManageService.updateUserNameAndBuyerById(id, name, cout);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteByers", method = RequestMethod.POST)
    public Map<String, Object> deleteByers(@RequestParam("id") String id,
                                           HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        String re[] = id.split("-");
        medinceSaileManageService.deleteByersAndUsersById(re);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/modifyMP", method = RequestMethod.POST)
    public Map<String, Object> modifyMP(@RequestParam("phone") String phone, @RequestParam("newPass") String newPass,
                                        HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        medinceSaileManageService.modifyBuyerMPById(phone, newPass);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/judgePM", method = RequestMethod.POST)
    public Map<String, Object> judgePM(@RequestParam("phone") String phone,
                                       HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        Integer re = medinceSaileManageService.judgeBuyerIsMemebership(phone);
        map.put("status", re);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/judgeBuyerIsRegit", method = RequestMethod.POST)
    public Map<String, Object> judgeBuyerIsRegit(@RequestParam("phone") String phone,
                                                 HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        Integer re = medinceSaileManageService.phoneIsInUser(phone);
        map.put("status", re);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/addUserAndBuyer", method = RequestMethod.POST)
    public Map<String, Object> addUserAndBuyer(@RequestParam("phone") String phone, @RequestParam("name") String name, @RequestParam("IC") String IC, @RequestParam("pas") String pas,
                                               HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        medinceSaileManageService.addUserAndBuyer(phone, name, pas, IC);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/seachBuyerByName", method = RequestMethod.POST)
    public Map<String, Object> seachBuyerByName(@RequestParam("name") String name,
                                                HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        List<Buyer> buyers = medinceSaileManageService.getBuyerByName(name);
        PageInfo page = new PageInfo(buyers, 5);// 5:表示每次只显示5页的导航菜单
        map.put("results", page);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/seachBuyerByPhone", method = RequestMethod.POST)
    public Map<String, Object> seachBuyerByPhone(@RequestParam("phone") String phone,
                                                 HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        List<Buyer> buyers = medinceSaileManageService.getBuyerByPhone(phone);
        PageInfo page = new PageInfo(buyers, 5);// 5:表示每次只显示5页的导航菜单
        map.put("results", page);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getAward", method = RequestMethod.POST)
    public Map<String, Object> getAward(@RequestParam("id") String id,
                                        HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("award", medinceSaileManageService.getAwardList(id));
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getAwardNumberByName", method = RequestMethod.POST)
    public Map<String, Object> getAwardNumberByName(@RequestParam("award") String award,
                                        HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("award", medinceSaileManageService.getAwardByName(award));
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/AwardSuc", method = RequestMethod.POST)
    public Map<String, Object> AwardSuc(@RequestParam("id") String id,@RequestParam("number") String number,
                                                    HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        medinceSaileManageService.updateBuyerNuByID(id,number);
        return map;
    }
}
