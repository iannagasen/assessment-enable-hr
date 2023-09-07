package com.enablehr.challenge.java.dto;

public class TodoUpdateRequest {
  private int id;
  private String updatedText;

  public TodoUpdateRequest() { }
  
  public TodoUpdateRequest(int id, String updatedText) {
    this.id = id;
    this.updatedText = updatedText;
  }

  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getUpdatedText() {
    return updatedText;
  }
  public void setUpdatedText(String updatedText) {
    this.updatedText = updatedText;
  }
}
