package myssm.listeners;

import myssm.ioc.BeanFactory;
import myssm.ioc.ClassPathXmlApplicationContext;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SummerRaid
 *
 * @className: ContextLoaderListener
 * @Description: 用来初始化IOC容器的Listener
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/5/29 18:56
 */
@WebListener
public class ContextLoaderListener implements ServletContextListener {

    public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ContextLoaderListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //1.获取ServletContext对象
        ServletContext application = servletContextEvent.getServletContext();
        //2.获取上下文的初始化参数
        String path = application.getInitParameter("contextConfigLocation");
        LOGGER.debug("开始创建IOC容器");
        //3.创建IOC容器
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(path);
        LOGGER.debug("IOC容器创建完成");
        //4.将IOC容器保存到application作用域
        application.setAttribute("beanFactory",beanFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
