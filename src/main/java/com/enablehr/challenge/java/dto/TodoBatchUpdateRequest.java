package com.enablehr.challenge.java.dto;

public class TodoBatchUpdateRequest {
  private String action;

  public TodoBatchUpdateRequest() { }

  public TodoBatchUpdateRequest(String action) {
    this.action = action;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }
}
