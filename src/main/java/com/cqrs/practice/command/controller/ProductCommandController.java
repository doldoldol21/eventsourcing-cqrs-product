package com.cqrs.practice.command.controller;

import com.cqrs.practice.command.commands.ProductCreateCommand;
import com.cqrs.practice.command.model.ProductRestModel;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductCommandController {

  @Autowired
  private CommandGateway commandGateway;

  @PostMapping
  public String addProduct(@RequestBody ProductRestModel productRestModel) {
    log.debug("addProduct {}", productRestModel);
    ProductCreateCommand createProductCommand = ProductCreateCommand
      .builder()
      .productId(UUID.randomUUID().toString())
      .name(productRestModel.getName())
      .price(productRestModel.getPrice())
      .quantity(productRestModel.getQuantity())
      .build();

    String result = commandGateway.sendAndWait(createProductCommand);

    return result;
  }
}
