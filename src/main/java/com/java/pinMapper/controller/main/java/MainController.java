package com.java.pinMapper.controller.main.java;

import com.java.pinMapper.entity.constant.ApiPath;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(ApiPath.PIN_MAPPER)
@Api(value = "PinMapper Main")
public class MainController {

  @ApiOperation(value = "Get.route")
  @GetMapping(path = ApiPath.ROUTE)
  public BaseResponse<RouteResponse> findRoute(
      @RequestParam Integer fromPincode,
      @RequestParam Integer toPincode
  ){
    return
  }


}
