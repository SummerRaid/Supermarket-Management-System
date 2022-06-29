package myssm.myspringmvc;

import myssm.basedao.MybatisSingleton;
import myssm.ioc.BeanFactory;
import myssm.util.StringUtil;
import org.slf4j.LoggerFactory;

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
    public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DispatcherServlet.class);


    public void init() throws ServletException {
        super.init();
        //之前是在此处主动创建IOC容器的
        //现在优化为从application作用域去获取
        //beanFactory = new ClassPathXmlApplicationContext();
        ServletContext application = getServletContext();
        Object beanFactoryObj = application.getAttribute("beanFactory");
        if(beanFactoryObj!=null){
            beanFactory = (BeanFactory)beanFactoryObj ;
            LOGGER.error("成功获取IOC容器");
        }else{
            LOGGER.error("IOC容器获取失败！");
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
        LOGGER.debug("成功获取请求：" + servletPath);

        // 2. hello -> HelloController
        Object controllerBeanObj = beanFactory.getBean(servletPath);

        String operate = req.getParameter("operate");
        if(StringUtil.isEmpty(operate)){
            operate = "index";
        }
        LOGGER.debug("调用：" + controllerBeanObj + " 中的：" + operate + " 方法！");

        try {
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if(operate.equals(method.getName())) {
                    // 1. 统一获取请求参数

                    // 1.1 返回当前方法的参数数组
                    Parameter[] parameters = method.getParameters();
                    // 1.2 用来承载参数的值 --》 parameterValues
                    Object[] parameterValues = new Object[parameters.length];
                    LOGGER.debug("向方法:" + method.getName() + "传递" + parameters.length + "个参数");
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
                            } else if (Double.class.getName().equals(typeName) &&
                                    StringUtil.isNotEmpty(parameterValue)) {
                                parameterValues[i] = Double.parseDouble(parameterValue);
                            } else {
                                parameterValues[i] = parameterValue;
                            }
                        }
                    }
                    LOGGER.debug("参数传递完成！");

                    // 2. controller组件中的方法调用
                    method.setAccessible(true);
                    LOGGER.debug("试图执行方法");

                    Object returnObj = method.invoke(controllerBeanObj, parameterValues);
                    LOGGER.debug("方法执行完成");

                    // 3. 视图处理
                    String methodReturnStr = (String) returnObj;
                    LOGGER.debug("进行视图处理：" + methodReturnStr);
                    if (StringUtil.isEmpty(methodReturnStr)) {
                        LOGGER.debug("值为空，直接退出DispatcherServlet");
                        return;
                    } else {
                        if(methodReturnStr.startsWith("redirect:")){
                            String redirectStr = methodReturnStr.substring("redirect:".length());
                            LOGGER.debug("redirect，重定向到：" + redirectStr);
                            resp.sendRedirect(redirectStr);
                        } else if(methodReturnStr.startsWith("json:")) {
                            //MIME-TYPE
                            resp.setContentType("application/json;charset=utf-8");

                            String jsonStr = methodReturnStr.substring("json:".length());
                            LOGGER.debug("json，写入json到：" + jsonStr);
                            PrintWriter out = resp.getWriter();
                            out.write(jsonStr);
                            out.flush();
                        } else {
                            LOGGER.debug("无，内部转发到：" + methodReturnStr);
                            super.processTemplate(methodReturnStr, req, resp);
                        }
                    }
                }
            }

            /*} else {
                throw new RuntimeException("operate值非法！");
            }*/
        } catch ( InvocationTargetException | IllegalAccessException e) {
            LOGGER.error("DispatcherServlet 出错了！");
            e.printStackTrace();
            throw new DispatcherServletException("DispatcherServlet 出错了！");
        }
    }

}
