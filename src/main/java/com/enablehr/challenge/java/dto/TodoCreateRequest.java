package com.enablehr.challenge.java.dto;

import com.enablehr.challenge.java.entity.Todo;

public class TodoCreateRequest {
  private String text;
  private boolean isCompleted;

  public Todo toEntity() {
    Todo todo = new Todo();
    todo.setCompleted(isCompleted);
    todo.setText(text);
    return todo;
  }
  
  // constructors, getters, setters
  public TodoCreateRequest() { }

  public TodoCreateRequest(String text, boolean isCompleted) {
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
