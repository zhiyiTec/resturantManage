package com.boot.liqian.listerner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebListener
public class MyListener implements ServletContextListener {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    HttpServletRequest request;
    HttpSession session;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("监听到会话开启");
         request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
         session=request.getSession();

         System.out.println("会话Id为："+session.getId());


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("监听到会话销毁，销毁的会话id为："+session.getId());
    }
}
