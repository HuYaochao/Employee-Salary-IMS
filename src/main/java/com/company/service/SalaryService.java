package com.company.service;
import com.company.entity.ImsSalary;
import java.util.List;
/**
 *
 * @author hyc
 * Date: 2024/8/15
 * @version 1.0
 */

public interface SalaryService {
    boolean addSalary(ImsSalary salary);
    boolean updateSalary(ImsSalary salary);
    boolean deleteSalary(String salaryId);
    boolean isSalaryAlreadyExists(String employeeId, String salaryDate);
    /**
     * 根据员工ID和工资日期查询薪资信息
     * @param employeeId 员工ID
     * @param salaryDate 工资日期 (格式: yyyy-MM)
     * @return 薪资信息
     */
    ImsSalary getSalaryByEmployeeAndDate(String employeeId, String salaryDate);
    ImsSalary getSalaryById(String salaryId);
    List<ImsSalary> getAllSalaries();
    List<ImsSalary> getSalariesByEmployee(String employeeId);
    List<ImsSalary> getSalariesByDate(String salaryDate);
}

