package com.company.config;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author hyc
 * Date: 2024/8/14
 * @version 1.0
 */

public class DatabaseConfig {

    private static DruidDataSource dataSource;

    static {
        Properties properties = new Properties();
        try {
            properties.load(DatabaseConfig.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load database configuration", e);
        }

        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(properties.getProperty("db.driver"));
        druidDataSource.setUrl(properties.getProperty("db.url"));
        druidDataSource.setUsername(properties.getProperty("db.username"));
        druidDataSource.setPassword(properties.getProperty("db.password"));
        druidDataSource.setInitialSize(Integer.parseInt(properties.getProperty("db.initialSize")));
        druidDataSource.setMaxActive(Integer.parseInt(properties.getProperty("db.maxActive")));
        druidDataSource.setMaxWait(Long.parseLong(properties.getProperty("db.maxWait")));

        dataSource = druidDataSource;
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    // 新增关闭数据源方法
    public static void closeDataSource() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
