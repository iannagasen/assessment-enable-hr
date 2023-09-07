package com.enablehr.challenge.java.exceptions;

public class UnknownTodoActionException extends RuntimeException {

  public UnknownTodoActionException(String msg) {
    super(msg);
  }

  public static UnknownTodoActionException withAction(String action) {
    return new UnknownTodoActionException("Unknown todo action: " + action);
  }
}
