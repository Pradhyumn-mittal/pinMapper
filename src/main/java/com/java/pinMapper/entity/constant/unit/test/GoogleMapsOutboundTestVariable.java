package com.java.pinMapper.entity.constant.unit.test;

import com.java.pinMapper.entity.pojo.outbound.GoogleRouteResponse;

public class GoogleMapsOutboundTestVariable {
  public static final String DESTINATION="110024";
  public static final String ORIGIN="110022";
  public static final String BASE_URL="https://maps.googleapis.com/";
  public static final String API_KEY="2323";

  public static final GoogleRouteResponse GOOGLE_ROUTE_RESPONSE=GoogleRouteResponse.builder()
      .status("OK")
      .build();

  public static final GoogleRouteResponse FAILED_GOOGLE_ROUTE_RESPONSE=GoogleRouteResponse.builder()
      .status("ZERO_RESULTS")
      .build();

}
