package com.company.terminal;

import com.company.entity.ImsEmployee;
import com.company.entity.ImsSalary;
import com.company.service.DepartmentService;
import com.company.service.EmployeeService;
import com.company.service.SalaryService;
import com.company.util.DateUtil;
import com.company.util.TablePrinter;
import com.company.util.UUIDUtil;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author hyc
 * Date: 2024/8/14
 * @version 1.0
 */

public class SalaryTerminal extends BaseTerminal {
    private final EmployeeTerminal employeeTerminal;
    private SalaryService salaryService;
    private EmployeeService employeeService;
    private DepartmentService departmentService;

    public SalaryTerminal() {
        this.moduleName = "薪资";
        // 使用 ServiceInitializer 初始化
        ServiceInitializer initializer = new ServiceInitializer();
        this.salaryService = initializer.getSalaryService();
        this.employeeService = initializer.getEmployeeService();
        this.departmentService = initializer.getDepartmentService();
        this.employeeTerminal = new EmployeeTerminal();
    }

    @Override
    public void showMenu() {
        super.showMenu();
    }

    @Override
    protected void add() {
        Scanner scanner = new Scanner(System.in);

        // 调用 EmployeeTerminal 的 retrieve 方法展示员工信息
        employeeTerminal.retrieve();

        System.out.println("请输入员工序号：");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        List<ImsEmployee> employees = employeeService.getAllEmployees();
        if (index < 0 || index >= employees.size()) {
            System.out.println("无效的序号！");
            return;
        }
        ImsEmployee selectedEmployee = employees.get(index);
        String employeeId = selectedEmployee.getEmpId();

        System.out.println("请输入工资日期（格式: yyyy-MM）：");
        String saDate = scanner.nextLine();

        // 检查当月薪资是否已经录入
        ImsSalary existingSalary = salaryService.getSalaryByEmployeeAndDate(employeeId, saDate);
        if (existingSalary != null) {
            System.out.println("该员工本月薪资已录入，请勿重复录入！");
            return;
        }

        // 获取薪资信息
        System.out.println("请输入基础工资：");
        BigDecimal baseSalary = scanner.nextBigDecimal();
        System.out.println("请输入绩效工资：");
        BigDecimal performanceSalary = scanner.nextBigDecimal();
        System.out.println("请输入保险扣除：");
        BigDecimal insuranceDeduction = scanner.nextBigDecimal();
        scanner.nextLine();  // 清除输入缓冲区中的换行符

        // 计算实际工资
        BigDecimal actualSalary = baseSalary.add(performanceSalary).subtract(insuranceDeduction);

        // 创建薪资对象
        ImsSalary salary = new ImsSalary();
        salary.setSaId(UUIDUtil.generateUUID());
        salary.setEmpId(employeeId);
        salary.setSaDate(saDate);
        salary.setSaBase(baseSalary);
        salary.setSaPerformance(performanceSalary);
        salary.setSaInsurance(insuranceDeduction);
        salary.setSaActual(actualSalary);
        salary.setIsDeleted(0);
        salary.setCreateTime(DateUtil.getCurrentTime());
        salary.setUpdateTime(DateUtil.getCurrentTime());

        // 添加薪资信息
        boolean success = salaryService.addSalary(salary);

        if (success) {
            System.out.println("薪资信息录入成功！");
        } else {
            System.out.println("薪资信息录入失败！");
        }
    }


    @Override
    protected void update() {
        Scanner scanner = new Scanner(System.in);

        // 调用 EmployeeTerminal 的 retrieve 方法展示员工信息
        employeeTerminal.retrieve();

        System.out.println("请输入员工序号：");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        List<ImsEmployee> employees = employeeService.getAllEmployees();
        if (index < 0 || index >= employees.size()) {
            System.out.println("无效的序号！");
            return;
        }
        ImsEmployee selectedEmployee = employees.get(index);
        String employeeId = selectedEmployee.getEmpId();

        System.out.println("请输入工资日期（格式: yyyy-MM）：");
        String saDate = scanner.nextLine();

        // 检查当月薪资是否已经录入
        ImsSalary existingSalary = salaryService.getSalaryByEmployeeAndDate(employeeId, saDate);
        if (existingSalary == null) {
            System.out.println("该员工本月薪资尚未录入，无法进行修改！");
            return;
        }

        // 获取修改后的薪资信息
        System.out.println("请输入新的基础工资：");
        BigDecimal baseSalary = scanner.nextBigDecimal();
        System.out.println("请输入新的绩效工资：");
        BigDecimal performanceSalary = scanner.nextBigDecimal();
        System.out.println("请输入新的保险扣除：");
        BigDecimal insuranceDeduction = scanner.nextBigDecimal();
        scanner.nextLine();  // 清除输入缓冲区中的换行符

        // 计算实际工资
        BigDecimal actualSalary = baseSalary.add(performanceSalary).subtract(insuranceDeduction);

        // 更新薪资对象
        existingSalary.setSaBase(baseSalary);
        existingSalary.setSaPerformance(performanceSalary);
        existingSalary.setSaInsurance(insuranceDeduction);
        existingSalary.setSaActual(actualSalary);
        existingSalary.setUpdateTime(DateUtil.getCurrentTime());

        // 保存更新后的薪资信息
        boolean success = salaryService.updateSalary(existingSalary);

        if (success) {
            System.out.println("薪资信息修改成功！");
        } else {
            System.out.println("薪资信息修改失败！");
        }
    }


    @Override
    protected void delete() {
        Scanner scanner = new Scanner(System.in);

        employeeTerminal.retrieve();

        System.out.println("请输入员工序号：");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        List<ImsEmployee> employees = employeeService.getAllEmployees();
        if (index < 0 || index >= employees.size()) {
            System.out.println("无效的序号！");
            return;
        }
        ImsEmployee selectedEmployee = employees.get(index);
        String employeeId = selectedEmployee.getEmpId();

        System.out.println("请输入薪资日期（格式: yyyy-MM）：");
        String saDate = scanner.nextLine();

        // 检查当月薪资是否存在
        ImsSalary existingSalary = salaryService.getSalaryByEmployeeAndDate(employeeId, saDate);
        if (existingSalary == null) {
            System.out.println("该员工本月薪资记录不存在，无法删除！");
            return;
        }

        // 确认删除操作
        System.out.println("确认删除薪资记录？(yes/no)：");
        String confirmation = scanner.nextLine();
        if (!confirmation.equalsIgnoreCase("yes")) {
            System.out.println("取消删除操作！");
            return;
        }

        // 执行删除操作
        boolean success = salaryService.deleteSalary(existingSalary.getSaId());
        if (success) {
            System.out.println("薪资记录删除成功！");
        } else {
            System.out.println("薪资记录删除失败！");
        }
    }

    @Override
    protected void retrieve() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请选择查询方式：");
        System.out.println("1. 按员工查询");
        System.out.println("2. 按日期查询");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                retrieveByEmployee();
                break;
            case 2:
                retrieveByDate();
                break;
            default:
                System.out.println("无效的选择！");
                break;
        }
    }

    private void retrieveByEmployee() {
        Scanner scanner = new Scanner(System.in);

        employeeTerminal.retrieve(); // 显示员工信息

        System.out.println("请输入员工序号：");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        List<ImsEmployee> employees = employeeService.getAllEmployees();
        if (index < 0 || index >= employees.size()) {
            System.out.println("无效的序号！");
            return;
        }
        ImsEmployee selectedEmployee = employees.get(index);
        String employeeId = selectedEmployee.getEmpId();

        List<ImsSalary> salaries = salaryService.getSalariesByEmployee(employeeId);
        if (salaries.isEmpty()) {
            System.out.println("该员工没有薪资记录！");
            return;
        }

        String[] headers = {"工资ID", "工资日期", "基础工资", "绩效工资", "保险扣除", "实发工资"};
        String[][] data = new String[salaries.size()][headers.length];

        for (int i = 0; i < salaries.size(); i++) {
            ImsSalary salary = salaries.get(i);
            data[i][0] = salary.getSaId();
            data[i][1] = salary.getSaDate();
            data[i][2] = salary.getSaBase().toString();
            data[i][3] = salary.getSaPerformance().toString();
            data[i][4] = salary.getSaInsurance().toString();
            data[i][5] = salary.getSaActual().toString();
        }

        TablePrinter.printTable(headers, data);
    }

    private void retrieveByDate() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入工资日期（格式: yyyy-MM）：");
        String salaryDate = scanner.nextLine();

        List<ImsSalary> salaries = salaryService.getSalariesByDate(salaryDate);
        if (salaries.isEmpty()) {
            System.out.println("该日期没有薪资记录！");
            return;
        }

        String[] headers = {"工资ID", "员工ID", "工资日期", "基础工资", "绩效工资", "保险扣除", "实发工资"};
        String[][] data = new String[salaries.size()][headers.length];

        for (int i = 0; i < salaries.size(); i++) {
            ImsSalary salary = salaries.get(i);
            data[i][0] = salary.getSaId();
            data[i][1] = salary.getEmpId();
            data[i][2] = salary.getSaDate();
            data[i][3] = salary.getSaBase().toString();
            data[i][4] = salary.getSaPerformance().toString();
            data[i][5] = salary.getSaInsurance().toString();
            data[i][6] = salary.getSaActual().toString();
        }

        TablePrinter.printTable(headers, data);

    }

}



