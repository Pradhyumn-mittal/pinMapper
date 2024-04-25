package com.java.pinMapper.ControllerTest;



import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.java.pinMapper.controller.ErrorHandlerController;
import com.java.pinMapper.controller.PinMapperController;
import com.java.pinMapper.entity.constant.ApiPath;
import com.java.pinMapper.entity.constant.enums.ResponseCode;
import com.java.pinMapper.entity.constant.unit.test.ErrorHandlerTestVariable;
import com.java.pinMapper.entity.pojo.exception.BusinessLogicException;
import com.java.pinMapper.service.api.PinMapperService;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

public class ErrorHandlerTest extends ErrorHandlerTestVariable {

  @InjectMocks
  private PinMapperController pinMapperController;
  @InjectMocks
  private ErrorHandlerController errorHandlerController;

  @Mock
  private PinMapperService pinMapperService;

  private MockMvc mockMvc;


  @Test
  public void runTimeExceptionTest() throws Exception {
    when(pinMapperService.findRouteByPincode(ORIGIN,DESTINATION)).thenThrow(new RuntimeException(ResponseCode.PARSE_ERROR.getCode()));
    this.mockMvc.perform(
        get(ApiPath.PIN_MAPPER+ApiPath.ROUTE)
            .param(ORIGIN_PARAM, String.valueOf(ORIGIN))
            .param(DESTINATION_PARAM, String.valueOf(DESTINATION)))
        .andExpect(status().is5xxServerError())
        .andExpect(jsonPath("$.code",equalTo(ResponseCode.RUNTIME_ERROR.getCode())));

    verify(this.pinMapperService).findRouteByPincode(ORIGIN,DESTINATION);
  }

  @Test
  public void exceptionTest() throws Exception {
    when(pinMapperService.findRouteByPincode(ORIGIN,DESTINATION)).thenThrow(new IOException(ResponseCode.SYSTEM_ERROR.getMessage()));
    this.mockMvc.perform(
            get(ApiPath.PIN_MAPPER+ApiPath.ROUTE)
                .param(ORIGIN_PARAM, String.valueOf(ORIGIN))
                .param(DESTINATION_PARAM, String.valueOf(DESTINATION)))
        .andExpect(status().is5xxServerError())
        .andExpect(jsonPath("$.message",equalTo(ResponseCode.SYSTEM_ERROR.getMessage())));

    verify(this.pinMapperService).findRouteByPincode(ORIGIN,DESTINATION);
  }
  @Test
  public void bleExceptionTest() throws Exception {
    when(pinMapperService.findRouteByPincode(ORIGIN,DESTINATION)).thenThrow(new BusinessLogicException(ResponseCode.DATA_NOT_EXIST));
    this.mockMvc.perform(
            get(ApiPath.PIN_MAPPER+ApiPath.ROUTE)
                .param(ORIGIN_PARAM, String.valueOf(ORIGIN))
                .param(DESTINATION_PARAM, String.valueOf(DESTINATION)))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.message",equalTo(ResponseCode.DATA_NOT_EXIST.getMessage())));

    verify(this.pinMapperService).findRouteByPincode(ORIGIN,DESTINATION);
  }

  @Before
  public void setUp() {
    initMocks(this);

    this.mockMvc = standaloneSetup(this.pinMapperController)
        .setControllerAdvice(errorHandlerController).build();
  }
  @After
  public void teardown()
  {
    verifyNoMoreInteractions(this.pinMapperService);
  }


}
