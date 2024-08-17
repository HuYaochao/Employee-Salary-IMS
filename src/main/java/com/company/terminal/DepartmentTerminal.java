package com.company.terminal;

import com.company.dao.DepartmentDao;
import com.company.dao.EmployeeDao;
import com.company.dao.impl.DepartmentDaoImpl;
import com.company.dao.impl.EmployeeDaoImpl;
import com.company.entity.ImsDepartment;
import com.company.service.DepartmentService;
import com.company.service.EmployeeService;
import com.company.service.impl.DepartmentServiceImpl;
import com.company.util.TablePrinter;

import java.util.List;
import java.util.Scanner;


/**
 *
 * @author hyc
 * Date: 2024/8/14
 * @version 1.0
 */

public class DepartmentTerminal extends BaseTerminal {
    private DepartmentService departmentService;

    private Scanner scanner;
    public DepartmentTerminal() {
        this.moduleName = "部门";
        // 使用 ServiceInitializer 初始化
        ServiceInitializer initializer = new ServiceInitializer();
        this.departmentService = initializer.getDepartmentService();
    }

    @Override
    public void showMenu() {
        super.showMenu();
    }

    @Override
    protected void add() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入部门名称（仅允许中文）:");

        String departmentName = scanner.nextLine();
        // 保存部门
        boolean success = departmentService.addDepartment(departmentName);
        if (success) {
            System.out.println("部门信息新增成功！");
        } else {
            System.out.println("部门信息新增失败！");
        }
    }

    @Override
    protected void delete() {
        Scanner scanner = new Scanner(System.in);
        // 显示部门列表
        retrieve();
        // 获取用户输入的序号
        System.out.println("请输入要删除的部门序号：");
        // 序号从 1 开始，所以减去 1
        int index = scanner.nextInt() - 1;

        // 获取部门列表
        List<ImsDepartment> departments = departmentService.getAllDepartments();
        if (index < 0 || index >= departments.size()) {
            System.out.println("无效的序号！");
            return;
        }

        // 获取对应的部门ID
        String departmentId = departments.get(index).getDptId();

        // 调用业务逻辑层删除部门
        boolean success = departmentService.deleteDepartment(departmentId);

        if (success) {
            System.out.println("部门删除成功！");
        } else {
            System.out.println("部门删除失败！");
        }
    }

    @Override
    protected void update() {
        Scanner scanner = new Scanner(System.in);

        // 查询并显示所有部门
        List<ImsDepartment> departments = departmentService.getAllDepartments();
        if (departments.isEmpty()) {
            System.out.println("没有找到部门信息！");
            return;
        }

        // 显示部门列表
        System.out.println("以下是所有部门：");
        String[] headers = {"序号", "部门名称"};
        String[][] data = new String[departments.size()][headers.length];
        for (int i = 0; i < departments.size(); i++) {
            ImsDepartment dept = departments.get(i);
            data[i][0] = String.valueOf(i + 1);
            data[i][1] = dept.getDptName();
        }
        TablePrinter.printTable(headers, data);

        // 选择要更新的部门
        System.out.println("请输入要更新的部门序号：");
        int index = scanner.nextInt();
        scanner.nextLine(); // 清理输入缓冲区

        if (index < 1 || index > departments.size()) {
            System.out.println("无效的序号！");
            return;
        }

        ImsDepartment selectedDepartment = departments.get(index - 1);
        String departmentId = selectedDepartment.getDptId();

        // 输入新的部门名称
        System.out.println("请输入新的部门名称：");
        String newName = scanner.nextLine();

        // 更新部门
        boolean success = departmentService.updateDepartment(departmentId, newName);

        if (success) {
            System.out.println("部门更新成功！");
        } else {
            System.out.println("部门更新失败！");
        }
    }


    @Override
    protected void retrieve() {
        List<ImsDepartment> departments = departmentService.getAllDepartments();
        if (departments.isEmpty()) {
            System.out.println("没有找到部门信息！");
            return;
        }

        // 创建表头
        String[] headers = {"序号", "部门名称"};

        // 将部门信息转换为二维数组
        String[][] data = new String[departments.size()][headers.length];
        for (int i = 0; i < departments.size(); i++) {
            ImsDepartment dept = departments.get(i);
            // 序号
            data[i][0] = String.valueOf(i + 1);
            // 部门名称
            data[i][1] = dept.getDptName();
        }

        // 打印表格
        TablePrinter.printTable(headers, data);
    }

    public ImsDepartment getDepartmentByIndex(int index) {
        List<ImsDepartment> departments = departmentService.getAllDepartments();
        if (index < 1 || index > departments.size()) {
            return null; // 返回 null 表示索引无效
        }
        return departments.get(index - 1); // 转换为 0 基索引
    }



}


