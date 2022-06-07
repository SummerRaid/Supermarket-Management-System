package myssm.ioc;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SummerRaid
 *
 * @className: BeanFactory
 * @Description: IOC容器抽象类
 * @version: v1.17.0
 * @author: ZIRUI QIAO
 * @date: 2022/5/27 15:30
 */
public interface BeanFactory {

    /**
     * @Description: 获取IOC容器中的对象
     * @param id 对象的id
     * @return: java.lang.Object
     * @Author: Zirui Qiao
     * @Date: 2022/6/7 16:29
     */
    Object getBean(String id);
}
