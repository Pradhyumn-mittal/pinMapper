package com.java.pinMapper.configuration;

import com.java.pinMapper.entity.pojo.outbound.GoogleRouteResponse;
import retrofit2.http.Query;
import retrofit2.http.Url;
import retrofit2.Call;
import retrofit2.http.POST;


public interface GoogleMapsEndpointService {

  @POST("/maps/api/directions/json")
  Call<GoogleRouteResponse> findRoutes(@Query("origin") String origin,
      @Query("destination") String destination, @Query("key") String key,
      @Query("alternatives") Boolean alternatives);

}
