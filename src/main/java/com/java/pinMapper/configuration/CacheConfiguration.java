package com.java.pinMapper.configuration;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.ClientOptions.DisconnectedBehavior;
import io.lettuce.core.resource.ClientResources;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
public class CacheConfiguration {

  @Autowired
  private RedisConfiguration redisConfiguration;
  @Bean
  public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
    RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager
        .RedisCacheManagerBuilder
        .fromConnectionFactory(redisConnectionFactory);
    return builder.build();
  }

  private JedisPoolConfig jedisPoolConfig() {
    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    jedisPoolConfig.setMaxTotal(redisConfiguration.getMaxTotal());
    jedisPoolConfig.setMinIdle(redisConfiguration.getMinIdle());
    jedisPoolConfig.setMaxIdle(redisConfiguration.getMaxIdle());
    return jedisPoolConfig;
  }

  @Primary
  @Bean(name = "pinMapperJedisConnectionFactory")
  public JedisConnectionFactory JedisConnectionFactory() {
    RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(
        redisConfiguration.getHost(),
        redisConfiguration.getPort()
    );

    configuration.setPassword(RedisPassword.of(redisConfiguration.getPassword()));
    JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder()
        .usePooling()
        .poolConfig(jedisPoolConfig())
        .build();

    return new JedisConnectionFactory(configuration,
        jedisClientConfiguration);
  }


  @Bean
  StringRedisSerializer stringRedisSerializer() {
    return new StringRedisSerializer();
  }

  @Bean
  Jackson2JsonRedisSerializer jackson2JsonRedisSerializer() {
    return new Jackson2JsonRedisSerializer(Object.class);
  }

  @Bean(name = "redisTemplate")
  public RedisTemplate redisTemplate(
      @Qualifier(value = "pinMapperJedisConnectionFactory") JedisConnectionFactory jedisConnectionFactory,
      StringRedisSerializer stringRedisSerializer,
      Jackson2JsonRedisSerializer jackson2JsonRedisSerializer
  ) {
    RedisTemplate redisTemplate = new RedisTemplate();

    redisTemplate.setConnectionFactory(jedisConnectionFactory);
    redisTemplate.setKeySerializer(stringRedisSerializer);
    redisTemplate.setHashKeySerializer(stringRedisSerializer);
    redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
    redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

    return redisTemplate;
  }

  @Bean
  public ClientOptions clientOptions() {
    return ClientOptions.builder()
        .disconnectedBehavior(DisconnectedBehavior.REJECT_COMMANDS)
        .autoReconnect(true)
        .build();
  }

  @Bean
  LettucePoolingClientConfiguration lettucePoolConfig(ClientOptions options, ClientResources dcr) {

    GenericObjectPoolConfig<LettuceClientConfiguration> poolConfig = new GenericObjectPoolConfig<>();
    poolConfig.setMaxIdle(redisConfiguration.getMaxIdle());
    poolConfig.setMaxTotal(redisConfiguration.getMaxTotal());
    poolConfig.setMinIdle(redisConfiguration.getMinIdle());

    return LettucePoolingClientConfiguration.builder()
        .poolConfig(poolConfig)
        .clientOptions(options)
        .clientResources(dcr)
        .build();
  }

  @Bean
  public RedisStandaloneConfiguration redisStandaloneConfiguration() {
    RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
    redisStandaloneConfiguration.setHostName(redisConfiguration.getHost());
    redisStandaloneConfiguration.setPort(redisConfiguration.getPort());
    redisStandaloneConfiguration.setPassword(redisConfiguration.getPassword());
    return redisStandaloneConfiguration;
  }
}
