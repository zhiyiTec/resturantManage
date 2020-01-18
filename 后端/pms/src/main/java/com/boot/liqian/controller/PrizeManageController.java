package com.boot.liqian.controller;

import com.boot.liqian.model.Medince;
import com.boot.liqian.model.Redemption;
import com.boot.liqian.service.BuyerService;
import com.boot.liqian.service.PrizeManageService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class PrizeManageController {
	@Autowired
    PrizeManageService prizeManageService;

    /**
     * 获取所有的奖品
     * @param pn
     * @param response
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllPrize",method = RequestMethod.GET)
    public Map<String, Object> getAllPrize(@RequestParam("pn") Integer pn,
                                                  HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        List<Redemption> redemptions=prizeManageService.getAllRedemption(pn);
        PageInfo page = new PageInfo(redemptions, 5);// 5:表示每次只显示5页的导航菜单
        map.put("results", page);
        return map;
    }

    /**
     * 通过奖品id获取对应奖品的信息
     * @param id
     * @param response
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getPrizeInfoById",method = RequestMethod.GET)
    public Map<String, Object> getPrizeInfoById(@RequestParam("id") Integer id,
                                           HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        Redemption prize=prizeManageService.getRedemtionById(id);
        map.put("prize", prize);
        return map;
    }

    /**
     * 更新奖品信息
     * @param id
     * @param name
     * @param number
     * @param response
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updatePrizeInfo",method = RequestMethod.GET)
    public Map<String, Object> updatePrizeInfo(@RequestParam("id") Integer id,@RequestParam("name") String name,@RequestParam("number") Integer number,
                                                HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        Redemption prize=new Redemption();
        prize.setId(id);
        prize.setAward(name);
        prize.setNumber(number);
        Integer re=prizeManageService.updateRedemption(prize);
        map.put("re",re);
        return map;
    }

    /**
     * 通过奖品Id删除此奖品
     * @param id
     * @param response
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deletePrize",method = RequestMethod.GET)
    public Map<String, Object> deletePrize(@RequestParam("id") Integer id,
                                               HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();

        Integer re=prizeManageService.deletePrizeById(id);
        map.put("re",re);
        return map;
    }

    /**
     * 添加奖品
     * @param name
     * @param number
     * @param response
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addPrizeInfo",method = RequestMethod.GET)
    public Map<String, Object> addPrize(@RequestParam("name") String name,@RequestParam("number") Integer number,
                                           HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        Redemption prize=new Redemption();
        prize.setNumber(number);
        prize.setAward(name);
        Integer re=prizeManageService.addPrize(prize);
        map.put("re",re);
        return map;
    }

    /**
     * 批量删除奖品
     * @param ids
     * @param response
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deletePrizes",method = RequestMethod.GET)
    public Map<String, Object> deletePrizes(@RequestParam("ids") String ids,                                    HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        String ids_str[]=ids.split("-");

        List<Integer> id_list=new LinkedList<Integer>();
        for(String r:ids_str){
            id_list.add(Integer.valueOf(r));
        }

        Integer re=prizeManageService.deletePrizes(id_list);
        map.put("re",re);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/searchPrizeByName",method = RequestMethod.GET)
    public Map<String, Object> searchPrizeByName(@RequestParam("content") String content,                                    HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");// 允许服务器向浏览器跨域响应时更改浏览器（客户端）的cookie
        Map<String, Object> map = new HashMap<String, Object>();
        List<Redemption> redemptions=prizeManageService.searchPrizesByName(content);
        PageInfo page = new PageInfo(redemptions, 5);// 5:表示每次只显示5页的导航菜单
        map.put("results", page);
        return map;
    }
}
