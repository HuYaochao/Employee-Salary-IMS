package com.company.terminal;

import java.util.Scanner;

/**
 *
 * @author hyc
 * Date: 2024/8/14
 * @version 1.0
 */

public class TerminalController {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("************************************");
            System.out.println("********** 员工薪资管理系统 **********");
            System.out.println("** 1. 部门信息管理 *******************");
            System.out.println("** 2. 员工信息管理 *******************");
            System.out.println("** 3. 薪资信息管理 *******************");
            System.out.println("** 4. 退出 **************************");
            System.out.println("************************************");
            System.out.print("请输入要执行的功能序号: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    DepartmentTerminal departmentTerminal = new DepartmentTerminal();
                    departmentTerminal.showMenu();
                    break;
                case 2:
                    EmployeeTerminal employeeTerminal = new EmployeeTerminal();
                    employeeTerminal.showMenu();
                    break;
                case 3:
                    SalaryTerminal salaryTerminal = new SalaryTerminal();
                    salaryTerminal.showMenu();
                    break;
                case 4:
                    System.out.println("退出系统...");
                    scanner.close();
                    return;
                default:
                    System.out.println("无效的选择，请重新输入。");
            }
        }

    }
}
