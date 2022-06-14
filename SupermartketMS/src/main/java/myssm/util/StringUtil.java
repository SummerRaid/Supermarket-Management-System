package myssm.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SummerRaid
 *
 * @className: StringUtil
 * @Description: TODO
 * @version: v1.17.0
 * @author: ZIRUI QIAO
 * @date: 2022/5/22 14:04
 */
public class StringUtil {
    /**
     * @Description: 判断字符串是否为null或""
     * @param str
     * @return: boolean
     * @Author: Zirui Qiao
     * @Date: 2022/5/22 14:04
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String localDateTimeToString(LocalDateTime time) {
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String strDate2 = dtf2.format(time);
        return strDate2;
    }

    public static String dateToString(Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return sdf.format(time);
    }

    public static Date stringToDate(String str) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
             date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static LocalDateTime stringToLocalDateTime(String str) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(str,dtf);

        return ldt;
    }

    public static Date localDateTimeToDate(LocalDateTime time) {
        return stringToDate(localDateTimeToString(time));
    }

    public static LocalDateTime dateToLocalDateTime(Date time) {
        return stringToLocalDateTime(dateToString(time));
    }

    public static String readJson(HttpServletRequest req) throws IOException {
        StringBuilder stringBuffer = new StringBuilder("");
        BufferedReader reader = req.getReader();
        String str = null;
        while((str=reader.readLine()) != null) {
            stringBuffer.append(str);
        }
        str = stringBuffer.toString();
        return str;
    }
}
