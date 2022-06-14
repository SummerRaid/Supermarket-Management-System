package myssm.myspringmvc;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SummerRaid
 *
 * @className: DispatcherServletException
 * @Description: DispatcherServlet专属异常
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/5/30 14:12
 */
public class DispatcherServletException extends RuntimeException{
    public DispatcherServletException(String message) {
        super(message);
    }
}
