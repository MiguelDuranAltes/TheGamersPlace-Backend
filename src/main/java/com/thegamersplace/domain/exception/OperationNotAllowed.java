package com.thegamersplace.domain.exception;

public class OperationNotAllowed extends ModelException {
  public OperationNotAllowed(String msg) {
    super("Operation not allowed: " + msg);
  }
}
