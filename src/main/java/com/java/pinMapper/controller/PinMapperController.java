package com.java.pinMapper.controller;

import com.java.pinMapper.entity.constant.ApiPath;

import com.java.pinMapper.entity.constant.enums.ResponseCode;

import com.java.pinMapper.entity.pojo.RouteResponse;
import com.java.pinMapper.entity.pojo.response.BaseResponse;
import com.java.pinMapper.service.api.PinMapperService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(ApiPath.PIN_MAPPER)
public class PinMapperController {

  @Autowired
  private PinMapperService pinMapperService;
  @GetMapping(path = ApiPath.ROUTE)
  public BaseResponse<RouteResponse> findRouteFromPincode(
      @RequestParam Integer origin,
      @RequestParam Integer destination
  ) throws IOException {
    RouteResponse routeResponse=pinMapperService.findRouteByPincode(origin,destination);
    return BaseResponse.constructResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(),null,
        routeResponse);
  }

}