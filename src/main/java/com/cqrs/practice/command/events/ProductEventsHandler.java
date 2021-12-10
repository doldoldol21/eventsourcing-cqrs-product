package com.cqrs.practice.command.events;

import com.cqrs.practice.command.data.Product;
import com.cqrs.practice.command.data.ProductRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product")
public class ProductEventsHandler {

  @Autowired
  private ProductRepository productRepository;

  @EventHandler
  public void on(ProductCreatedEvent event) throws Exception {
    Product product = new Product();
    BeanUtils.copyProperties(event, product);
    if (event.getName().equals("apple")) {
      throw new Exception("Exception Occurred");
    }
    productRepository.save(product);
  }

  @ExceptionHandler
  public void handle(Exception exception) throws Exception {
    throw exception;
  }
}
