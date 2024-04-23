package com.java.pinMapper.entity.constant.enums;

import lombok.Getter;

@Getter
public enum ResponseCode {
  SUCCESS("SUCCESS", "SUCCESS"),
  SYSTEM_ERROR("SYSTEM_ERROR", "Contact our team"),
  DUPLICATE_DATA("DUPLICATE_DATA", "Duplicate data"),
  DATA_NOT_EXIST("DATA_NOT_EXIST", "No data exist in storage"),
  UPDATE_DATA_ERROR("UPDATE_DATA_ERROR", "Update data failed"),
  CREATE_DATA_ERROR("CREATE_DATA_ERROR", "Create data failed"),
  DELETE_DATA_ERROR("DELETE_DATA_ERROR", "Delete data failed"),
  DATA_NOT_MATCH("DATA_NOT_MATCH", "Data Not Match"),
  DATA_EMPTY("DATA_EMPTY", "Data Empty or null"),
  DATA_NOT_VALID("DATA_NOT_VALID", "Data Not Valid"),
  BIND_ERROR("BIND_ERROR", "Please fill in mandatory parameter"),
  RUNTIME_ERROR("RUNTIME_ERROR", "Runtime Error"),
  PARSE_ERROR("PARSE_ERROR", "Parsing data Error"),
  HTTP_ERROR("HTTP_ERROR", "Sorry, we are not able to process your request. Please try again later."),
  API_CALL_ERROR("API_CALL_ERROR", "Error calling to API");


  private final String code;
  private final String message;

  ResponseCode(String code, String message) {
    this.code = code;
    this.message = message;
  }

}
