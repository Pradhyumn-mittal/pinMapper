package com.java.pinMapper.entity.pojo.outbound;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Leg {
  private Distance distance;
  private Duration duration;
  private String endAddress;
  private Location endLocation;
  private String startAddress;
  private Location startLocation;
  private List<Step> steps;
  private List<Integer> trafficSpeedEntry;
  private List<Integer> viaWaypoint;

}
