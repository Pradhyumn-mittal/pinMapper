package com.java.pinMapper.outbound.api;

import io.reactivex.rxjava3.core.Completable;

public interface KafkaProducerOutboundService {
  Completable sendMessageWithKey(String kafkaTopic, String key, String message);
}
