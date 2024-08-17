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

            // 读取整行输入
            String input = scanner.nextLine();
            try {
                // 尝试将输入转换为整数
                int choice = Integer.parseInt(input);
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
                        System.out.println("无效的选择，请重新输入 1 到 4 之间的数字。");
                }
            } catch (NumberFormatException e) {
                System.out.println("无效的输入！请输入一个有效的数字。");
            }

        }

    }
}
