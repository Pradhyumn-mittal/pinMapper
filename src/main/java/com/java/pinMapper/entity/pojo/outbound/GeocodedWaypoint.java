package com.java.pinMapper.entity.pojo.outbound;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class GeocodedWaypoint {
  private String geocoderStatus;
  private String placeId;
  private List<String> types;
}
