package myssm.myspringmvc;

import myssm.ioc.BeanFactory;
import myssm.util.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SummerRaid
 *
 * @className: DispatcherServlet
 * @Description: 总Servlet
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/5/24 13:07
 */
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    private BeanFactory beanFactory;

    public void init() throws ServletException {
        super.init();
        //之前是在此处主动创建IOC容器的
        //现在优化为从application作用域去获取
        //beanFactory = new ClassPathXmlApplicationContext();
        ServletContext application = getServletContext();
        Object beanFactoryObj = application.getAttribute("beanFactory");
        if(beanFactoryObj!=null){
            beanFactory = (BeanFactory)beanFactoryObj ;
        }else{
            throw new RuntimeException("IOC容器获取失败！");
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.setCharacterEncoding("UTF-8");

        // 1. /hello.do -> hello
        String servletPath = req.getServletPath();
        servletPath = servletPath.substring(1);
        int lastDotIndex = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(0, lastDotIndex);

        // 2. hello -> HelloController
        Object controllerBeanObj = beanFactory.getBean(servletPath);

        String operate = req.getParameter("operate");
        if(StringUtil.isEmpty(operate)){
            operate = "index";
        }

        try {
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if(operate.equals(method.getName())) {
                    // 1. 统一获取请求参数

                    // 1.1 返回当前方法的参数数组
                    Parameter[] parameters = method.getParameters();
                    // 1.2 用来承载参数的值 --》 parameterValues
                    Object[] parameterValues = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];
                        Class parameterType = parameter.getType();
                        String parameterName = parameter.getName();
                        // 如果参数名是req, resp和session，那就不是从req中获取参数了
                        if(HttpServletRequest.class == parameterType) {
                            parameterValues[i] = req;
                        } else if(HttpServletResponse.class == parameterType){
                            parameterValues[i] = resp;
                        } else if(HttpSession.class == parameterType) {
                            parameterValues[i] = req.getSession();
                        } else {
                            // 从请求中获取参数值
                            String parameterValue = req.getParameter(parameterName);
                            String typeName = parameter.getType().getName();
                            if(Integer.class.getName().equals(typeName) &&
                                    StringUtil.isNotEmpty(parameterValue)) {
                                parameterValues[i] = Integer.parseInt(parameterValue);
                            } else {
                                parameterValues[i] = parameterValue;
                            }
                        }
                    }

                    // 2. controller组件中的方法调用
                    method.setAccessible(true);
                    Object returnObj = method.invoke(controllerBeanObj, parameterValues);

                    // 3. 视图处理
                    String methodReturnStr = (String) returnObj;
                    if (StringUtil.isEmpty(methodReturnStr)) {
                        return;
                    } else {
                        if(methodReturnStr.startsWith("redirect:")){
                            String redirectStr = methodReturnStr.substring("redirect:".length());
                            resp.sendRedirect(redirectStr);
                        } else if(methodReturnStr.startsWith("json:")) {
                            //MIME-TYPE
                            resp.setContentType("application/json;charset=utf-8");

                            String jsonStr = methodReturnStr.substring("json:".length());
                            PrintWriter out = resp.getWriter();
                            out.write(jsonStr);
                            out.flush();
                        } else {
                            super.processTemplate(methodReturnStr, req, resp);
                        }
                    }
                }
            }

            /*} else {
                throw new RuntimeException("operate值非法！");
            }*/
        } catch ( InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            throw new DispatcherServletException("DispatcherServlet 出错了！");
        }
    }

}
