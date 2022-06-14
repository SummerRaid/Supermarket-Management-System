package myssm.ioc;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SummerRaid
 *
 * @className: BeanFactory
 * @Description: IOC容器接口
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/5/27 15:30
 */
public interface BeanFactory {
    Object getBean(String id);
}
