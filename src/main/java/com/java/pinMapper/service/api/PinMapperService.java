package com.java.pinMapper.service.api;

import com.java.pinMapper.entity.pojo.RouteResponse;

public interface PinMapperService {
    RouteResponse findRouteByPincode(Integer origin,Integer destination);
    RouteResponse findRouteByAddress(String origin,String destination);
}
