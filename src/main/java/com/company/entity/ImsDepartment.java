package com.company.entity;

/**
 *
 * @author hyc
 * Date: 2024/8/15
 * @version 1.0
 */


public class ImsDepartment {
  private String dptId;
  private String dptName;
  private int isDeleted;
  private String createTime;
  private String updateTime;

  public ImsDepartment(String dptId, String dptName, int isDeleted, String createTime, String updateTime) {
    this.dptId = dptId;
    this.dptName = dptName;
    this.isDeleted = isDeleted;
    this.createTime = createTime;
    this.updateTime = updateTime;
  }

    public ImsDepartment() {

    }

    // Getters and Setters
  public String getDptId() { return dptId; }
  public void setDptId(String dptId) { this.dptId = dptId; }
  public String getDptName() { return dptName; }
  public void setDptName(String dptName) { this.dptName = dptName; }
  public int getIsDeleted() { return isDeleted; }
  public void setIsDeleted(int isDeleted) { this.isDeleted = isDeleted; }
  public String getCreateTime() { return createTime; }
  public void setCreateTime(String createTime) { this.createTime = createTime; }
  public String getUpdateTime() { return updateTime; }
  public void setUpdateTime(String updateTime) { this.updateTime = updateTime; }

  @Override
  public String toString() {
    return "ImsDepartment [dptId=" + dptId + ", dptName=" + dptName + ", isDeleted=" + isDeleted
            + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
  }
}

