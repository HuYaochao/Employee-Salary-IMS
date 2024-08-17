package com.company.terminal;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author hyc
 * Date: 2024/8/14
 * @version 1.0
 */

public abstract class BaseTerminal {

    protected String moduleName;

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("************************************");
            System.out.println("********** " + moduleName + " 数据管理 **********");
            System.out.println("** 1. 新增 " + moduleName + " *******************");
            System.out.println("** 2. 修改 " + moduleName + " *******************");
            System.out.println("** 3. 删除 " + moduleName + " *******************");
            System.out.println("** 4. 查询 " + moduleName + " *******************");
            System.out.println("** 5. 返回主菜单 *******************");
            System.out.println("************************************");
            System.out.print("请输入执行的功能序号 (1-5): ");

            int choice;
            while (true) {
                String input = scanner.nextLine(); // 读取整行输入
                try {
                    choice = Integer.parseInt(input); // 尝试将输入转换为整数
                    if (choice >= 1 && choice <= 5) {
                        break; // 输入有效，退出循环
                    } else {
                        System.out.println("无效的选择！请输入 1 到 5 之间的数字。");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("无效的输入！请输入一个有效的数字。");
                }
            }

            switch (choice) {
                case 1:
                    System.out.println("新增 " + moduleName);
                    add();
                    break;
                case 2:
                    System.out.println("修改 " + moduleName);
                    update();
                    break;
                case 3:
                    System.out.println("删除 " + moduleName);
                    delete();
                    break;
                case 4:
                    System.out.println("查询 " + moduleName);
                    retrieve();
                    break;
                case 5:
                    return;
                default:
                    // 这个 default 分支理论上不会被执行
                    System.out.println("无效的选择，请重新输入。");
            }
        }

    }

    protected abstract void add();
    protected abstract void update();
    protected abstract void delete();
    protected abstract void retrieve();
}

