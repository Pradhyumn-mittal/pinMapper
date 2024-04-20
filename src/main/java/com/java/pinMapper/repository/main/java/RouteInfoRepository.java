package com.java.pinMapper.repository.main.java;

import com.java.pinMapper.entity.dao.RouteInfo;
import com.java.pinMapper.entity.pojo.outbound.Route;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteInfoRepository extends MongoRepository<RouteInfo,String > {

  RouteInfo findRouteInfoByFromPincodeAndToPincode(Integer fromPincode,Integer toPincode);

}
