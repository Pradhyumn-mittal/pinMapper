package com.java.pinMapper.service.impl.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.pinMapper.configuration.KafkaTopicProperties;
import com.java.pinMapper.entity.dao.RouteInfo;
import com.java.pinMapper.entity.pojo.PincodeData;
import com.java.pinMapper.entity.pojo.RouteResponse;
import com.java.pinMapper.outbound.api.KafkaProducerOutboundService;
import com.java.pinMapper.service.api.KafkaService;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaServiceImpl implements KafkaService{
  public static final Logger LOGGER= LoggerFactory.getLogger(KafkaService.class);
  @Autowired
  private KafkaProducerOutboundService kafkaProducerOutboundService;
  @Autowired
  private KafkaTopicProperties kafkaTopicProperties;
  @Override
  public void sendKafkaMessage(RouteResponse routeResponse) {
     ObjectMapper mapper = new ObjectMapper();
    PincodeData pincodeData= PincodeData.builder()
        .destinationPincode(routeResponse.getDestinationPincode())
        .originPincode(routeResponse.getOriginPincode())
        .destinationLocation(routeResponse.getDestinationLocation())
        .originLocation(routeResponse.getOriginLocation())
        .build();
    String message;
    try{
      message=mapper.writeValueAsString(pincodeData);
    }
    catch (JsonProcessingException e)
    {
      LOGGER.error("sendKafkaMessage unable to parse routeResponse");
      throw new RuntimeException("unable to parse routeResponse");
    }

    String topic=kafkaTopicProperties.getPincodeData();
    String kafkaKey=routeResponse.getOriginPincode()+"-"+ routeResponse.getDestinationPincode();
    this.kafkaProducerOutboundService.sendMessageWithKey(topic,kafkaKey,message).subscribe();
  }

}
