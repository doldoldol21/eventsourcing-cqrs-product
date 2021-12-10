package com.cqrs.practice.query.projection;

import com.cqrs.practice.command.data.Product;
import com.cqrs.practice.command.data.ProductRepository;
import com.cqrs.practice.command.model.ProductRestModel;
import com.cqrs.practice.query.queries.GetProductsQuery;
import java.util.List;
import java.util.stream.Collectors;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductProjection {

  @Autowired
  private ProductRepository productRepository;

  @QueryHandler
  public List<ProductRestModel> handle(GetProductsQuery getProductsQuery) {
    List<Product> products = productRepository.findAll();

    List<ProductRestModel> productRestModels = products
      .stream()
      .map(product ->
        ProductRestModel
          .builder()
          .name(product.getName())
          .price(product.getPrice())
          .quantity(product.getQuantity())
          .build()
      )
      .collect(Collectors.toList());
    return productRestModels;
  }
}
