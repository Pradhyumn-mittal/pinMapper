package com.java.pinMapper.configuration;

import com.java.pinMapper.entity.pojo.exception.ErrorRes;
import com.java.pinMapper.entity.pojo.response.BaseResponse;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.converter.ResolvedSchema;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

  @Bean
  public OpenAPI usersMicroserviceOpenAPI() {

    Server localServer = new Server()
        .url("http://localhost:8081")
        .description("A Server URL");

    Contact contact = new Contact()
        .email("pradhyumn.work@gmail.com")
        .name("Pradhyumn Mittal");

    Info info = new Info()
        .contact(contact)
        .description("Simplifying pin-to-pin distance queries with Google Maps integration and efficient caching")
        .summary("Swagger for Pinmapper Backend API")
        .title("PinMapper")
        .version("V1.0.0")
        .license(new License().name("Apache 2.0").url("http://springdoc.org"));

    return new OpenAPI().info(info).
        addServersItem(localServer);
  }

  @Bean
  public OpenApiCustomizer openApiCustomizer() {
    ResolvedSchema errResSchema =
        ModelConverters.getInstance().resolveAsResolvedSchema(new AnnotatedType(BaseResponse.class));
    Content content =
        new Content().addMediaType("application/json", new MediaType().schema(errResSchema.schema));
    return openApi ->
        openApi
            .getPaths()
            .values()
            .forEach(
                pathItem ->
                    pathItem
                        .readOperations()
                        .forEach(
                            operation ->
                                operation
                                    .getResponses()
                                    .addApiResponse(
                                        "400",
                                        new ApiResponse()
                                            .description("Bad Request")
                                            .content(content))
                                    .addApiResponse(
                                        "500",
                                        new ApiResponse()
                                            .description("Internal Server Error")
                                            .content(content))));
  }
}

