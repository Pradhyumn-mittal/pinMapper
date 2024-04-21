package com.java.pinMapper.entity.dao;

import com.java.pinMapper.entity.constant.CollectionName;
import com.java.pinMapper.entity.constant.fields.RouteInfoFields;
import com.java.pinMapper.entity.pojo.DirectionSteps;
import com.java.pinMapper.entity.pojo.outbound.Distance;
import com.java.pinMapper.entity.pojo.outbound.Duration;
import com.java.pinMapper.entity.pojo.outbound.Location;
import com.java.pinMapper.entity.pojo.outbound.OverviewPolyline;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = CollectionName.ROUTE_INFO)
public class RouteInfo extends BaseMongo {

  @Field(value = RouteInfoFields.ORIGIN_PINCODE)
  private Integer originPincode;
  @Field(value = RouteInfoFields.DESTINATION_PINCODE)
  private Integer destinationPincode;
  @Field(value = RouteInfoFields.DISTANCE)
  private String distance;
  @Field(value = RouteInfoFields.DURATION)
  private String duration;
  @Field(value = RouteInfoFields.STEPS)
  private List<DirectionSteps> steps;
  @Field(value=RouteInfoFields.ORIGIN_LOCATION)
  private Location originLocation;
  @Field(value=RouteInfoFields.DESTINATION_LOCATION)
  private Location destinationLocation;
  @Field(value = RouteInfoFields.POLYGON_INFO)
  private OverviewPolyline polygonInfo;


}
