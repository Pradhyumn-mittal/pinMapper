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
  private Location end_location;
  private Location start_location;
  private List<Step> steps;

}
