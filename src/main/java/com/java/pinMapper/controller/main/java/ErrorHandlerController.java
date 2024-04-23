package com.java.pinMapper.controller.main.java;

import com.java.pinMapper.entity.constant.enums.ResponseCode;
import com.java.pinMapper.entity.pojo.exception.BusinessLogicException;
import com.java.pinMapper.entity.pojo.response.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandlerController {
  private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandlerController.class);
  @ExceptionHandler(BindException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public BaseResponse bindException(BindException bindException) {
    LOGGER.info("BindException = {}", bindException.getMessage(), bindException);
    List<FieldError> bindErrors = bindException.getFieldErrors();
    List<String> errors = new ArrayList<>();
    for (FieldError fieldError : bindErrors) {
      errors.add(fieldError.getField() + " " + fieldError.getDefaultMessage());
    }

    BaseResponse baseResponse = BaseResponse.constructResponse(ResponseCode.BIND_ERROR.getCode(),
        ResponseCode.BIND_ERROR.getMessage(),
        errors, null);

    return baseResponse;
  }
  @ExceptionHandler(Exception.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public BaseResponse exception(Exception e) {
    LOGGER.warn("Exception = {}", e.getMessage(), e);
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
    LOGGER.warn("Exception = {}", e.getMessage(), e);
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
    LOGGER.info("BusinessLogicException = {}", ble.getMessage(), ble);
    List<String> errors = Arrays.asList(ExceptionUtils.getRootCauseStackTrace(ble))
        .subList(0,1);
    BaseResponse baseResponse = BaseResponse
        .constructResponse(ble.getCode(), ble.getMessage(), errors, null);
    return baseResponse;
  }
}
