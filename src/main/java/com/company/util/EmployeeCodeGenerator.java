package com.company.util;

import net.sourceforge.pinyin4j.PinyinHelper;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author hyc
 * Date: 2024/8/15
 * @version 1.0
 */

public class EmployeeCodeGenerator {

    // 保存当前日期
    private static String lastGeneratedDate = null;
    // 保存当前序列号
    private static int currentSequenceNumber = 0;

    // 静态方法生成员工编码
    public static String generateEmployeeCode(String departmentName) {
        // 获取当前日期
        String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());

        // 如果当前日期与上一次生成的日期不同，重置序号
        if (!currentDate.equals(lastGeneratedDate)) {
            lastGeneratedDate = currentDate;
            // 新的一天，序号从1开始
            currentSequenceNumber = 1;
        } else {
            // 同一天，序号递增
            currentSequenceNumber++;
        }

        // 获取部门名称首字母缩写
        String getPinyinAbbreviation = getPinyinAbbreviation(departmentName);

        // 返回生成的编码，格式：部门首字母 + 日期 + 序列号
        return getPinyinAbbreviation + currentDate + String.format("%03d", currentSequenceNumber);
    }

    // 工具方法：将中文转换为拼音首字母
    public static String getPinyinAbbreviation(String chinese) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chinese.length(); i++) {
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(chinese.charAt(i));
            if (pinyinArray != null && pinyinArray.length > 0) {
                sb.append(pinyinArray[0].charAt(0));
            }
        }
        return sb.toString().toUpperCase();
    }
}

