package com.company.dao.impl;

import com.company.dao.SalaryDao;
import com.company.entity.ImsSalary;
import com.company.util.DatabaseUtil;
import com.company.util.DateUtil;

import java.math.BigDecimal;
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

public class SalaryDaoImpl implements SalaryDao {


    @Override
    public boolean addSalary(ImsSalary salary) {
        String sql = "INSERT INTO ims_salary (sa_id, emp_id, sa_date, sa_base, sa_performance, sa_insurance, sa_actual, is_deleted, create_time, update_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, salary.getSaId());
            statement.setString(2, salary.getEmpId());
            statement.setString(3, salary.getSaDate());

            // 设置 BigDecimal 类型的字段
            statement.setBigDecimal(4, salary.getSaBase());
            statement.setBigDecimal(5, salary.getSaPerformance());
            statement.setBigDecimal(6, salary.getSaInsurance());
            statement.setBigDecimal(7, salary.getSaActual());

            statement.setInt(8, salary.getIsDeleted());
            statement.setString(9, salary.getCreateTime());
            statement.setString(10, salary.getUpdateTime());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ImsSalary getSalaryByEmployeeAndDate(String employeeId, String salaryDate) {
        String sql = "SELECT * FROM ims_salary WHERE emp_id = ? AND sa_date = ? AND is_deleted = 0";
        ImsSalary salary = null;

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, employeeId);
            statement.setString(2, salaryDate);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    salary = new ImsSalary();
                    salary.setSaId(resultSet.getString("sa_id"));
                    salary.setEmpId(resultSet.getString("emp_id"));
                    salary.setSaDate(resultSet.getString("sa_date"));

                    // 读取 BigDecimal 类型的字段
                    salary.setSaBase(resultSet.getBigDecimal("sa_base"));
                    salary.setSaPerformance(resultSet.getBigDecimal("sa_performance"));
                    salary.setSaInsurance(resultSet.getBigDecimal("sa_insurance"));
                    salary.setSaActual(resultSet.getBigDecimal("sa_actual"));

                    salary.setIsDeleted(resultSet.getInt("is_deleted"));
                    salary.setCreateTime(resultSet.getString("create_time"));
                    salary.setUpdateTime(resultSet.getString("update_time"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salary;
    }

    @Override
    public boolean updateSalary(ImsSalary salary) {
        // SQL 更新语句
        String sql = "UPDATE ims_salary SET sa_base = ?, sa_performance = ?, sa_insurance = ?, sa_actual = ?, update_time = ? WHERE sa_id = ? AND is_deleted = 0";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // 设置 SQL 参数
            preparedStatement.setBigDecimal(1, salary.getSaBase());
            preparedStatement.setBigDecimal(2, salary.getSaPerformance());
            preparedStatement.setBigDecimal(3, salary.getSaInsurance());
            preparedStatement.setBigDecimal(4, salary.getSaActual());
            preparedStatement.setString(5, salary.getUpdateTime());
            preparedStatement.setString(6, salary.getSaId());

            // 执行更新操作
            int rowsAffected = preparedStatement.executeUpdate();

            // 判断更新操作是否成功
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean deleteSalary(String salaryId) {
        String sql = "UPDATE ims_salary SET is_deleted = 1, update_time = ? WHERE sa_id = ? AND is_deleted = 0";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // 设置 SQL 参数
            preparedStatement.setString(1, DateUtil.getCurrentTime());
            preparedStatement.setString(2, salaryId);

            // 执行更新操作
            int rowsAffected = preparedStatement.executeUpdate();

            // 判断更新操作是否成功
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public ImsSalary findSalaryById(String salaryId) {
        // 实现逻辑
        return null;
    }

    @Override
    public List<ImsSalary> findAllSalaries() {
        // 实现逻辑
        return new ArrayList<>();
    }

    @Override
    public List<ImsSalary> getSalariesByEmployee(String employeeId) {
        String sql = "SELECT * FROM ims_salary WHERE emp_id = ? AND is_deleted = 0";
        List<ImsSalary> salaries = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ImsSalary salary = new ImsSalary();
                salary.setSaId(resultSet.getString("sa_id"));
                salary.setEmpId(resultSet.getString("emp_id"));
                salary.setSaDate(resultSet.getString("sa_date"));
                salary.setSaBase(resultSet.getBigDecimal("sa_base"));
                salary.setSaPerformance(resultSet.getBigDecimal("sa_performance"));
                salary.setSaInsurance(resultSet.getBigDecimal("sa_insurance"));
                salary.setSaActual(resultSet.getBigDecimal("sa_actual"));
                salary.setIsDeleted(resultSet.getInt("is_deleted"));
                salary.setCreateTime(resultSet.getString("create_time"));
                salary.setUpdateTime(resultSet.getString("update_time"));
                salaries.add(salary);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salaries;
    }

    @Override
    public List<ImsSalary> getSalariesByDate(String salaryDate) {
        String sql = "SELECT * FROM ims_salary WHERE sa_date = ? AND is_deleted = 0";
        List<ImsSalary> salaries = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, salaryDate);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ImsSalary salary = new ImsSalary();
                salary.setSaId(resultSet.getString("sa_id"));
                salary.setEmpId(resultSet.getString("emp_id"));
                salary.setSaDate(resultSet.getString("sa_date"));
                salary.setSaBase(resultSet.getBigDecimal("sa_base"));
                salary.setSaPerformance(resultSet.getBigDecimal("sa_performance"));
                salary.setSaInsurance(resultSet.getBigDecimal("sa_insurance"));
                salary.setSaActual(resultSet.getBigDecimal("sa_actual"));
                salary.setIsDeleted(resultSet.getInt("is_deleted"));
                salary.setCreateTime(resultSet.getString("create_time"));
                salary.setUpdateTime(resultSet.getString("update_time"));
                salaries.add(salary);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salaries;
    }

    @Override
    public boolean hasSalaryForCurrentMonth(String employeeId) {
        String sql = "SELECT COUNT(*) FROM ims_salary WHERE emp_id = ? AND MONTH(create_time) = MONTH(CURRENT_DATE) AND YEAR(create_time) = YEAR(CURRENT_DATE)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, employeeId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}

