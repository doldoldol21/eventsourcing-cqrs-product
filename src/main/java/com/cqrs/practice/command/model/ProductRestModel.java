package com.cqrs.practice.practice.command.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRestModel {

  private String name;
  private BigDecimal price;
  private Integer quantity;
}
