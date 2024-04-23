package com.java.pinMapper.entity.pojo.exception;

public class ErrorRes {
  private int errorCode;
  private String errorMessage;

  // Constructors
  public ErrorRes() {
  }

  public ErrorRes(int errorCode, String errorMessage) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

  // Getters and Setters
  public int getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(int errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}