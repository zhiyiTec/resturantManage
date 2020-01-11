package com.boot.liqian.controller;

import com.boot.liqian.model.User;
import com.boot.liqian.service.LoginService;
import com.boot.liqian.service.SuperManageService;
import com.github.pagehelper.PageHelper;
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
public class SuperManageController {
	@Autowired
    SuperManageService superManageService;

    /**
     * 获取所有的销售人员
     * @param pn
     * @param response
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllSailers",method = RequestMethod.POST)
    public Map<String, Object> getAllSailers(@RequestParam("pn") Integer pn, HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        List<User> sailers=superManageService.getAllSailers(pn);
        PageInfo page = new PageInfo(sailers, 5);// 5:表示每次只显示5页的导航菜单
        map.put("results", page);
        return map;
    }

    /**
     * 更新销售
     * @param id
     * @param name
     * @param phone
     * @param pass
     * @param passToken
     * @param response
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateSailer",method = RequestMethod.POST)
    public Map<String, Object> updateSailer(@RequestParam("id") Integer  id,@RequestParam("name") String  name, @RequestParam("phone") String  phone, @RequestParam("pass") String  pass, @RequestParam("passToken") String  passToken,HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        superManageService.updateSailer(id,name,phone,pass,passToken);
        return map;
    }

    /**
     * 删除sailer
     * @param phone
     * @param response
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteSailer",method = RequestMethod.POST)
    public Map<String, Object> deleteSailer(@RequestParam("phone") String  phone,HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        superManageService.deleteSailer(phone);
        return map;
    }

    /**
     * 添加销售
     * @param phone
     * @param response
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addSailer",method = RequestMethod.POST)
    public Map<String, Object> addSailer(@RequestParam("name") String  name,@RequestParam("pas") String  pas,@RequestParam("IC") String  IC,@RequestParam("phone") String  phone,HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        superManageService.addSailer(name,phone,pas,IC);
        return map;
    }

    /**
     * 批量删除
     * @param phones
     * @param response
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteSailers",method = RequestMethod.POST)
    public Map<String, Object> deleteSailers(@RequestParam("phones") String  phones,HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        String re[]=phones.split("-");
        superManageService.deleteSailers(re);
        return map;
    }

    /**
     * 通过姓名迷糊匹配用户
     * @param name
     * @param response
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getSailerbyName",method = RequestMethod.POST)
    public Map<String, Object> getSailerbyName(@RequestParam("name") String  name,HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        List<User> sailers=superManageService.getUserByName(name);
        PageInfo page = new PageInfo(sailers, 5);// 5:表示每次只显示5页的导航菜单
        map.put("result",page);
        return map;
    }
}
