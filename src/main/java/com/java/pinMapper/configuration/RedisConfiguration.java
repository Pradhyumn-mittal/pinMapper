package com.java.pinMapper.configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource({
    "classpath:application.properties"
})
@ConfigurationProperties(prefix = "pin.mapper.redis")
public class RedisConfiguration {
  private String host;
  private Integer port;
  private String password;
  private Integer maxTotal;
  private Integer minIdle;
  private Integer maxIdle;

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;

  }

  public Integer getPort() {
    return port;
  }

  public void setPort(Integer port) {
    this.port = port;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getMaxTotal() {
    return maxTotal;
  }

  public void setMaxTotal(Integer maxTotal) {
    this.maxTotal = maxTotal;
  }

  public Integer getMinIdle() {
    return minIdle;
  }

  public void setMinIdle(Integer minIdle) {
    this.minIdle = minIdle;
  }

  public Integer getMaxIdle() {
    return maxIdle;
  }

  public void setMaxIdle(Integer maxIdle) {
    this.maxIdle = maxIdle;
  }

  @Override
  public String toString() {
    return "RedisConfiguration{" +
        "host='" + host + '\'' +
        ", port=" + port +
        ", password='" + password + '\'' +
        ", maxTotal=" + maxTotal +
        ", minIdle=" + minIdle +
        ", maxIdle=" + maxIdle +
        '}';
  }
}
