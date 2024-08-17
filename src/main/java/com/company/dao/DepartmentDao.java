package com.company.dao;
import com.company.entity.ImsDepartment;
import java.util.List;
/**
 *
 * @author hyc
 * Date: 2024/8/15
 * @version 1.0
 */

public interface DepartmentDao {
    boolean insertDepartment(ImsDepartment department);
    boolean isDepartmentNameExists(String departmentName);
    boolean updateDepartment(ImsDepartment department);


    /**
     * 逻辑删除部门
     *
     * @param departmentId 部门ID
     * @param isDeleted 逻辑删除标识 (0:未删除;1:已删除)
     * @return 成功返回 true，失败返回 false
     */
    boolean updateDepartmentIsDeleted(String departmentId, int isDeleted);


    void deleteDepartment(String departmentId);


    ImsDepartment getDepartmentById(String departmentId);
    List<ImsDepartment> findAllDepartments();

    ImsDepartment getDepartmentByName(String departmentName);

}

