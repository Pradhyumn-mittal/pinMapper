package com.java.pinMapper.outbound.api;

import com.java.pinMapper.entity.pojo.outbound.GoogleRouteResponse;
import java.io.IOException;

public interface GoogleMapsOutboundService {
    GoogleRouteResponse findRouteInfo(String origin,String destination) throws IOException;
    
    
}
