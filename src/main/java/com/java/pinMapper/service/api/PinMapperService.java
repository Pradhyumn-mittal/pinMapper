package com.java.pinMapper.service.api;

import com.java.pinMapper.entity.pojo.RouteResponse;

public interface PinMapperService {
    RouteResponse findRoute(Integer origin,Integer destination);
}
