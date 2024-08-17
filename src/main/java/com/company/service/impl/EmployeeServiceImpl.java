package com.company.service.impl;

import com.company.dao.DepartmentDao;
import com.company.dao.SalaryDao;
import com.company.dao.impl.DepartmentDaoImpl;
import com.company.dao.impl.EmployeeDaoImpl;
import com.company.entity.ImsDepartment;
import com.company.entity.ImsEmployee;
import com.company.entity.ImsSalary;
import com.company.service.EmployeeService;
import com.company.dao.EmployeeDao;
import com.company.util.DateUtil;
import com.company.util.EmployeeCodeGenerator;
import com.company.util.UUIDUtil;

import java.util.List;
/**
 *
 * @author hyc
 * Date: 2024/8/15
 * @version 1.0
 */

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;
    private DepartmentDao departmentDao;
    private SalaryDao salaryDao;
    public EmployeeServiceImpl(EmployeeDao employeeDao, DepartmentDao departmentDao,SalaryDao salaryDao) {
        this.employeeDao = employeeDao;
        this.departmentDao = departmentDao;
        this.salaryDao = salaryDao;
    }


    @Override
    public boolean addEmployee(String empName, String empSex, String departmentName) {
        // 获取部门信息
        ImsDepartment department = departmentDao.getDepartmentByName(departmentName);
        if (department == null) {
            System.out.println("部门不存在，无法添加员工。");
            return false;
        }

        // 生成员工ID、编码、和创建时间
        String empId = UUIDUtil.generateUUID();
        String empCode = EmployeeCodeGenerator.generateEmployeeCode(department.getDptName());
        String currentTime = DateUtil.getCurrentTime();

        // 创建员工对象
        ImsEmployee employee = new ImsEmployee();
        employee.setEmpId(empId);
        employee.setDptId(department.getDptId());
        employee.setEmpName(empName);
        employee.setEmpCode(empCode);
        employee.setEmpSex(empSex);
        employee.setCreateTime(currentTime);
        employee.setUpdateTime(currentTime);
        employee.setIsDeleted(0);

        // 保存员工到数据库
        return employeeDao.addEmployee(employee);
    }

    @Override
    public boolean updateEmployee(ImsEmployee employee) {
        // 更新员工信息时，确保不修改员工的 ID、部门 ID 和编码
        ImsEmployee existingEmployee = employeeDao.getEmployeeById(employee.getEmpId());
        if (existingEmployee == null) {
            System.out.println("未找到员工，无法更新！");
            return false;
        }

        // 仅更新允许修改的字段
        existingEmployee.setEmpName(employee.getEmpName());
        existingEmployee.setEmpSex(employee.getEmpSex());
        existingEmployee.setUpdateTime(DateUtil.getCurrentTime());

        // 更新到数据库
        return employeeDao.updateEmployee(existingEmployee);
    }
    @Override
    public boolean deleteEmployee(String employeeId) {
        // 检查员工是否存在
        ImsEmployee employee = employeeDao.getEmployeeById(employeeId);
        if (employee == null) {
            System.out.println("员工不存在，无法删除！");
            return false;
        }

        // 检查员工是否有当月薪资记录

        boolean hasSalaryForCurrentMonth = salaryDao.hasSalaryForCurrentMonth(employeeId);
        if (hasSalaryForCurrentMonth) {
            System.out.println("员工有当月薪资记录，无法删除！");
            return false;
        }

        // 将员工的 is_deleted 标记设置为 1
        employee.setIsDeleted(1);

        // 更新员工记录
        return employeeDao.deleteEmployee(employeeId);
    }

    @Override
    public ImsEmployee getEmployeeById(String employeeId) {
        return employeeDao.getEmployeeById(employeeId);
    }

    @Override
    public List<ImsEmployee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }
}

