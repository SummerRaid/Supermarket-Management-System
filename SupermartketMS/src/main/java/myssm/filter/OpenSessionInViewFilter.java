package myssm.filter;

import myssm.trans.TransactionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SummerRaid
 *
 * @className: OpenSessionInViewFilter
 * @Description: 设置事务的filter
 * @version: v1.17.0
 * @author: ZIRUI QIAO
 * @date: 2022/5/28 15:00
 */
@WebFilter("*.do")
public class OpenSessionInViewFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try{
            TransactionManager.beginTrans();
            //System.out.println("开启事务");
            filterChain.doFilter(servletRequest, servletResponse);
            TransactionManager.commit();
            //System.out.println("提交事务");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                TransactionManager.rollback();
                System.out.println("回滚事务");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {

    }
}