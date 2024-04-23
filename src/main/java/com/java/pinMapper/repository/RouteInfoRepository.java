package com.java.pinMapper.repository;

import com.java.pinMapper.entity.dao.RouteInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RouteInfoRepository extends MongoRepository<RouteInfo,String>{
  RouteInfo findRouteInfoByOriginPincodeAndDestinationPincode(Integer originPincode,Integer destinationPincode);
}
