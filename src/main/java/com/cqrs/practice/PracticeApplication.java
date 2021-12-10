package com.cqrs.practice;

import com.cqrs.practice.command.exception.ProductServiceEventsErrorHandler;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PracticeApplication {

  public static void main(String[] args) {
    SpringApplication.run(PracticeApplication.class, args);
  }

  @Autowired
  public void configure(EventProcessingConfigurer configurer) {
    configurer.registerListenerInvocationErrorHandler(
      "product",
      configuration -> new ProductServiceEventsErrorHandler()
    );
  }
}
