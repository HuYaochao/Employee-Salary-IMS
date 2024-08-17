package com.company.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author hyc
 * Date: 2024/8/15
 * @version 1.0
 */

public class DateUtil {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat SALARY_DATE_FORMAT = new SimpleDateFormat("yyyy-MM");
    // 获取当前时间的字符串表示
    public static String getCurrentTime() {
        return DATE_FORMAT.format(new Date());
    }
    // 获取工资日期的字符串表示
    public static String getSalaryDate() {
        return SALARY_DATE_FORMAT.format(new Date());
    }
    // 获取指定日期的工资日期字符串表示
    public static String getSalaryDate(Date date) {
        return SALARY_DATE_FORMAT.format(date);
    }

}
