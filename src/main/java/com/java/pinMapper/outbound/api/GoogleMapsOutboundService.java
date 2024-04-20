package com.java.pinMapper.outbound.api;

import com.java.pinMapper.entity.dao.RouteInfo;
import java.io.IOException;

public interface GoogleMapsOutboundService {
    RouteInfo findRouteInfo(String origin,String destination) throws IOException;
    
    
}
