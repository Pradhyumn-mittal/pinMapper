package com.java.pinMapper.controller.main.java;

import com.java.pinMapper.entity.constant.ApiPath;
import com.java.pinMapper.entity.pojo.RouteResponse;
import com.java.pinMapper.service.api.PinMapperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(ApiPath.SAVED_PINCODE)
@Api(value = "Saved-pincodes Controller")
public class SavedPincodeController {

    @Autowired
    private PinMapperService pinMapperService;
    @ApiOperation(value = "Post.pincodes",notes = "Save Pincode")
    @PostMapping()
    public ResponseEntity<RouteResponse> findRouteFromPincode(
        @RequestParam Integer origin,
        @RequestParam Integer destination
    ) {
      return new ResponseEntity<>(pinMapperService.findRouteByPincode(origin,destination), HttpStatus.OK);
    }
    @ApiOperation(value = "Get.route",notes = "input String Address")
    @GetMapping(path = ApiPath.ROUTE+ApiPath.ADDRESS)
    public ResponseEntity<RouteResponse> findRouteFromAddress(
        @RequestParam String origin,
        @RequestParam String destination
    ) {
      return new ResponseEntity<>(pinMapperService.findRouteByAddress(origin,destination), HttpStatus.OK);
    }
  }
