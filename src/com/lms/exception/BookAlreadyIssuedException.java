package com.lms.exception;

public class BookAlreadyIssuedException extends Exception {
  public BookAlreadyIssuedException(String message) {
    super(message);
  }
}