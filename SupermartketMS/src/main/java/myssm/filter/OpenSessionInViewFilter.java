package myssm.filter;

import ch.qos.logback.classic.Logger;
import myssm.trans.TransactionManager;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SummerRaid
 *
 * @className: OpenSessionInViewFilter
 * @Description: 管理事务的Filter
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/5/28 15:00
 */
@WebFilter("*.do")
public class OpenSessionInViewFilter implements Filter {

    public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(OpenSessionInViewFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try{
            TransactionManager.beginTrans();
            LOGGER.debug("开启事务");
            //System.out.println("开启事务");
            filterChain.doFilter(servletRequest, servletResponse);
            TransactionManager.commit();
            LOGGER.debug("提交事务");
            //System.out.println("提交事务");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                TransactionManager.rollback();
                //System.out.println("回滚事务");
                LOGGER.debug("回滚事务");
            } catch (SQLException ex) {
                ex.printStackTrace();
                LOGGER.error("回滚事务失败");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
