package com.enablehr.challenge.java.dto;

public class TodoRequest {
  private String text;
  private boolean isCompleted;
  
  public TodoRequest() { }

  public TodoRequest(String text, boolean isCompleted) {
    this.text = text;
    this.isCompleted = isCompleted;
  }

  public String getText() {
    return text;
  }
  public void setText(String text) {
    this.text = text;
  }
  public boolean isCompleted() {
    return isCompleted;
  }
  public void setCompleted(boolean isCompleted) {
    this.isCompleted = isCompleted;
  }
}
