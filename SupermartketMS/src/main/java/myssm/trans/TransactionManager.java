package myssm.trans;

import myssm.basedao.ConnUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SummerRaid
 *
 * @className: TransactionManager
 * @Description: 事务处理的数据库连接
 * @version: v1.17.0
 * @author: ZIRUI QIAO
 * @date: 2022/5/28 15:03
 */
public class TransactionManager {

    // 开启事务
    public static void beginTrans() throws SQLException {
        ConnUtil.getConn().setAutoCommit(false);
    }

    // 提交事务
    public static void commit() throws SQLException {
        Connection conn = ConnUtil.getConn();
        conn.commit();
        ConnUtil.closeConn();
    }

    // 回滚事务
    public static void rollback() throws SQLException {
        Connection conn = ConnUtil.getConn();
        conn.rollback();
        ConnUtil.closeConn();
    }
}
