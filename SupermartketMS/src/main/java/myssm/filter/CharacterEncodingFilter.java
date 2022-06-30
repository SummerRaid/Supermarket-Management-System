package myssm.filter;

import myssm.util.StringUtil;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SummerRaid
 *
 * @className: CharacterEncodingFilter
 * @Description: 设置编码的Filter
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/5/27 22:31
 */

@WebFilter(value = "*.do", initParams = {@WebInitParam(name="encoding", value="UTF-8")})
public class CharacterEncodingFilter implements Filter {

    public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CharacterEncodingFilter.class);

    private String encoding = "UTF-8";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingStr = filterConfig.getInitParameter("encoding");
        if(StringUtil.isNotEmpty(encodingStr)){
            encoding = encodingStr;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ((HttpServletRequest) servletRequest).setCharacterEncoding(encoding);
        ((HttpServletResponse) servletResponse).setCharacterEncoding(encoding);

        LOGGER.info("设置编码");
        Map<String, String[]> parameterMap = ((HttpServletRequest) servletRequest).getParameterMap();
        LOGGER.info("ServletRequest: " + ((HttpServletRequest) servletRequest).getRequestURI());
        parameterMap.forEach((k, v) -> {
            System.out.println(k + " = " + Arrays.toString(v));
        });

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
