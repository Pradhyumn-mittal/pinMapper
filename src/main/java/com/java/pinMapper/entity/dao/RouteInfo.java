package com.java.pinMapper.entity.dao;

import com.java.pinMapper.entity.constant.CollectionName;
import com.java.pinMapper.entity.constant.fields.RouteInfoFields;
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

  @Field(value = RouteInfoFields.FROM_PINCODE)
  private String fromPinCode;
  @Field(value = RouteInfoFields.TO_PINCODE)
  private String toPinCode;
  @Field(value = RouteInfoFields.FROM_ADDRESS)
  private String fromAddress;
  @Field(value = RouteInfoFields.TO_ADDRESS)
  private String toAddress;
  @Field(value = RouteInfoFields.DISTANCE)
  private String distance;
  @Field(value = RouteInfoFields.DURATION)
  private String duration;

}
