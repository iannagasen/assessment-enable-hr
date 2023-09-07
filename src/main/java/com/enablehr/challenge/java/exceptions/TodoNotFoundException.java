package com.enablehr.challenge.java.exceptions;

public class TodoNotFoundException extends RuntimeException {
  
  public TodoNotFoundException(String msg) {
    super(msg);
  }

  public static TodoNotFoundException withId(Integer id) {
    return new TodoNotFoundException(
      "Todo item with id: " + id + ", was not found."
    );
  }
}
