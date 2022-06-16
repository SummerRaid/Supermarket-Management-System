package myssm.basedao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SummerRaid
 *
 * @className: util.MybatisSingleton
 * @Description: Mybatis 定制工具类
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/9 13:29
 */
public class MybatisSingleton {

    private static final ThreadLocal<SqlSession> threadLocal = new ThreadLocal<>();
    private final static SqlSessionFactory FACTORY;
    public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MybatisSingleton.class);


    private MybatisSingleton() {
    }

    private final static MybatisSingleton self;

    static {
        self = new MybatisSingleton();
        String resource = "mybatis-config.xml";
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            LOGGER.debug("读取Mybatis配置文件");
        } catch (IOException e) {
            LOGGER.error("建立连接池时出现错误！");
            e.printStackTrace();
            throw new DAOException("建立连接池时出现错误！");
        }
        FACTORY = new SqlSessionFactoryBuilder().build(inputStream);
        LOGGER.debug("生成SqlSessionFactory对象");
    }

    public static SqlSession getConn() {
        SqlSession conn = threadLocal.get();
        if (conn == null) {
            conn = FACTORY.openSession();
            threadLocal.set(conn);
            LOGGER.debug("新建SqlSession对象");
        }
        return threadLocal.get();
    }

    public static void closeConn() throws SQLException {
        SqlSession conn = threadLocal.get();
        if (conn == null) {
            return;
        }
        conn.close();
        threadLocal.set(null);
        LOGGER.debug("删除SqlSession对象");
    }

}