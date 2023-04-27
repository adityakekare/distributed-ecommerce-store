package com.service;

import com.model.ProductOrders;
import com.repository.ProductOrdersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductsOrderService {

  @Autowired
  ProductOrdersRepository productOrdersRepository;

  public ProductOrders create(ProductOrders productOrder) {
    return this.productOrdersRepository.save(productOrder);
  }
}
