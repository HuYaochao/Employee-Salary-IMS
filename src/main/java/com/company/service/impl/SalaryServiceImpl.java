package com.company.service.impl;

import com.company.entity.ImsSalary;
import com.company.service.SalaryService;
import com.company.dao.SalaryDao;

import java.math.BigDecimal;
import java.util.List;
/**
 *
 * @author hyc
 * Date: 2024/8/15
 * @version 1.0
 */

public class SalaryServiceImpl implements SalaryService {

    private SalaryDao salaryDao;

    public SalaryServiceImpl(SalaryDao salaryDao) {
        this.salaryDao = salaryDao;
    }

    @Override
    public boolean addSalary(ImsSalary salary) {
        if (isSalaryAlreadyExists(salary.getEmpId(), salary.getSaDate())) {
            System.out.println("该员工的薪资信息已存在，请勿重复录入！");
            return false;
        }
        // 校验薪资字段是否为负数
        if (salary.getSaBase().compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("基础工资不能为负数！");
            return false;
        }
        if (salary.getSaPerformance().compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("绩效工资不能为负数！");
            return false;
        }
        if (salary.getSaInsurance().compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("保险扣除不能为负数！");
            return false;
        }
        if (salary.getSaActual().compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("实发工资不能为负数！");
            return false;
        }

        // 如果所有校验通过，则添加薪资信息

        return salaryDao.addSalary(salary);
    }

    @Override
    public boolean updateSalary(ImsSalary salary) {

        return salaryDao.updateSalary(salary);
    }

    @Override
    public boolean deleteSalary(String salaryId) {
        return salaryDao.deleteSalary(salaryId);
    }

    @Override
    public ImsSalary getSalaryById(String salaryId) {
        return salaryDao.findSalaryById(salaryId);
    }

    @Override
    public List<ImsSalary> getAllSalaries() {
        return salaryDao.findAllSalaries();
    }

    @Override
    public boolean isSalaryAlreadyExists(String employeeId, String salaryDate) {
        return salaryDao.getSalaryByEmployeeAndDate(employeeId, salaryDate) != null;
    }

    @Override
    public ImsSalary getSalaryByEmployeeAndDate(String employeeId, String salaryDate) {
        return salaryDao.getSalaryByEmployeeAndDate(employeeId, salaryDate);
    }

    @Override
    public List<ImsSalary> getSalariesByEmployee(String employeeId) {
        return salaryDao.getSalariesByEmployee(employeeId);
    }

    @Override
    public List<ImsSalary> getSalariesByDate(String salaryDate) {
        return salaryDao.getSalariesByDate(salaryDate);
    }


}

