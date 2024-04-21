package com.java.pinMapper.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DirectionSteps {
  private String distance;
  private String duration;
  private String directionInfo;
}
