package com.java.pinMapper.entity.pojo.outbound;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class Route {
  private Bounds bounds;
  private String copyrights;
  private List<Leg> legs;
  private OverviewPolyline overviewPolyline;
  private String summary;
  private List<String> warnings;
  private List<Integer> waypointOrder;
}
