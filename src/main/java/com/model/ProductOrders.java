package com.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
public class ProductOrders {
  @EmbeddedId
  @JsonIgnore
  private ProductOrdersPK key;

  @Column(nullable = false)
  private Integer quantity;

  public ProductOrders(){}

  public ProductOrders(Product product, Order order, Integer quantity){
    key = new ProductOrdersPK();
    key.setOrder(order);
    key.setProduct(product);
    this.quantity = quantity;
  }

  @Transient
  public Product getProduct() {
    return this.key.getProduct();
  }

  @Transient
  public float getTotalPrice() {
    return getProduct().getPrice() * getQuantity();
  }

  public ProductOrdersPK getKey() {
    return key;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ProductOrders that = (ProductOrders) o;
    return key.equals(that.key) && Objects.equals(quantity, that.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, quantity);
  }
}
