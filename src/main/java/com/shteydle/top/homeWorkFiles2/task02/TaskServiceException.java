package com.shteydle.top.homeWorkFiles2.task02;

public class TaskServiceException extends Exception {

  public TaskServiceException() {
  }

  public TaskServiceException(String message) {
    super(message);
  }

  public TaskServiceException(String message, Throwable cause) {
    super(message, cause);
  }

  public TaskServiceException(Throwable cause) {
    super(cause);
  }
}
