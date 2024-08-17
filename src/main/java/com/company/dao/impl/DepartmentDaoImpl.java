package com.company.dao.impl;

import com.company.dao.DepartmentDao;
import com.company.entity.ImsDepartment;
import com.company.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import static com.company.util.DatabaseUtil.getConnection;

/**
 *
 * @author hyc
 * Date: 2024/8/15
 * @version 1.0
 */


public class DepartmentDaoImpl implements DepartmentDao {

    @Override
    public boolean insertDepartment(ImsDepartment department) {
        String sql = "INSERT INTO ims_department (dpt_id, dpt_name, is_deleted, create_time, update_time) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, department.getDptId());
            stmt.setString(2, department.getDptName());
            stmt.setInt(3, department.getIsDeleted());
            stmt.setString(4, String.valueOf(department.getCreateTime()));
            stmt.setString(5, String.valueOf(department.getUpdateTime()));

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isDepartmentNameExists(String departmentName) {
        String sql = "SELECT COUNT(*) FROM ims_department WHERE dpt_name = ? AND is_deleted = 0";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, departmentName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateDepartment(ImsDepartment department) {
        String sql = "UPDATE ims_department SET dpt_name = ? WHERE dpt_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, department.getDptName());
            stmt.setString(2, department.getDptId());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void deleteDepartment(String  departmentId) {
        // 实现逻辑
    }

    @Override
    public boolean updateDepartmentIsDeleted(String departmentId, int isDeleted) {
        String sql = "UPDATE ims_department SET is_deleted = ? WHERE dpt_id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, isDeleted);
            ps.setString(2, departmentId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ImsDepartment getDepartmentById(String departmentId) {
        String sql = "SELECT * FROM ims_department WHERE dpt_id = ?";
        // 使用 JDBC 执行查询
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, departmentId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ImsDepartment department = new ImsDepartment();
                    department.setDptId(rs.getString("dpt_id"));
                    department.setDptName(rs.getString("dpt_name"));
                    return department;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ImsDepartment> findAllDepartments() {
        // 实现逻辑
        List<ImsDepartment> departments = new ArrayList<>();
        String sql = "SELECT * FROM ims_department WHERE is_deleted = 0";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                departments.add(new ImsDepartment(
                        rs.getString("dpt_id"),
                        rs.getString("dpt_name"),
                        rs.getInt("is_deleted"),
                        rs.getString("create_time"),
                        rs.getString("update_time")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    @Override
    public ImsDepartment getDepartmentByName(String departmentName) {
        // 获取所有未删除的部门
        List<ImsDepartment> departments = findAllDepartments();
        for (ImsDepartment department : departments) {
            // 匹配部门名称
            if (department.getDptName().equals(departmentName)) {
                return department;
            }
        }
        // 如果找不到则返回null
        return null;
    }
}

