package com.java.pinMapper.service.api;

import com.java.pinMapper.entity.dao.RouteInfo;
import com.java.pinMapper.entity.pojo.RouteResponse;
import java.io.IOException;

public interface PinMapperService {
    RouteResponse findRouteByPincode(Integer origin,Integer destination) throws IOException;

}
