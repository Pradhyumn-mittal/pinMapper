package com.java.pinMapper.service.impl.main;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.pinMapper.service.api.CacheService;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceImpl implements CacheService {
  private static final Logger LOGGER = LoggerFactory.getLogger(CacheServiceImpl.class);

  @Autowired
  RedisTemplate redisTemplate;
  private static final ObjectMapper MAPPER =
      new ObjectMapper().configure(FAIL_ON_UNKNOWN_PROPERTIES, false);

  @Override
  public <T> T findCacheByKey(String key, Class<T> clazz) {
    T object = null;

    try {
      Object cacheValue = this.redisTemplate.opsForValue().get(key);

      if (cacheValue != null) {
        object = MAPPER.convertValue(cacheValue, clazz);
      }
    } catch (Exception e) {
      LOGGER.error("CacheServiceImpl-findCacheByKey error stackTrace = {}",
          e);
    }

    return object;
  }

  @Override
  public void createCache(String key, Object value, long expirySeconds) {
    LOGGER.info("createCache key: {}, value: {}", key, value);
    try {
      if (expirySeconds == 0) {
        this.redisTemplate.opsForValue().set(key, value);
      } else {
        this.redisTemplate.opsForValue().set(key, value, expirySeconds, TimeUnit.SECONDS);
      }
    } catch (Exception e) {
      LOGGER.error("CacheServiceImpl-createCache error stackTrace = {}",
          e);
    }
  }

}
