package com.java.pinMapper.outbound.api;

import com.java.pinMapper.entity.dao.RouteInfo;

public interface GoogleMapsOutboundService {
    RouteInfo findRouteInfo(String origin,String destination);
    
    
}
