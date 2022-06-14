package myssm.basedao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

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

    private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<>();
    private final static SqlSessionFactory FACTORY;

    private MybatisSingleton() {
    }

    private final static MybatisSingleton self;

    static {
        self = new MybatisSingleton();
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
            throw new DAOException("建立连接池时出现错误！");
        }
        FACTORY = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static SqlSession getConn() {
        SqlSession conn = threadLocal.get();
        if (conn == null) {
            conn = FACTORY.openSession();
            threadLocal.set(conn);
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
    }

}