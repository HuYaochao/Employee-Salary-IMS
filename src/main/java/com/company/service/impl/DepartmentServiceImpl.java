package com.company.service.impl;
import com.company.dao.EmployeeDao;
import  com.company.entity.ImsDepartment;
import com.company.entity.ImsEmployee;
import com.company.service.DepartmentService;
import com.company.dao.DepartmentDao;
import com.company.util.DateUtil;
import com.company.util.UUIDUtil;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author hyc
 * Date: 2024/8/15
 * @version 1.0
 */

public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDao departmentDao;
    private EmployeeDao employeeDao;
    public DepartmentServiceImpl(DepartmentDao departmentDao, EmployeeDao employeeDao) {
        this.departmentDao = departmentDao;
        this.employeeDao = employeeDao;
    }

    @Override
    public boolean addDepartment(String departmentName) {
        // 验证部门名称是否只包含中文
        if (!Pattern.matches("^[\\u4e00-\\u9fa5]+$", departmentName)) {
            System.out.println("部门名称只能包含中文！");
            return false;
        }

        // 检查部门名称是否已存在
        if (departmentDao.isDepartmentNameExists(departmentName)) {
            System.out.println("部门名称已存在！");
            return false;
        }

        // 生成 UUID 作为部门 ID
        String dptId = UUIDUtil.generateUUID();

        // 获取当前时间
        String currentTime = DateUtil.getCurrentTime();

        // 插入部门信息
        ImsDepartment department = new ImsDepartment(dptId, departmentName, 0, currentTime, currentTime);
        System.out.println(department);
        return departmentDao.insertDepartment(department);
    }


    @Override
    public boolean updateDepartment(String departmentId, String newName) {
        // 检查部门是否存在
        ImsDepartment department = departmentDao.getDepartmentById(departmentId);
        if (department == null) {
            System.out.println("部门不存在，无法更新！");
            return false;
        }

        // 更新部门名称
        department.setDptName(newName);
        return departmentDao.updateDepartment(department);
    }

    @Override
    public boolean deleteDepartment(String departmentId) {
        // 检查部门是否存在
        ImsDepartment department = departmentDao.getDepartmentById(departmentId);
        if (department == null) {
            System.out.println("部门不存在，无法删除！");
            return false;
        }

        // 检查是否有员工关联
        List<ImsEmployee> employees = employeeDao.getEmployeesByDepartmentId(departmentId);
        if (!employees.isEmpty()) {
            System.out.println("部门中存在员工，无法删除！");
            return false;
        }


        return departmentDao.updateDepartmentIsDeleted(departmentId, 1);
    }

    @Override
    public ImsDepartment getDepartmentById(String departmentId) {
        return departmentDao.getDepartmentById(departmentId);
    }


    @Override
    public List<ImsDepartment> getAllDepartments() {
        return departmentDao.findAllDepartments();
    }
}

