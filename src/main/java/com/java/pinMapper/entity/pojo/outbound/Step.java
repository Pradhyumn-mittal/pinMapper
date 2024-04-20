package com.java.pinMapper.entity.pojo.outbound;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Step {
  private Distance distance;
  private Duration duration;
  private Location endLocation;
  private String htmlInstructions;
  private String maneuver;
  private Polyline polyline;
  private Location startLocation;
  private String travelMode;
}
