//package com.model;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.OneToOne;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name = "orders")
//public class Order {
//
//  @OneToMany(targetEntity=com.model.Product.class,
//          mappedBy = "order", fetch = FetchType.LAZY, orphanRemoval = false)
//  private Map<Integer, Integer> products;
//
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  @OneToOne(mappedBy = "order")
//  private Integer id;
//
//  @Column(name = "total_amount")
//  private float totalAmount;
//
//  @Column(name = "is_paid")
//  private boolean isPaid;
//
//  public Order() {
//    this.products = new HashMap<>();
//    this.totalAmount = 0;
//    this.isPaid = false;
//  }
//
//  public float getTotalAmount() {
//    return totalAmount;
//  }
//
//  public void setTotalAmount(float totalAmount) {
//    this.totalAmount = totalAmount;
//  }
//
//  public boolean isPaid() {
//    return isPaid;
//  }
//
//  public void setPaid(boolean paid) {
//    isPaid = paid;
//  }
//
//  public void addProduct(Product product) {
//    products.put(product.getId(), products.getOrDefault(product.getId(), 0) + 1);
//  }
//}
