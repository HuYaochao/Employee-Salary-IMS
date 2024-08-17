package com.company.dao.impl;
import com.company.dao.EmployeeDao;
import com.company.entity.ImsEmployee;
import com.company.util.DatabaseUtil;
import com.company.util.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author hyc
 * Date: 2024/8/15
 * @version 1.0
 */

public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public boolean addEmployee(ImsEmployee employee) {
        // SQL 语句，插入员工信息
        String sql = "INSERT INTO ims_employee (emp_id, dpt_id, emp_name, emp_code, emp_sex, is_deleted, create_time, update_time) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // 设置 SQL 参数
            stmt.setString(1, employee.getEmpId());
            stmt.setString(2, employee.getDptId());
            stmt.setString(3, employee.getEmpName());
            stmt.setString(4, employee.getEmpCode());
            stmt.setString(5, employee.getEmpSex());
            stmt.setInt(6, employee.getIsDeleted());
            stmt.setString(7, employee.getCreateTime());
            stmt.setString(8, employee.getUpdateTime());

            // 执行插入操作
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateEmployee(ImsEmployee employee) {
        String sql = "UPDATE ims_employee SET emp_name = ?, emp_sex = ?, update_time = ? WHERE emp_id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, employee.getEmpName());
            ps.setString(2, employee.getEmpSex());
            ps.setString(3, employee.getUpdateTime());
            ps.setString(4, employee.getEmpId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean deleteEmployee(String employeeId) {
        // 更新员工的 is_deleted 字段为 1
        String sql = "UPDATE ims_employee SET is_deleted = 1, update_time = ? WHERE emp_id = ? AND is_deleted = 0";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, DateUtil.getCurrentTime());
            stmt.setString(2, employeeId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ImsEmployee getEmployeeById(String empId) {
        String sql = "SELECT * FROM ims_employee WHERE emp_id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, empId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ImsEmployee employee = new ImsEmployee();
                employee.setEmpId(rs.getString("emp_id"));
                employee.setEmpName(rs.getString("emp_name"));
                employee.setEmpSex(rs.getString("emp_sex"));
                employee.setDptId(rs.getString("dpt_id"));
                employee.setEmpCode(rs.getString("emp_code"));
                employee.setIsDeleted(rs.getInt("is_deleted"));
                employee.setCreateTime(rs.getString("create_time"));
                employee.setUpdateTime(rs.getString("update_time"));
                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ImsEmployee> getAllEmployees() {
        List<ImsEmployee> employees = new ArrayList<>();
        String sql = "SELECT * FROM ims_employee WHERE is_deleted = 0";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                ImsEmployee employee = new ImsEmployee();
                employee.setEmpId(resultSet.getString("emp_id"));
                employee.setDptId(resultSet.getString("dpt_id"));
                employee.setEmpName(resultSet.getString("emp_name"));
                employee.setEmpCode(resultSet.getString("emp_code"));
                employee.setEmpSex(resultSet.getString("emp_sex"));
                employee.setCreateTime(resultSet.getString("create_time"));
                employee.setUpdateTime(resultSet.getString("update_time"));
                employee.setIsDeleted(resultSet.getInt("is_deleted"));
                employees.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public List<ImsEmployee> getEmployeesByDepartmentId(String departmentId) {
        List<ImsEmployee> employees = new ArrayList<>();
        String sql = "SELECT * FROM ims_employee WHERE dpt_id = ? AND is_deleted = 0";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, departmentId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ImsEmployee employee = new ImsEmployee();
                    employee.setEmpId(rs.getString("emp_id"));
                    employee.setDptId(rs.getString("dpt_id"));
                    employee.setEmpName(rs.getString("emp_name"));
                    employee.setEmpCode(rs.getString("emp_code"));
                    employee.setEmpSex(rs.getString("emp_sex"));
                    employee.setCreateTime(rs.getString("create_time"));
                    employee.setUpdateTime(rs.getString("update_time"));
                    employee.setIsDeleted(rs.getInt("is_deleted"));
                    employees.add(employee);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }


}

