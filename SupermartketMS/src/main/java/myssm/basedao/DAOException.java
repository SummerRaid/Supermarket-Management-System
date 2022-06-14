package myssm.basedao;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SummerRaid
 *
 * @className: DAOException
 * @Description: BaseDAO专属异常
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/5/29 18:43
 */
public class DAOException extends RuntimeException{
    public DAOException(String msg){
        super(msg);
    }
}