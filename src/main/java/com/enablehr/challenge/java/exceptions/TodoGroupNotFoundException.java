package com.enablehr.challenge.java.exceptions;

public class TodoGroupNotFoundException extends RuntimeException {

  public TodoGroupNotFoundException(String msg) {
    super(msg);
  } 

  public static TodoGroupNotFoundException withName(String name) {
    return new TodoGroupNotFoundException(
      "TodoGroup with name: " + name + ", was not found."
    );
  }
}
