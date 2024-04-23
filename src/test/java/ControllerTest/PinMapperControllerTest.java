package ControllerTest;


import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.java.pinMapper.controller.PinMapperController;
import com.java.pinMapper.entity.constant.ApiPath;
import com.java.pinMapper.entity.constant.enums.ResponseCode;
import com.java.pinMapper.entity.constant.unit.test.PinMapperTestVariable;
import com.java.pinMapper.service.api.PinMapperService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;

public class PinMapperControllerTest extends PinMapperTestVariable {

  @InjectMocks
  private PinMapperController pinMapperController;

  @Mock
  private PinMapperService pinMapperService;
  private MockMvc mockMvc;

  @Test
  public void findByRouteAndPincodeTest() throws Exception {
    when(pinMapperService.findRouteByPincode(ORIGIN,DESTINATION)).thenReturn(ROUTE_RESPONSE);
    this.mockMvc.perform(
            get(ApiPath.PIN_MAPPER+ApiPath.ROUTE)
                .param(ORIGIN_PARAM, String.valueOf(ORIGIN))
                .param(DESTINATION_PARAM, String.valueOf(DESTINATION)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.message",equalTo(ResponseCode.SUCCESS.getMessage())))
        .andExpect(jsonPath("$.code",equalTo(ResponseCode.SUCCESS.getCode())))
        .andExpect(jsonPath("$.errors", equalTo(null)));

    verify(this.pinMapperService).findRouteByPincode(ORIGIN,DESTINATION);
  }
  @Before
  public void setUp() {
    initMocks(this);

    this.mockMvc = standaloneSetup(this.pinMapperController).build();
  }
  @After
  public void teardown()
  {
    verifyNoMoreInteractions(this.pinMapperService);
  }

}
