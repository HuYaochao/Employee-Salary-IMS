package com.company.service;
import com.company.entity.ImsDepartment;
import java.util.List;
/**
 *
 * @author hyc
 * Date: 2024/8/15
 * @version 1.0
 */

public interface DepartmentService {
    boolean addDepartment(String departmentName);
    boolean updateDepartment(String departmentId, String newName);
    boolean deleteDepartment(String departmentId);
    ImsDepartment getDepartmentById(String  departmentId);
    List<ImsDepartment> getAllDepartments();
}

