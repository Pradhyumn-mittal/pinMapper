package com.java.pinMapper.service.api;

import java.util.List;
import java.util.Set;

public interface CacheService {
  <T> T findCacheByKey(String key, Class<T> clazz);

  Boolean createCache(String key, Object value, long expirySeconds);

  Boolean deleteCache(String key);

  Set getCacheKeys(String pattern);
}
