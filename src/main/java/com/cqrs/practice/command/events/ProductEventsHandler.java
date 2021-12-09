package com.cqrs.practice.practice.command.events;

import com.cqrs.practice.practice.command.data.Product;
import com.cqrs.practice.practice.command.data.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductEventsHandler {

  @Autowired
  private ProductRepository productRepository;

  @EventHandler
  public void on(ProductCreatedEvent event) {
    Product product = new Product();
    BeanUtils.copyProperties(event, product);
    productRepository.save(product);
  }
}
