package com.company.dao;

import com.company.entity.ImsSalary;
import java.util.List;
/**
 *
 * @author hyc
 * Date: 2024/8/15
 * @version 1.0
 */

public interface SalaryDao {
    boolean addSalary(ImsSalary salary);
    boolean updateSalary(ImsSalary salary);
    boolean deleteSalary(String salaryId);
    ImsSalary findSalaryById(String salaryId);
    List<ImsSalary> findAllSalaries();
    /**
     * 根据员工ID和工资日期查询薪资信息
     * @param employeeId 员工ID
     * @param salaryDate 工资日期 (格式: yyyy-MM)
     * @return 薪资信息
     */
    ImsSalary getSalaryByEmployeeAndDate(String employeeId, String salaryDate);

    List<ImsSalary> getSalariesByEmployee(String employeeId);
    List<ImsSalary> getSalariesByDate(String salaryDate);

    /**
     * 检查员工是否有当月薪资记录
     *
     * @param employeeId 员工ID
     * @return 有薪资记录返回 true，否则返回 false
     */
    boolean hasSalaryForCurrentMonth(String employeeId);
}

