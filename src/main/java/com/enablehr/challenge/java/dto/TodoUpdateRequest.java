package com.enablehr.challenge.java.dto;

public class TodoUpdateRequest {
  private String text;

  public TodoUpdateRequest() { }
  
  public TodoUpdateRequest(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }
  public void setText(String text) {
    this.text = text;
  }
}
