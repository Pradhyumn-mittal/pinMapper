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
  private List<Leg> legs;
  private OverviewPolyline overview_polyline;
}
