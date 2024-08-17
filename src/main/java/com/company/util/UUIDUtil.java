package com.company.util;
import java.util.UUID;
/**
 *
 * @author hyc
 * Date: 2024/8/15
 * @version 1.0
 */


public class UUIDUtil {
    // 生成 UUID
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
