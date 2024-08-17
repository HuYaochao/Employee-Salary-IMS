package com.company.entity;

/**
 *
 * @author hyc
 * Date: 2024/8/15
 * @version 1.0
 */


public class ImsEmployee {
  private String empId;
  private String dptId;
  private String empName;
  private String empCode;
  private String empSex;
  private int isDeleted;
  private String createTime;
  private String updateTime;

  // 默认构造方法
  public ImsEmployee() {}

  // 带参数的构造方法
  public ImsEmployee(String empId, String dptId, String empName, String empCode, String empSex, int isDeleted, String createTime, String updateTime) {
    this.empId = empId;
    this.dptId = dptId;
    this.empName = empName;
    this.empCode = empCode;
    this.empSex = empSex;
    this.isDeleted = isDeleted;
    this.createTime = createTime;
    this.updateTime = updateTime;
  }

  // Getters and Setters
  public String getEmpId() { return empId; }
  public void setEmpId(String empId) { this.empId = empId; }
  public String getDptId() { return dptId; }
  public void setDptId(String dptId) { this.dptId = dptId; }
  public String getEmpName() { return empName; }
  public void setEmpName(String empName) { this.empName = empName; }
  public String getEmpCode() { return empCode; }
  public void setEmpCode(String empCode) { this.empCode = empCode; }
  public String getEmpSex() { return empSex; }
  public void setEmpSex(String empSex) { this.empSex = empSex; }
  public int getIsDeleted() { return isDeleted; }
  public void setIsDeleted(int isDeleted) { this.isDeleted = isDeleted; }
  public String getCreateTime() { return createTime; }
  public void setCreateTime(String createTime) { this.createTime = createTime; }
  public String getUpdateTime() { return updateTime; }
  public void setUpdateTime(String updateTime) { this.updateTime = updateTime; }

}
