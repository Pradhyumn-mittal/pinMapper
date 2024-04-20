package com.java.pinMapper.outbound.impl.main;

import com.java.pinMapper.configuration.GoogleMapsConfiguration;
import com.java.pinMapper.configuration.GoogleMapsEndpointService;
import com.java.pinMapper.entity.dao.RouteInfo;
import com.java.pinMapper.entity.pojo.outbound.GoogleRouteResponse;
import com.java.pinMapper.outbound.api.GoogleMapsOutboundService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public class GoogleMapsOutboundServiceImpl implements GoogleMapsOutboundService {

  private final GoogleMapsConfiguration googleMapsConfiguration;
  private final GoogleMapsEndpointService googleMapsEndpointService;

  @Autowired
  public GoogleMapsOutboundServiceImpl(GoogleMapsConfiguration googleMapsConfiguration) {
    this.googleMapsConfiguration = googleMapsConfiguration;

    // Initialize Retrofit here
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(googleMapsConfiguration.getBase_url())
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    googleMapsEndpointService = retrofit.create(GoogleMapsEndpointService.class);
  }

  @Override
  public RouteInfo findRouteInfo(String origin, String destination) throws IOException {
    System.out.println("IN OUTBOUND");
    Response<GoogleRouteResponse> response = googleMapsEndpointService.findRoutes(origin, destination,
        googleMapsConfiguration.getApi_key(), true).execute();
    RouteInfo routeInfo;
    if (!response.isSuccessful()) {
      throw new IOException(response.errorBody() != null
          ? response.errorBody().string() : "Unknown error");
    } else {
      routeInfo = RouteInfo.builder()
          .distance(response.body().getRoutes().get(0).getLegs().get(0).getDistance().getText())
          .build();
    }
    return routeInfo;
  }
}
