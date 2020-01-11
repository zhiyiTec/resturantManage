package com.boot.liqian.controller;

import com.boot.liqian.model.Medince;
import com.boot.liqian.model.MedinceType;
import com.boot.liqian.model.Productor;
import com.boot.liqian.service.MedinceManageService;
import com.boot.liqian.service.SailService;
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
public class MedinceController {
	@Autowired
    MedinceManageService medinceManageService;

    @ResponseBody
    @RequestMapping(value = "/getAllMedinceInfo",method = RequestMethod.POST)
    public Map<String, Object> getAllMedinceInfo(@RequestParam("pn") Integer pn,
                                                  HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        List<Medince> medinces=medinceManageService.getAllMedinces(pn);
        PageInfo page = new PageInfo(medinces, 5);// 5:表示每次只显示5页的导航菜单
        map.put("results", page);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/updateMedince",method = RequestMethod.POST)
    public Map<String, Object> updateMedince(@RequestParam("mI") String mI,@RequestParam("mN") String mN,@RequestParam("bN") String bN,@RequestParam("rN") String rN,@RequestParam("funcional") String funcional,@RequestParam("price") String price,
                                                 HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        medinceManageService.updateMedince(mI,mN,bN,rN,funcional,price);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteMedince",method = RequestMethod.POST)
    public Map<String, Object> deleteMedince(@RequestParam("mI") String mI,
                                             HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        medinceManageService.delteMedince(mI);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteMedinces",method = RequestMethod.POST)
    public Map<String, Object> deleteMedinces(@RequestParam("mIs") String mIs,
                                             HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
       String re[]=mIs.split("-");
       medinceManageService.delteMedinces(re);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getMedinceType",method = RequestMethod.POST)
    public Map<String, Object> getMedinceType(
                                              HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        List<MedinceType> medinceTypes=medinceManageService.getMedinceType();
        map.put("medinceTypes",medinceTypes);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getAllProductor",method = RequestMethod.POST)
    public Map<String, Object> getAllProductor(
            HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        List<Productor>productors=medinceManageService.getAllPro();
        map.put("productors",productors);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/judgeMedince",method = RequestMethod.POST)
    public Map<String, Object> judgeMedince(@RequestParam("mI") String mI,
                                          HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        Integer re=medinceManageService.judgeMedinceByMI(mI);
        map.put("status",re);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/addMedince",method = RequestMethod.POST)
    public Map<String, Object> addMedince(@RequestParam("medinceNu") String medinceNu,@RequestParam("overTime") String overTime,@RequestParam("purchasePr") String purchasePr,@RequestParam("mI") String mI,@RequestParam("time") String time,@RequestParam("mt") String mt,@RequestParam("mn") String mn,@RequestParam("bn") Double bn,@RequestParam("mf") String mf,@RequestParam("mP") String mP,@RequestParam("mp") Double mp,
            HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        medinceManageService.addMedince(overTime,purchasePr,mI,mt,mn,bn,time,mf,mp,mP,medinceNu);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/judgeMedincetype",method = RequestMethod.POST)
    public Map<String, Object> judgeMedincetype(@RequestParam("mC") String mC,
                                          HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        Integer re=medinceManageService.judgeMedinceType(mC);
        map.put("status",re);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/addMedincetype",method = RequestMethod.POST)
    public Map<String, Object> addMedincetype(@RequestParam("mC") String mC,
                                                HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        medinceManageService.addMedinceType(mC);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/searchMedinceByName",method = RequestMethod.POST)
    public Map<String, Object> searchMedinceByName(@RequestParam("serchCotent") String serchCotent,
                                              HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        List<Medince> medinces=medinceManageService.searchMedinceByName(serchCotent);
        PageInfo page = new PageInfo(medinces, 5);// 5:表示每次只显示5页的导航菜单
        map.put("results", page);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/searchMedinceByMI",method = RequestMethod.POST)
    public Map<String, Object> searchMedinceByMI(@RequestParam("serchCotent") String serchCotent,
                                                   HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        List<Medince> medinces=medinceManageService.searchMedinceByMI(serchCotent);
        PageInfo page = new PageInfo(medinces, 5);// 5:表示每次只显示5页的导航菜单
        map.put("results", page);
        return map;
    }
}
