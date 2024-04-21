package com.java.pinMapper.service.impl.main;

import com.java.pinMapper.entity.constant.CacheKey;
import com.java.pinMapper.entity.dao.RouteInfo;
import com.java.pinMapper.entity.pojo.DirectionSteps;
import com.java.pinMapper.entity.pojo.RouteResponse;
import com.java.pinMapper.entity.pojo.outbound.GoogleRouteResponse;
import com.java.pinMapper.entity.pojo.outbound.Leg;
import com.java.pinMapper.entity.pojo.outbound.OverviewPolyline;
import com.java.pinMapper.outbound.api.GoogleMapsOutboundService;
import com.java.pinMapper.repository.api.RouteInfoRepository;
import com.java.pinMapper.service.api.CacheService;
import com.java.pinMapper.service.api.PinMapperService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PinMapperServiceImpl implements PinMapperService {
  private static final Logger LOGGER = LoggerFactory.getLogger(PinMapperServiceImpl.class);
  private ExecutorService executorService = Executors.newCachedThreadPool();
  @Autowired
  private RouteInfoRepository routeInfoRepository;
  @Autowired
  private CacheService cacheService;
  @Autowired
  private GoogleMapsOutboundService googleMapsOutboundService;

  @Override
  public RouteResponse findRouteByPincode(Integer origin, Integer destination) throws IOException {
    LOGGER.info("findRouteByPincode origin:{},destination:{}",origin,destination);
    String cacheKey= CacheKey.ROUTE_INFO+origin.toString()+"-"+destination.toString();

    RouteResponse routeResponse= cacheService.findCacheByKey(cacheKey,RouteResponse.class);
    if(Objects.nonNull(routeResponse))
    {
      return routeResponse;
    }
   else{
     GoogleRouteResponse googleRouteResponse=googleMapsOutboundService.findRouteInfo(String.valueOf(origin),String.valueOf(destination));
     routeResponse = convertToRouteResponse(origin,destination,googleRouteResponse);
     cacheService.createCache(cacheKey,routeResponse,3600);
      saveData(origin, destination, routeResponse);
    }
    return routeResponse;
  }
  private void saveData(Integer origin, Integer destination, RouteResponse response) {
    CompletableFuture.runAsync(() -> {
      RouteInfo existingRoute = routeInfoRepository.findRouteInfoByOriginPincodeAndDestinationPincode(origin,
          destination);
      if (existingRoute == null) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        LOGGER.info("Route info not found : {} ", response);
        RouteInfo routeInfo = RouteInfo.builder().steps(response.getSteps()).duration(response.getDuration())
            .distance(response.getDistance()).destinationPincode(destination).polygonInfo(response.getPolygonInfo())
            .originLocation(response.getOriginLocation()).destinationLocation(response.getDestinationLocation())
            .originPincode(origin).build();
         routeInfoRepository.save(routeInfo);
        // kafkaService.sendPincode(response);
      }
    }, executorService);
  }

  private RouteResponse convertToRouteResponse(Integer origin,Integer destination,GoogleRouteResponse googleRouteResponse)
  {
    List<DirectionSteps> directionStepsList=new ArrayList<>();
    Leg leg=googleRouteResponse.getRoutes().get(0).getLegs().get(0);
    OverviewPolyline overviewPolyline=googleRouteResponse.getRoutes().get(0).getOverview_polyline();
    googleRouteResponse.getRoutes().get(0).getLegs().get(0).getSteps().stream()
        .forEach(step->{
          String direction= step.getHtml_instructions();
          DirectionSteps directionSteps=DirectionSteps.builder()
              .duration(step.getDuration().getText())
              .directionInfo(direction)
              .distance(step.getDistance().getText()).build();
          directionStepsList.add(directionSteps);
        });
    return RouteResponse.builder()
        .distance(leg.getDistance().getText())
        .duration(leg.getDuration().getText())
        .destinationLocation(leg.getEnd_location())
        .destinationPincode(String.valueOf(destination))
        .originLocation(leg.getStart_location())
        .originPincode(String.valueOf(origin))
        .polygonInfo(overviewPolyline)
        .steps(directionStepsList)
        .build();
  }


}
