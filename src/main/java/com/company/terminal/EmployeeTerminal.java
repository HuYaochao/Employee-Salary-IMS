package com.company.terminal;
import com.company.entity.ImsDepartment;
import com.company.entity.ImsEmployee;
import com.company.service.DepartmentService;
import com.company.service.EmployeeService;
import com.company.util.TablePrinter;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author hyc
 * Date: 2024/8/14
 * @version 1.0
 */

public class EmployeeTerminal extends BaseTerminal {
    private EmployeeService employeeService;
    private DepartmentService departmentService;
    private DepartmentTerminal departmentTerminal;
    public EmployeeTerminal() {
        this.moduleName = "员工";
        // 使用 ServiceInitializer 初始化
        ServiceInitializer initializer = new ServiceInitializer();
        this.departmentTerminal = new DepartmentTerminal();
        this.employeeService = initializer.getEmployeeService();
        this.departmentService = initializer.getDepartmentService();
    }


    @Override
    public void showMenu() {
        super.showMenu();
    }

    @Override
    protected void add() {
        Scanner scanner = new Scanner(System.in);

        // 获取用户输入的员工信息
        System.out.println("请输入员工姓名：");
        String empName = scanner.nextLine();

        System.out.println("请输入员工性别 (MALE / FEMALE)：");
        String empSex = scanner.nextLine().toUpperCase();


        departmentTerminal.retrieve();

        System.out.println("请输入员工所属部门的序号：");
        int departmentIndex = -1;
        while (true) {
            try {
                String input = scanner.nextLine();
                departmentIndex = Integer.parseInt(input);
                ImsDepartment department = departmentTerminal.getDepartmentByIndex(departmentIndex);
                if (department != null) {
                    System.out.println("选定的部门是：" + department.getDptName());
                    // 调用业务逻辑层添加员工
                    boolean success = employeeService.addEmployee(empName, empSex, department.getDptName());

                    if (success) {
                        System.out.println("员工添加成功！");
                    } else {
                        System.out.println("员工添加失败，请检查输入信息。");
                    }
                    break;
                } else {
                    System.out.println("无效的序号！请输入有效的部门序号。");
                }
            } catch (NumberFormatException e) {
                System.out.println("无效的输入！请输入一个有效的数字。");
            }
        }
    }



    @Override
    protected void update() {
        Scanner scanner = new Scanner(System.in);

        // 显示现有员工列表
        retrieve();

        System.out.println("请输入要修改的员工序号：");
        int employeeIndex = scanner.nextInt();
        scanner.nextLine();  // 处理换行符

        // 获取所有员工
        List<ImsEmployee> employees = employeeService.getAllEmployees();
        if (employeeIndex < 1 || employeeIndex > employees.size()) {
            System.out.println("无效的员工序号！");
            return;
        }

        // 获取要修改的员工信息
        ImsEmployee employee = employees.get(employeeIndex - 1);

        // 显示员工当前信息
        System.out.println("当前员工信息：");
        System.out.println("员工姓名：" + employee.getEmpName());
        System.out.println("员工性别：" + employee.getEmpSex());

        // 修改员工信息
        System.out.println("请输入新的员工姓名（留空则不修改）：");
        String newEmpName = scanner.nextLine();
        if (!newEmpName.trim().isEmpty()) {
            employee.setEmpName(newEmpName);
        }

        System.out.println("请输入新的员工性别 (MALE / FEMALE)（留空则不修改）：");
        String newEmpSex = scanner.nextLine().toUpperCase();
        if (!newEmpSex.trim().isEmpty()) {
            employee.setEmpSex(newEmpSex);
        }

        // 调用业务逻辑层更新员工信息
        boolean success = employeeService.updateEmployee(employee);

        if (success) {
            System.out.println("员工信息更新成功！");
        } else {
            System.out.println("员工信息更新失败！");
        }
    }
    @Override
    protected void delete() {
        Scanner scanner = new Scanner(System.in);

        // 显示所有员工信息
        retrieve();

        // 获取用户输入的序号
        System.out.println("请输入要删除的员工序号：");
        int index = scanner.nextInt();

        // 获取所有员工信息
        List<ImsEmployee> employees = employeeService.getAllEmployees();
        if (employees.isEmpty() || index < 1 || index > employees.size()) {
            System.out.println("无效的序号，请检查输入！");
            return;
        }

        // 获取要删除的员工ID
        ImsEmployee employee = employees.get(index - 1);
        String empId = employee.getEmpId();

        // 调用业务逻辑层删除员工
        boolean success = employeeService.deleteEmployee(empId);

        if (success) {
            System.out.println("员工删除成功！");
        } else {
            System.out.println("员工删除失败，请检查输入信息！");
        }
    }



    @Override
    protected void retrieve() {
        List<ImsEmployee> employees = employeeService.getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("没有找到员工信息！");
            return;
        }

        String[] headers = {"序号", "员工姓名", "性别", "员工编码", "部门名称"};
        String[][] data = new String[employees.size()][headers.length];

        for (int i = 0; i < employees.size(); i++) {
            ImsEmployee emp = employees.get(i);
            ImsDepartment dept = departmentService.getDepartmentById(emp.getDptId());
            data[i][0] = String.valueOf(i + 1);
            data[i][1] = emp.getEmpName();
            data[i][2] = emp.getEmpSex();
            data[i][3] = emp.getEmpCode();
            data[i][4] = (dept != null) ? dept.getDptName() : "未知";
        }

        TablePrinter.printTable(headers, data);
    }
}

