package com.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.Valid;

@Entity
@Table(name = "orders")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="productOrders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dateCreated;

  @Column(name = "is_paid")
  private boolean isPaid;

//  @JsonManagedReference
//  @JsonIgnore
  @OneToMany(mappedBy = "key.order")
  @Valid
  private List<ProductOrders> productOrders = new ArrayList<>();

  public Order() {}

  @Transient
  public Double getTotalOrderPrice() {
    double sum = 0D;
    List<ProductOrders> orderProducts = getProductOrders();
    for (ProductOrders op : orderProducts) {
      sum += op.getTotalPrice();
    }
    return sum;
  }

  @Transient
  public int getNumberOfProducts() {
    return this.productOrders.size();
  }

  public LocalDate getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(LocalDate dateCreated) {
    this.dateCreated = dateCreated;
  }

  public boolean isPaid() {
    return isPaid;
  }

  public void setPaid(boolean paid) {
    isPaid = paid;
  }

  public List<ProductOrders> getProductOrders() {
    return productOrders;
  }

  public void setProductOrders(List<ProductOrders> productOrders) {
    this.productOrders = productOrders;
  }
}
