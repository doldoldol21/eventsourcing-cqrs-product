package com.cqrs.practice.query.controller;

import com.cqrs.practice.command.model.ProductRestModel;
import com.cqrs.practice.query.queries.GetProductsQuery;
import java.util.List;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

  @Autowired
  private QueryGateway queryGateway;

  @GetMapping
  public List<ProductRestModel> getAllProducts() {
    GetProductsQuery getProductsQuery = new GetProductsQuery();

    List<ProductRestModel> productRestModels = queryGateway
      .query(
        getProductsQuery,
        ResponseTypes.multipleInstancesOf(ProductRestModel.class)
      )
      .join();

    return productRestModels;
  }
}
