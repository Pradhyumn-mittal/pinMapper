package com.java.pinMapper.ServiceTest;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.MockitoAnnotations.initMocks;
import com.java.pinMapper.entity.constant.CacheKey;
import com.java.pinMapper.entity.constant.unit.test.PinMapperTestVariable;
import com.java.pinMapper.entity.pojo.RouteResponse;
import com.java.pinMapper.outbound.api.GoogleMapsOutboundService;
import com.java.pinMapper.repository.RouteInfoRepository;
import com.java.pinMapper.service.api.CacheService;
import com.java.pinMapper.service.api.KafkaService;
import com.java.pinMapper.service.impl.PinMapperServiceImpl;
import java.io.IOException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

public class PinMapperServiceImplTest extends PinMapperTestVariable {
  @InjectMocks
  private PinMapperServiceImpl pinMapperService;
  @Mock
  private RouteInfoRepository routeInfoRepository;
  @Mock
  private CacheService cacheService;
  @Mock
  private GoogleMapsOutboundService googleMapsOutboundService;
  @Mock
  private KafkaService kafkaService;

  @Before
  public void setup()
  {
    initMocks(this);
    ReflectionTestUtils.setField(pinMapperService,"cacheExpiry",10000L);
  }
  @After
  public void teardown()
  {
    Mockito.verifyNoMoreInteractions(routeInfoRepository,cacheService,googleMapsOutboundService,kafkaService);
  }
  @Test
  public void findRouteByPincodeCacheTest() throws IOException {
    String cacheKey= CacheKey.ROUTE_INFO+ORIGIN_STRING+"-"+DESTINATION_STRING;
    Mockito.when(cacheService.findCacheByKey(cacheKey, RouteResponse.class)).thenReturn(ROUTE_RESPONSE);
    RouteResponse result= pinMapperService.findRouteByPincode(ORIGIN,DESTINATION);
    Assert.assertEquals(ROUTE_RESPONSE,result);
    Mockito.verify(cacheService).findCacheByKey(cacheKey,RouteResponse.class);
  }
  @Test
  public void findRouteByPincodeTest() throws IOException {
    String cacheKey= CacheKey.ROUTE_INFO+ORIGIN_STRING+"-"+DESTINATION_STRING;
    Mockito.when(cacheService.findCacheByKey(cacheKey, RouteResponse.class)).thenReturn(null);
    Mockito.when(googleMapsOutboundService.findRouteInfo(ORIGIN_STRING,DESTINATION_STRING)).thenReturn(GOOGLE_ROUTE_RESPONSE);
    Mockito.when(cacheService.createCache(cacheKey,ROUTE_RESPONSE,10000L)).thenReturn(true);
    Mockito.when(routeInfoRepository.findRouteInfoByOriginPincodeAndDestinationPincode(ORIGIN,DESTINATION)).thenReturn(ROUTE_INFO);

    RouteResponse result= pinMapperService.findRouteByPincode(ORIGIN,DESTINATION);
    Assert.assertEquals(ROUTE_RESPONSE,result);
    Mockito.verify(cacheService).findCacheByKey(cacheKey,RouteResponse.class);
    Mockito.verify(googleMapsOutboundService).findRouteInfo(ORIGIN_STRING,DESTINATION_STRING);
    Mockito.verify(cacheService).createCache(cacheKey,ROUTE_RESPONSE,10000L);
    Mockito.verify(routeInfoRepository).findRouteInfoByOriginPincodeAndDestinationPincode(ORIGIN,DESTINATION);

  }
  @Test
  public void findRouteByPincodeNotFoundDBTest() throws IOException {
    String cacheKey= CacheKey.ROUTE_INFO+ORIGIN_STRING+"-"+DESTINATION_STRING;
    Mockito.when(cacheService.findCacheByKey(cacheKey, RouteResponse.class)).thenReturn(null);
    Mockito.when(googleMapsOutboundService.findRouteInfo(ORIGIN_STRING,DESTINATION_STRING)).thenReturn(GOOGLE_ROUTE_RESPONSE);
    Mockito.when(cacheService.createCache(cacheKey,ROUTE_RESPONSE,10000L)).thenReturn(true);
    Mockito.when(routeInfoRepository.findRouteInfoByOriginPincodeAndDestinationPincode(ORIGIN,DESTINATION)).thenReturn(null);
    Mockito.when(routeInfoRepository.save(any())).thenReturn(ROUTE_INFO);
    RouteResponse result= pinMapperService.findRouteByPincode(ORIGIN,DESTINATION);
    Assert.assertEquals(ROUTE_RESPONSE,result);
    Mockito.verify(cacheService).findCacheByKey(cacheKey,RouteResponse.class);
    Mockito.verify(googleMapsOutboundService).findRouteInfo(ORIGIN_STRING,DESTINATION_STRING);
    Mockito.verify(cacheService).createCache(cacheKey,ROUTE_RESPONSE,10000L);
    Mockito.verify(routeInfoRepository).findRouteInfoByOriginPincodeAndDestinationPincode(ORIGIN,DESTINATION);
    Mockito.verify(routeInfoRepository).save(any());
    Mockito.verify(kafkaService).sendKafkaMessage(ROUTE_RESPONSE);
  }

}
