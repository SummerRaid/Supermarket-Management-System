package myssm.util;

import myssm.basedao.MybatisSingleton;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: MapperUtil
 * @Description: 动态代理获取mapper工具类
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/16 14:28
 */
public class MapperUtil {

    private static <T> T getMapper(Class<T> clazz) {
        return MybatisSingleton.getConn().getMapper(clazz);
    }

    public static <T> T getProxy(Class<T> clazz) {
        ClassLoader classLoader = clazz.getClassLoader();
        Class<?>[] interfaces = new Class[]{clazz};
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                T mapper = getMapper(clazz);
                Object result = method.invoke(mapper, args);
                return result;
            }
        };

        Object proxyMapper = Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        return (T) proxyMapper;
    }

}