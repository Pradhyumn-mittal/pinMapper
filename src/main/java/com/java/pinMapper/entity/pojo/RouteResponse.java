package com.java.pinMapper.entity.pojo;

import com.java.pinMapper.entity.pojo.outbound.Distance;
import com.java.pinMapper.entity.pojo.outbound.Duration;
import com.java.pinMapper.entity.pojo.outbound.Location;
import com.java.pinMapper.entity.pojo.outbound.OverviewPolyline;
import com.java.pinMapper.entity.pojo.outbound.Route;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class RouteResponse implements Serializable {
  private String originPincode;
  private String destinationPincode;
  private String duration;
  private String distance;
  private List<DirectionSteps> steps;
  private Location originLocation;
  private Location destinationLocation;
  private OverviewPolyline polygonInfo;
}



