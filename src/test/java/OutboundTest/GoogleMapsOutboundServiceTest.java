package OutboundTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.java.pinMapper.configuration.GoogleMapsConfiguration;
import com.java.pinMapper.configuration.GoogleMapsEndpointService;
import com.java.pinMapper.entity.constant.unit.test.GoogleMapsOutboundTestVariable;
import com.java.pinMapper.entity.pojo.outbound.GoogleRouteResponse;
import com.java.pinMapper.outbound.impl.GoogleMapsOutboundServiceImpl;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import retrofit2.Call;
import retrofit2.Response;

public class GoogleMapsOutboundServiceTest extends GoogleMapsOutboundTestVariable {
  @InjectMocks
   GoogleMapsOutboundServiceImpl googleMapsOutboundService;
  @Mock
   GoogleMapsEndpointService googleMapsEndpointService;

  @Mock
   GoogleMapsConfiguration googleMapsConfiguration;

  @Before
  public void setUp() {
    initMocks(this);
  }

  @After
  public void teardown(){
  }

  @Test
  public void testFindRouteInfo_SuccessfulResponse() throws IOException {

    when(googleMapsConfiguration.getBase_url()).thenReturn(BASE_URL);
    when(googleMapsConfiguration.getApi_key()).thenReturn(API_KEY);

    Call<GoogleRouteResponse> callMock = mock(Call.class);
    when(callMock.execute()).thenReturn(Response.success(GOOGLE_ROUTE_RESPONSE));

    when(googleMapsEndpointService.findRoutes(anyString(), anyString(), anyString(), anyBoolean()))
        .thenReturn(callMock);

    GoogleRouteResponse result = googleMapsOutboundService.findRouteInfo(ORIGIN, DESTINATION);

    assertEquals(GOOGLE_ROUTE_RESPONSE, result);

    verify(googleMapsConfiguration).getApi_key();
    verify(googleMapsEndpointService).findRoutes(anyString(), anyString(), anyString(), anyBoolean());
  }

  @Test(expected = RuntimeException.class)
  public void testFindRouteInfo_IOException() throws IOException {
    when(googleMapsConfiguration.getBase_url()).thenReturn(BASE_URL);
    when(googleMapsConfiguration.getApi_key()).thenReturn(API_KEY);

    Call<GoogleRouteResponse> callMock = mock(Call.class);
    when(callMock.execute()).thenThrow(new IOException());

    when(googleMapsEndpointService.findRoutes(anyString(), anyString(), anyString(), anyBoolean()))
        .thenReturn(callMock);

    googleMapsOutboundService.findRouteInfo(ORIGIN, DESTINATION);


    verify(googleMapsConfiguration).getApi_key();
    verify(googleMapsEndpointService).findRoutes(anyString(), anyString(), anyString(), anyBoolean());
  }

  @Test(expected = RuntimeException.class)
  public void testFindRouteInfo_UnsuccessfulResponse() throws IOException {
    when(googleMapsConfiguration.getBase_url()).thenReturn(BASE_URL);
    when(googleMapsConfiguration.getApi_key()).thenReturn(API_KEY);


    Call<GoogleRouteResponse> callMock = mock(Call.class);
    when(callMock.execute()).thenReturn(Response.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), null));



    when(googleMapsEndpointService.findRoutes(anyString(), anyString(), anyString(), anyBoolean()))
        .thenReturn(callMock);
    
    googleMapsOutboundService.findRouteInfo(ORIGIN, DESTINATION);


    verify(googleMapsConfiguration).getApi_key();
    verify(googleMapsEndpointService).findRoutes(anyString(), anyString(), anyString(), anyBoolean());
  }

  @Test(expected = RuntimeException.class)
  public void testFindRouteInfo_NonOKStatus() throws IOException {
    when(googleMapsConfiguration.getBase_url()).thenReturn(BASE_URL);
    when(googleMapsConfiguration.getApi_key()).thenReturn(API_KEY);
    
    Call<GoogleRouteResponse> callMock = mock(Call.class);
    when(callMock.execute()).thenReturn(Response.success(FAILED_GOOGLE_ROUTE_RESPONSE));
    
    when(googleMapsEndpointService.findRoutes(anyString(), anyString(), anyString(), anyBoolean()))
        .thenReturn(callMock);
    
    googleMapsOutboundService.findRouteInfo(ORIGIN, DESTINATION);

    verify(googleMapsConfiguration).getApi_key();
    verify(googleMapsEndpointService).findRoutes(anyString(), anyString(), anyString(), anyBoolean());
  }
}
