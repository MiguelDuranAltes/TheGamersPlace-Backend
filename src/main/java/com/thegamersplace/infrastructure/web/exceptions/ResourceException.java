package com.thegamersplace.infrastructure.web.exceptions;

public class ResourceException extends Exception {
  public ResourceException(String errorMsg) {
    super(errorMsg);
  }
}
