package com.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.model.Product;
import com.service.ProductService;

import java.time.LocalDate;

public class OrderDto {

  private Product product;
  private int quantity;

  public OrderDto(){}

  public OrderDto(Product product, int quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
