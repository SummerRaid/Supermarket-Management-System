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
 * @Description: 自定义工具类
 * @version: v1.17.0
 * @author: ZIRUI QIAO
 * @date: 2022/5/22 14:04
 */
public class StringUtil {
    /**
     * @Description: 判断字符串是否为null或""
     * @param str 字符串
     * @return: boolean
     * @Author: Zirui Qiao
     * @Date: 2022/5/22 14:04
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    /**
     * @Description: 判断字符串是否非空
     * @param str 字符串
     * @return: boolean
     * @Author: Zirui Qiao
     * @Date: 2022/6/7 16:35
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * @Description: 将 LocalDateTime类型的时间 转换为 String类型字符串
     * @param time LocalDateTime时间
     * @return: java.lang.String
     * @Author: Zirui Qiao
     * @Date: 2022/6/7 16:36
     */
    public static String localDateTimeToString(LocalDateTime time) {
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return dtf2.format(time);
    }

    /**
     * @Description: 将 Date类型的时间 转换为 String类型字符串
     * @param time Date类型的时间
     * @return: java.lang.String
     * @Author: Zirui Qiao
     * @Date: 2022/6/7 16:37
     */
    public static String dateToString(Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return sdf.format(time);
    }

    /**
     * @Description: 将 String类型字符串 转换为 Date类型时间
     * @param str String类型 字符串
     * @return: java.util.Date
     * @Author: Zirui Qiao
     * @Date: 2022/6/7 16:38
     */
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

    /**
     * @Description: 将 String类型字符串 转换为 LocalDateTime类型时间
     * @param str String类型 字符串
     * @return: java.time.LocalDateTime
     * @Author: Zirui Qiao
     * @Date: 2022/6/7 16:39
     */
    public static LocalDateTime stringToLocalDateTime(String str) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return LocalDateTime.parse(str,dtf);
    }

    /**
     * @Description: 将 LocalDateTime类型时间 转换为 Date类型时间
     * @param time LocalDateTime类型时间
     * @return: java.util.Date
     * @Author: Zirui Qiao
     * @Date: 2022/6/7 16:40
     */
    public static Date localDateTimeToDate(LocalDateTime time) {
        return stringToDate(localDateTimeToString(time));
    }

    /**
     * @Description: 将 Date类型时间 转换为 LocalDateTime类型时间
     * @param time Date类型时间
     * @return: java.time.LocalDateTime
     * @Author: Zirui Qiao
     * @Date: 2022/6/7 16:40
     */
    public static LocalDateTime dateToLocalDateTime(Date time) {
        return stringToLocalDateTime(dateToString(time));
    }

    /**
     * @Description: 从 HttpServletRequest 中读取 json格式信息，返回字符串
     * @param req HttpServletRequest请求
     * @return: java.lang.String
     * @Author: Zirui Qiao
     * @Date: 2022/6/7 16:41
     */
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
