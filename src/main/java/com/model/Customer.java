//package com.model;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.OneToOne;
//import jakarta.persistence.Table;
//import jakarta.validation.constraints.NotBlank;
//
//@Entity
//@Table(name = "customers")
//public class Customer {
//
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  private Integer id;
//
//  private @NotBlank String username;
//
//  private @NotBlank String password;
//
////  @OneToOne(cascade = CascadeType.ALL)
////  @JoinColumn(name = "id", referencedColumnName = "id")
//  private Order order;
//
//  public Customer(@NotBlank String username, @NotBlank String password) {
//    this.username = username;
//    this.password = password;
//  }
//
//  public String getUsername() {
//    return username;
//  }
//
//  public void setUsername(String username) {
//    this.username = username;
//  }
//}
