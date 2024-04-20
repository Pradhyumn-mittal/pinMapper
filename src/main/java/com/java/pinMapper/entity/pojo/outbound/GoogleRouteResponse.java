package com.java.pinMapper.entity.pojo.outbound;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoogleRouteResponse {
  private List<GeocodedWaypoint> geocodedWaypoints;
  private List<Route> routes;
  private String status;
}
