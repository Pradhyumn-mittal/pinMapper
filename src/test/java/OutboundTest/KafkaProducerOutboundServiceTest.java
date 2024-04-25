package OutboundTest;


import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.java.pinMapper.entity.constant.unit.test.KafkaTestVariable;

import com.java.pinMapper.outbound.impl.KafkaProducerOutboundServiceImpl;
import io.reactivex.rxjava3.core.Completable;
import java.util.concurrent.CompletableFuture;

import org.apache.kafka.clients.producer.RecordMetadata;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;


public class KafkaProducerOutboundServiceTest extends KafkaTestVariable {

  @Mock
  private KafkaTemplate<String, String> kafkaTemplate;

  @InjectMocks
  private KafkaProducerOutboundServiceImpl kafkaProducerOutboundService;


  @Before
  public void setUp() {
    initMocks(this);
  }

  @After
  public void tearDown() {
    verifyNoMoreInteractions(kafkaTemplate);
  }

  @Test
  public void testSendMessageWithKey_Success() {
    CompletableFuture<SendResult<String, String>> future = new CompletableFuture<>();
    when(kafkaTemplate.send(anyString(), anyString(), anyString())).thenReturn(future);

    Completable result = kafkaProducerOutboundService.sendMessageWithKey(KAFKA_TOPIC, KEY, MESSAGE);

    SendResult<String, String> sendResult = mockSendResult();
    future.complete(sendResult);

    result.test().assertComplete();

    verify(kafkaTemplate).send(eq(KAFKA_TOPIC), eq(KEY), eq(MESSAGE));
  }


  private SendResult<String, String> mockSendResult() {
    RecordMetadata recordMetadata = new RecordMetadata(null, 0, 0, 0, 0L, 0, 0);
    return new SendResult<>(null, recordMetadata);
  }

  @Test
  public void testSendMessageWithKey_Failure() {

    CompletableFuture<SendResult<String, String>> future = new CompletableFuture<>();

    future.completeExceptionally(new RuntimeException(ERROR_MESSAGE));
    when(kafkaTemplate.send(anyString(), anyString(), anyString())).thenReturn(future);

    kafkaProducerOutboundService.sendMessageWithKey(KAFKA_TOPIC, KEY, MESSAGE).test()
        .assertComplete();

    verify(kafkaTemplate).send(eq(KAFKA_TOPIC), eq(KEY), eq(MESSAGE));

  }

}
