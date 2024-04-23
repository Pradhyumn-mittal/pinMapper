package com.java.pinMapper.controller;

import com.java.pinMapper.entity.constant.enums.ResponseCode;
import com.java.pinMapper.entity.pojo.exception.BusinessLogicException;
import com.java.pinMapper.entity.pojo.response.BaseResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandlerController {
  private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandlerController.class);
  @ExceptionHandler(Exception.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public BaseResponse exception(IOException e) {
    List<String> errors = Arrays.asList(ExceptionUtils.getRootCauseStackTrace(e)).subList(0, 1);
    BaseResponse baseResponse = BaseResponse.constructResponse(
        ResponseCode.SYSTEM_ERROR.getCode(),
        ResponseCode.SYSTEM_ERROR.getMessage(),

        errors, null);

    return baseResponse;
  }
  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public BaseResponse runTimeException(RuntimeException e) {
    List<String> errors = Arrays.asList(ExceptionUtils.getRootCauseStackTrace(e)).subList(0, 1);
    BaseResponse baseResponse = BaseResponse.constructResponse(
        ResponseCode.RUNTIME_ERROR.getCode(),
        ResponseCode.RUNTIME_ERROR.getMessage(),

        errors, null);

    return baseResponse;
  }
  @ExceptionHandler(BusinessLogicException.class)
  public BaseResponse businessLogicException(
      BusinessLogicException ble) {
    List<String> errors = Arrays.asList(ExceptionUtils.getRootCauseStackTrace(ble))
        .subList(0,1);
    BaseResponse baseResponse = BaseResponse
        .constructResponse(ble.getCode(), ble.getMessage(), errors, null);
    return baseResponse;
  }
}
