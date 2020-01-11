package com.boot.liqian.controller;

import com.boot.liqian.model.Productor;
import com.boot.liqian.model.User;
import com.boot.liqian.service.LoginService;
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
public class SailController {
	@Autowired
    SailService sailService;

    @ResponseBody
    @RequestMapping(value = "/getAllProduct",method = RequestMethod.POST)
    public Map<String, Object> getProduct(@RequestParam("pn") Integer pn,
                                                  HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        List<Productor> productors=sailService.getProduct(pn);
        PageInfo page = new PageInfo(productors, 5);// 5:表示每次只显示5页的导航菜单
        map.put("results", page);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/updateProductor",method = RequestMethod.POST)
    public Map<String, Object> updateProductor(@RequestParam("pI") String pI,@RequestParam("pN") String pN,@RequestParam("cN") String cN,@RequestParam("cP") String cP,
                                          HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        sailService.updateProductorByPI(pI,pN,cN,cP);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/deleteProductor",method = RequestMethod.POST)
    public Map<String, Object> deleteProductor(@RequestParam("pI") String pI,
                                               HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        sailService.deleteProduct(pI);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/deleteProductors",method = RequestMethod.POST)
    public Map<String, Object> deleteProductors(@RequestParam("pIs") String pIs,
                                               HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
       String pI[]=pIs.split("-");
       sailService.deleteProductors(pI);
        return map;
    }


    @ResponseBody
    @RequestMapping(value = "/addProductor",method = RequestMethod.POST)
    public Map<String, Object> addProductor(@RequestParam("pN") String pN,@RequestParam("cN") String cN,@RequestParam("cP") String cP,
                                                HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        sailService.addProductor(pN,cN,cP);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/searchProductorbypn",method = RequestMethod.POST)
    public Map<String, Object> searchProductorbypn(@RequestParam("pN") String pN,
                                            HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        List<Productor> productors=sailService.getProductorByPN(1,pN);
        PageInfo page = new PageInfo(productors, 5);// 5:表示每次只显示5页的导航菜单
        map.put("results", page);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/searchProductorbycN",method = RequestMethod.POST)
    public Map<String, Object> searchProductorbycN(@RequestParam("cN") String pN,
                                                   HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        List<Productor> productors=sailService.getProductorByCN(1,pN);
        PageInfo page = new PageInfo(productors, 5);// 5:表示每次只显示5页的导航菜单
        map.put("results", page);
        return map;
    }
}
