package com.java.pinMapper.ServiceTest;

import static com.java.pinMapper.entity.constant.unit.test.PinMapperTestVariable.ROUTE_RESPONSE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.pinMapper.configuration.KafkaTopicProperties;
import com.java.pinMapper.entity.constant.unit.test.KafkaTestVariable;
import com.java.pinMapper.entity.pojo.PincodeData;
import com.java.pinMapper.outbound.api.KafkaProducerOutboundService;
import com.java.pinMapper.service.impl.KafkaServiceImpl;
import io.reactivex.rxjava3.core.Completable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class KafkaServiceImplTest extends KafkaTestVariable {
  @InjectMocks
  private KafkaServiceImpl kafkaService;
  @Mock
  private KafkaProducerOutboundService kafkaProducerOutboundService;
  @Mock
  private KafkaTopicProperties kafkaTopicProperties;
  @Mock
  private ObjectMapper objectMapper;

  @Before
  public void setup() {
    initMocks(this);
  }

  @After
  public void teardown() {
    verifyNoMoreInteractions(kafkaProducerOutboundService, kafkaTopicProperties);
  }

  @Test
  public void sendKafkaMessageTest() throws JsonProcessingException {
    when(objectMapper.writeValueAsString(any(PincodeData.class))).thenReturn("jsonString");

    when(kafkaProducerOutboundService.sendMessageWithKey(any(), anyString(), anyString()))
        .thenReturn(Completable.complete());

    when(kafkaTopicProperties.getPincodeData()).thenReturn("pincode-topic");

    kafkaService.sendKafkaMessage(ROUTE_RESPONSE);

    verify(kafkaTopicProperties).getPincodeData();
    verify(kafkaProducerOutboundService).sendMessageWithKey(any(), anyString(), anyString());
  }
}
