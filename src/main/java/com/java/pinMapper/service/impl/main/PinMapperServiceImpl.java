package com.java.pinMapper.service.impl.main;

import com.java.pinMapper.entity.constant.CacheKey;
import com.java.pinMapper.entity.dao.RouteInfo;
import com.java.pinMapper.entity.pojo.RouteResponse;
import com.java.pinMapper.outbound.api.GoogleMapsOutboundService;
import com.java.pinMapper.repository.main.java.RouteInfoRepository;
import com.java.pinMapper.service.api.CacheService;
import com.java.pinMapper.service.api.PinMapperService;
import java.io.IOException;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;

@Service
public class PinMapperServiceImpl implements PinMapperService {
  private static final Logger LOGGER = LoggerFactory.getLogger(PinMapperServiceImpl.class);
  @Autowired
  private RouteInfoRepository routeInfoRepository;
  @Autowired
  private CacheService cacheService;
  @Autowired
  private GoogleMapsOutboundService googleMapsOutboundService;

  @Override
  public RouteInfo findRouteByPincode(Integer origin, Integer destination) throws IOException {
    LOGGER.info("findRouteByPincode origin:{},destination:{}",origin,destination);
    String cacheKey= CacheKey.ROUTE_INFO+origin.toString()+"-"+destination.toString();

    RouteInfo routeInfo= cacheService.findCacheByKey(cacheKey,RouteInfo.class);
    if(Objects.isNull(routeInfo))
    {routeInfo=googleMapsOutboundService.findRouteInfo(String.valueOf(origin),String.valueOf(destination));
     cacheService.createCache(cacheKey,routeInfo,3600);
     //TODO: replace expiry seconds from application.properties
    }
    return routeInfo;
  }

  @Override
  public RouteInfo findRouteByAddress(String origin, String destination) {
    return null;
  }
}
