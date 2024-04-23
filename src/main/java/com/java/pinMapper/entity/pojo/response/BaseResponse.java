package com.java.pinMapper.entity.pojo.response;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> implements Serializable {

  private static final long serialVersionUID = 1L;
  private String code;
  private String message;
  private List<String> errors;
  private T data;
  public static <T> BaseResponse<T> constructResponse(String code, String message, List<String> errors, T data) {
    return BaseResponse.<T>builder().code(code).message(message).data(data).errors(errors).build();
  }
}