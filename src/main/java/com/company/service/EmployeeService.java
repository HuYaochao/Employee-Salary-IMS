package com.company.service;
import com.company.entity.ImsEmployee;
import java.util.List;
/**
 *
 * @author hyc
 * Date: 2024/8/15
 * @version 1.0
 */

public interface EmployeeService {
    boolean addEmployee(String empName, String empSex, String departmentName);

    /**
     * 更新员工信息
     *
     * @param employee 员工对象
     * @return 成功返回 true，失败返回 false
     */
    boolean updateEmployee(ImsEmployee employee);
    boolean deleteEmployee(String employeeId);
    ImsEmployee getEmployeeById(String employeeId);
    List<ImsEmployee> getAllEmployees();


}
