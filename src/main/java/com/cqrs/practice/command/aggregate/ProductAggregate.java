package com.cqrs.practice.practice.command.aggregate;

import com.cqrs.practice.practice.command.commands.ProductCreateCommand;
import com.cqrs.practice.practice.command.events.ProductCreatedEvent;
import java.math.BigDecimal;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class ProductAggregate {

  @AggregateIdentifier
  private String productId;

  private String name;
  private BigDecimal price;
  private Integer quantity;

  @CommandHandler
  public ProductAggregate(ProductCreateCommand productCreateCommand) {
    // 커맨드 퍼블리쉬
    ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
    BeanUtils.copyProperties(productCreateCommand, productCreatedEvent);
    AggregateLifecycle.apply(productCreatedEvent);
  }

  public ProductAggregate() {}

  @EventSourcingHandler
  public void on(ProductCreatedEvent productCreatedEvent) {
    this.quantity = productCreatedEvent.getQuantity();
    this.productId = productCreatedEvent.getProductId();
    this.price = productCreatedEvent.getPrice();
    this.name = productCreatedEvent.getName();
  }
}
