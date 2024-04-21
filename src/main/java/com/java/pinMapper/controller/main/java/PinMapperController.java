package com.java.pinMapper.controller.main.java;

import com.java.pinMapper.entity.constant.ApiPath;

import com.java.pinMapper.entity.dao.RouteInfo;
import com.java.pinMapper.entity.pojo.RouteResponse;
import com.java.pinMapper.service.api.PinMapperService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(ApiPath.PIN_MAPPER)
//@Api(value = "PinMapper Main")
public class PinMapperController {

  @Autowired
  private PinMapperService pinMapperService;
//  @ApiOperation(value = "Get.route",notes = "input Integer Pincode")
  @GetMapping(path = ApiPath.ROUTE+ApiPath.PINCODE)
  public RouteResponse findRouteFromPincode(
      @RequestParam Integer origin,
      @RequestParam Integer destination
  ) throws IOException {
    validatePincode(origin,"origin");
    validatePincode(destination,"destination");
    return pinMapperService.findRouteByPincode(origin,destination);
  }

  private void validatePincode(Integer number, String paramName) {
    if (number == null || number < 100000 || number > 999999) {
      throw new IllegalArgumentException(paramName + " must be a valid pincode");
    }
  }
}