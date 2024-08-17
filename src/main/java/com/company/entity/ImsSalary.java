package com.company.entity;
import java.math.BigDecimal;

/**
 *
 * @author hyc
 * Date: 2024/8/15
 * @version 1.0
 */


public class ImsSalary {
  private String saId;
  private String empId;
  private String saDate;
  private BigDecimal saBase;
  private BigDecimal saPerformance;
  private BigDecimal saInsurance;
  private BigDecimal saActual;
  private int isDeleted;
  private String createTime;
  private String updateTime;

  // 默认构造方法
  public ImsSalary() {}

  // 带参数的构造方法
  public ImsSalary(String saId, String empId, String saDate, BigDecimal saBase, BigDecimal saPerformance, BigDecimal saInsurance, BigDecimal saActual, int isDeleted, String createTime, String updateTime) {
    this.saId = saId;
    this.empId = empId;
    this.saDate = saDate;
    this.saBase = saBase;
    this.saPerformance = saPerformance;
    this.saInsurance = saInsurance;
    this.saActual = saActual;
    this.isDeleted = isDeleted;
    this.createTime = createTime;
    this.updateTime = updateTime;
  }

  // Getters and Setters
  public String getSaId() { return saId; }
  public void setSaId(String saId) { this.saId = saId; }
  public String getEmpId() { return empId; }
  public void setEmpId(String empId) { this.empId = empId; }
  public String getSaDate() { return saDate; }
  public void setSaDate(String saDate) { this.saDate = saDate; }
  public BigDecimal getSaBase() { return saBase; }
  public void setSaBase(BigDecimal saBase) { this.saBase = saBase; }
  public BigDecimal getSaPerformance() { return saPerformance; }
  public void setSaPerformance(BigDecimal saPerformance) { this.saPerformance = saPerformance; }
  public BigDecimal getSaInsurance() { return saInsurance; }
  public void setSaInsurance(BigDecimal saInsurance) { this.saInsurance = saInsurance; }
  public BigDecimal getSaActual() { return saActual; }
  public void setSaActual(BigDecimal saActual) { this.saActual = saActual; }
  public int getIsDeleted() { return isDeleted; }
  public void setIsDeleted(int isDeleted) { this.isDeleted = isDeleted; }
  public String getCreateTime() { return createTime; }
  public void setCreateTime(String createTime) { this.createTime = createTime; }
  public String getUpdateTime() { return updateTime; }
  public void setUpdateTime(String updateTime) { this.updateTime = updateTime; }

  @Override
  public String toString() {
    return "ImsSalary [saId=" + saId + ", empId=" + empId + ", saDate=" + saDate + ", saBase=" + saBase + ", saPerformance=" + saPerformance + ", saInsurance=" + saInsurance + ", saActual=" + saActual + ", isDeleted=" + isDeleted + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
  }
}
