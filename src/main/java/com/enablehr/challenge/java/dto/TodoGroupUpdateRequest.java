package com.enablehr.challenge.java.dto;

public class TodoGroupUpdateRequest {
  private String groupName;
  
  public TodoGroupUpdateRequest() { }
  
  public TodoGroupUpdateRequest(String groupName) {
    this.groupName = groupName;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }
}
