package myssm.basedao;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SummerRaid
 *
 * @className: ConnUtil
 * @Description: BaseDAO 工具类
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/5/28 15:07
 */
public class ConnUtil {

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    private static DataSource dataSource;

    static {
        try {
            // 0. 读取properties文件
            Properties properties = new Properties();
            java.net.URL url1 = BaseDAO.class.getResource("/jdbc.properties");
            InputStream is = new FileInputStream(url1.getPath());
            properties.load(is);

            // 1. 建立连接池对象
            dataSource = DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("建立连接池时出现错误！");
        }
    }

    public static Connection getConn() {
        Connection conn = threadLocal.get();
        if(conn == null) {
            conn = createConn();
            threadLocal.set(conn);
        }
        return threadLocal.get();
    }

    private static Connection createConn() {
        try {
            Connection conn = dataSource.getConnection();
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeConn() throws SQLException {
        Connection conn = threadLocal.get();
        if(conn == null) {
            return;
        }
        if(!conn.isClosed()){
            conn.close();
            threadLocal.set(null);
        }
    }
}
