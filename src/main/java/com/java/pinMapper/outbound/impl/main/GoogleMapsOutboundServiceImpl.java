package com.java.pinMapper.outbound.impl.main;

import com.java.pinMapper.entity.dao.RouteInfo;
import com.java.pinMapper.entity.pojo.outbound.GoogleRouteResponse;
import com.java.pinMapper.outbound.api.GoogleMapsOutboundService;
import org.springframework.stereotype.Service;

@Service
public class GoogleMapsOutboundServiceImpl implements GoogleMapsOutboundService {

  @Override
  public RouteInfo findRouteInfo(String origin, String destination) {

  }
}
