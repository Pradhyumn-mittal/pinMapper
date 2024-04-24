package com.java.pinMapper.outbound.impl;

import com.java.pinMapper.outbound.api.KafkaProducerOutboundService;
import io.reactivex.rxjava3.core.Completable;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;


@Service
public class KafkaProducerOutboundServiceImpl implements KafkaProducerOutboundService {
  public static final Logger LOGGER= LoggerFactory.getLogger(KafkaProducerOutboundService.class);

  @Autowired
  @Qualifier("kafkaTemplate")
  private KafkaTemplate<String,String> kafkaTemplate;
  @Override
  public Completable sendMessageWithKey(String kafkaTopic, String key, String message) {
    return Completable.create(completableEmitter -> {
      CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(kafkaTopic, key, message);

      future.whenComplete((sendResult, throwable) -> {
        if (throwable != null) {
          LOGGER.warn("Failed to publish kafka message. Topic: {}, Key: {}, Message: {}, Error: {}",
              kafkaTopic, key, message, throwable.getMessage(), throwable);
          completableEmitter.onComplete();
        } else {
          LOGGER.info("Successfully published kafka message. Topic: {}, Key: {}, Message: {}, Offset: {}, Partition: {}",
              sendResult.getProducerRecord().topic(), sendResult.getProducerRecord().key(), message, sendResult.getRecordMetadata().offset(),
              sendResult.getRecordMetadata().partition());
          completableEmitter.onComplete();
        }
      });
    });
  }



}
