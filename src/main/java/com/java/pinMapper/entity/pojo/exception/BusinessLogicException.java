package com.java.pinMapper.entity.pojo.exception;

import com.java.pinMapper.entity.constant.enums.ResponseCode;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data

public class BusinessLogicException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private String code;
  private String message;
  private List<String> errors;


  public BusinessLogicException(String code, String message) {
    super();
    this.setCode(code);
    this.setMessage(message);
  }

  public BusinessLogicException(ResponseCode responseCode) {
    super();
    this.setCode(responseCode.getCode());
    this.setMessage(responseCode.getMessage());
  }
  public BusinessLogicException(String code, String message, List<String> errors) {
    super();
    this.setCode(code);
    this.setMessage(message);
    this.setErrors(errors);
  }
}