package com.utc.exception;

public class GameException extends RuntimeException {
  
  private ExceptionType type;
  
  public GameException(ExceptionType type) {
    super(type.getMessage());
  }
}
