package com.java.pinMapper.configuration;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
@ConditionalOnClass(Retrofit.class)
public class RetrofitConfiguration {
  @Bean
  public Retrofit retrofitGoogleMapsApi(GoogleMapsConfiguration googleMapsConfiguration) {
    return new Retrofit.Builder()
        .baseUrl(googleMapsConfiguration.getBase_url())
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  @Bean
  public GoogleMapsEndpointService googleMapsEndpointService(Retrofit retrofitGoogleMapsApi) {
    return retrofitGoogleMapsApi.create(GoogleMapsEndpointService.class);
  }
}
