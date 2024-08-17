package com.company.dao;

import com.company.entity.ImsEmployee;
import java.util.List;
/**
 *
 * @author hyc
 * Date: 2024/8/15
 * @version 1.0
 */

public interface EmployeeDao {
    boolean addEmployee(ImsEmployee employee);

    /**
     * 更新员工信息
     *
     * @param employee 员工对象
     * @return 成功返回 true，失败返回 false
     */
    boolean updateEmployee(ImsEmployee employee);
    boolean deleteEmployee(String employeeId);
    /**
     * 根据员工ID查询员工信息
     *
     * @param empId 员工ID
     * @return 员工对象
     */
    ImsEmployee getEmployeeById(String empId);


    List<ImsEmployee> getAllEmployees();
    List<ImsEmployee> getEmployeesByDepartmentId(String departmentId);
}

