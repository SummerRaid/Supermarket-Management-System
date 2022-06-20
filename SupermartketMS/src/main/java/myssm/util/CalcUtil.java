package myssm.util;

import java.math.BigDecimal;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: CalcUtil
 * @Description: BigDecimal计算工具类
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/19 17:31
 */
public class CalcUtil {
    public static Double addDoubles(Double num1, Double num2) {
        double totalMoney = 0.0;
        BigDecimal bigDecimalNum1 = new BigDecimal(num1 + "");
        BigDecimal bigDecimalNum2 = new BigDecimal(num2 + "");
        BigDecimal result = bigDecimalNum1.add(bigDecimalNum2);
        totalMoney += result.doubleValue();

        return totalMoney;
    }

    public static Double multiplyDoubles(Double num1, Integer num2) {
        double totalMoney = 0.0;
        BigDecimal bigDecimalNum1 = new BigDecimal(num1 + "");
        BigDecimal bigDecimalNum2 = new BigDecimal(num2 + "");
        BigDecimal result = bigDecimalNum1.multiply(bigDecimalNum2);
        totalMoney += result.doubleValue();

        return totalMoney;
    }
}
