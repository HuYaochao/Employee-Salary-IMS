package com.company.terminal;

import com.company.dao.impl.DepartmentDaoImpl;
import com.company.dao.impl.EmployeeDaoImpl;
import com.company.dao.impl.SalaryDaoImpl;
import com.company.service.impl.DepartmentServiceImpl;
import com.company.service.impl.EmployeeServiceImpl;
import com.company.service.impl.SalaryServiceImpl;
/**
 *
 * @author hyc
 * Date: 2024/8/16
 * @version 1.0
 */

public class ServiceInitializer {
    private DepartmentServiceImpl departmentService;
    private EmployeeServiceImpl employeeService;
    private SalaryServiceImpl salaryService;

    public ServiceInitializer() {
        DepartmentDaoImpl departmentDao = new DepartmentDaoImpl();
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        SalaryDaoImpl salaryDao = new SalaryDaoImpl();

        this.departmentService = new DepartmentServiceImpl(departmentDao, employeeDao);
        this.employeeService = new EmployeeServiceImpl(employeeDao, departmentDao,salaryDao);
        this.salaryService = new SalaryServiceImpl(salaryDao);
    }

    public DepartmentServiceImpl getDepartmentService() {
        return departmentService;
    }

    public EmployeeServiceImpl getEmployeeService() {
        return employeeService;
    }

    public SalaryServiceImpl getSalaryService() {
        return salaryService;
    }
}
