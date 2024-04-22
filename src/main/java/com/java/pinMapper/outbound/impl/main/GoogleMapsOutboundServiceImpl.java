package com.java.pinMapper.outbound.impl.main;

import com.java.pinMapper.configuration.GoogleMapsConfiguration;
import com.java.pinMapper.configuration.GoogleMapsEndpointService;
import com.java.pinMapper.entity.dao.RouteInfo;
import com.java.pinMapper.entity.pojo.outbound.GoogleRouteResponse;
import com.java.pinMapper.outbound.api.GoogleMapsOutboundService;
import java.io.IOException;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public class GoogleMapsOutboundServiceImpl implements GoogleMapsOutboundService {
  public static final Logger LOGGER= LoggerFactory.getLogger(GoogleMapsOutboundServiceImpl.class);
  private final GoogleMapsConfiguration googleMapsConfiguration;
  private final GoogleMapsEndpointService googleMapsEndpointService;

  @Autowired
  public GoogleMapsOutboundServiceImpl(GoogleMapsConfiguration googleMapsConfiguration) {
    this.googleMapsConfiguration = googleMapsConfiguration;
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(googleMapsConfiguration.getBase_url())
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    googleMapsEndpointService = retrofit.create(GoogleMapsEndpointService.class);
  }

  @Override
  public GoogleRouteResponse findRouteInfo(String origin, String destination) throws IOException {
    Response<GoogleRouteResponse> response;
    try {
      response = googleMapsEndpointService.findRoutes(origin, destination,
          googleMapsConfiguration.getApi_key(), false).execute();
      System.out.println(response);
    }
    catch (IOException e)
    {
      LOGGER.error("findRouteInfo error {},origin:{} destination:{}",e.getMessage(),origin,destination);
      throw new RuntimeException(String.format("IOException for origin: %s destination: %s",origin,destination));
      }
      GoogleRouteResponse googleRouteResponse=response.body();
    if (!response.isSuccessful() || Objects.isNull(googleRouteResponse)) {
        LOGGER.error("findRouteInfo  Third party error origin:{}, destination:{} response:{}",origin,destination,googleRouteResponse);
    }
    else if(!"OK".equalsIgnoreCase(Objects.requireNonNull(googleRouteResponse.getStatus())))
    {
      LOGGER.error("findRouteInfo third party error origin:{}, destination:{} response:{}",origin,destination,googleRouteResponse);
      throw new RuntimeException(String.format("%s from googleMaps api",googleRouteResponse.getStatus()));

    }
    System.out.println(googleRouteResponse);
    return googleRouteResponse;
    }
}
