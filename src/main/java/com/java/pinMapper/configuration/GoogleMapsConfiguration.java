package com.java.pinMapper.configuration;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "pin.mapper.google.maps")
public class GoogleMapsConfiguration {
  private String base_url;
  private String api_key;

  public String getBase_url() {
    return base_url;
  }

  public void setBase_url(String base_url) {
    this.base_url = base_url;
  }

  public String getApi_key() {
    return api_key;
  }

  public void setApi_key(String api_key) {
    this.api_key = api_key;
  }

  @Override
  public String toString() {
    return "GoogleMapsConfiguration{" +
        "base_url='" + base_url + '\'' +
        ", api_key='" + api_key + '\'' +
        '}';
  }
}
