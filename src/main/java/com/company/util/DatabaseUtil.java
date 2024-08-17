package com.company.util;

import com.company.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author hyc
 * Date: 2024/8/14
 * @version 1.0
 */

public class DatabaseUtil {

    // 获取数据库连接
    public static Connection getConnection() throws SQLException {
        return DatabaseConfig.getDataSource().getConnection();
    }

    // 关闭数据库连接
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 关闭PreparedStatement
    public static void closeStatement(PreparedStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 关闭ResultSet
    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 关闭所有资源
    public static void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
        closeResultSet(rs);
        closeStatement(stmt);
        closeConnection(conn);
    }

    // 执行查询
    public static ResultSet executeQuery(Connection conn, String sql, Object... params) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(sql);
        setParameters(stmt, params);
        return stmt.executeQuery();
    }

    // 执行更新（INSERT, UPDATE, DELETE）
    public static int executeUpdate(Connection conn, String sql, Object... params) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(sql);
        setParameters(stmt, params);
        return stmt.executeUpdate();
    }

    // 设置PreparedStatement的参数
    private static void setParameters(PreparedStatement stmt, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
    }
}
