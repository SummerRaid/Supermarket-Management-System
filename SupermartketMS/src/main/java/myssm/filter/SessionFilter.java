package myssm.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SummerRaid
 *
 * @className: SessionFilter
 * @Description: 判断用户是否为合法访问的filter
 * @version: v1.17.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/4 21:00
 */
/*
@WebFilter(urlPatterns = {"*.do", "*.html"},
        initParams = {
            @WebInitParam(name="bai",
                    value="/page.do?operate=page&page=user/login,/user.do?null")
        })
*/
public class SessionFilter implements Filter {

    List<String> baiList = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String bai = filterConfig.getInitParameter("bai");
        String[] baiArr = bai.split(",");
        baiList = Arrays.asList(baiArr);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        String requestURI = request.getRequestURI();
        String queryString = request.getQueryString();
        String str = requestURI + "?" + queryString;
        if(baiList.contains(str)) {
            filterChain.doFilter(request, response);
        } else {
            HttpSession session = request.getSession();
            Object currUserObj = session.getAttribute("currUser");

            if(currUserObj == null) {
                response.sendRedirect("page.do?operate=page&page=user/login");
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
