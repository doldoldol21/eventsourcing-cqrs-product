package com.cqrs.practice.practice.command.commands;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class ProductCreateCommand {

  @TargetAggregateIdentifier
  private String productId;

  private String name;
  private BigDecimal price;
  private Integer quantity;
}
