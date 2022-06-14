package myssm.trans;

import myssm.basedao.MybatisSingleton;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SummerRaid
 *
 * @className: TransactionManager
 * @Description: TODO
 * @version: v1.17.0
 * @author: ZIRUI QIAO
 * @date: 2022/5/28 15:03
 */
public class TransactionManager {

    // 开启事务
    public static void beginTrans() {
        //ConnUtil.getConn().setAutoCommit(false);
        MybatisSingleton.getConn();
    }

    // 提交事务
    public static void commit() throws SQLException {
        /*Connection conn = ConnUtil.getConn();
        conn.commit();
        ConnUtil.closeConn();*/
        SqlSession conn = MybatisSingleton.getConn();
        conn.commit();
        MybatisSingleton.closeConn();
    }

    // 回滚事务
    public static void rollback() throws SQLException {
        /*Connection conn = ConnUtil.getConn();
        conn.rollback();
        ConnUtil.closeConn();*/
        SqlSession conn = MybatisSingleton.getConn();
        conn.rollback();
        MybatisSingleton.closeConn();
    }
}
