package com.java.pinMapper.entity.pojo;

import com.java.pinMapper.entity.pojo.outbound.Location;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PincodeData implements Serializable {

  private String originPincode;
  private Location originLocation;
  private String destinationPincode;
  private Location destinationLocation;
}
