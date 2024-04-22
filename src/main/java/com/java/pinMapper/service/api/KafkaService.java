package com.java.pinMapper.service.api;

import com.java.pinMapper.entity.dao.RouteInfo;
import com.java.pinMapper.entity.pojo.RouteResponse;

public interface KafkaService {
  void sendKafkaMessage(RouteResponse routeResponse);
}
