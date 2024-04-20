package com.java.pinMapper.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan(basePackages = "com.java.pinMapper.repository.main.java")
@ComponentScan(basePackages = "com.java.pinMapper.service")
@EnableMongoRepositories(value = "com.java.pinMapper.repository.main.java")
public class MongoConfiguration {
  private MongoProperties mongoProperties;
  @Autowired
  public MongoConfiguration(
      MongoProperties mongoProperties
  ) {
    this.mongoProperties = mongoProperties;
  }

  @Bean
  public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDbFactory, MongoConverter converter) {
    return new MongoTemplate(mongoDbFactory, converter);
  }
  @Bean
  protected String getDatabaseName() {
    return this.mongoProperties.getDatabase();
  }
}
