package com.java.pinMapper.service.impl.main;

import com.java.pinMapper.entity.pojo.RouteResponse;
import com.java.pinMapper.service.api.PinMapperService;
import org.springframework.stereotype.Service;

@Service
public class PinMapperServiceImpl implements PinMapperService {


  @Override
  public RouteResponse findRouteByPincode(Integer origin, Integer destination) {
return null;
  }

  @Override
  public RouteResponse findRouteByAddress(String origin, String destination) {
    return null;
  }
}
