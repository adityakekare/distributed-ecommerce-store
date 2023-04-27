package com.service;

import com.exception.ResourceNotFoundException;
import com.model.Order;
import com.model.Product;
import com.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService {

  @Autowired
  private OrderRepository orderRepository;

  public Iterable<Order> getAllOrders() {
    return this.orderRepository.findAll();
  }

  public Order readOrder(Integer orderId){
    if(this.orderRepository.findById(orderId).isPresent()){
      return this.orderRepository.findById(orderId).get();
    }
    else{
      throw new ResourceNotFoundException("Order not found");
    }
  }

  public Order create(Order order) {
    order.setDateCreated(LocalDate.now());
    return this.orderRepository.save(order);
  }

  public void update(Order order) {
    order.setDateCreated(LocalDate.now());
    this.orderRepository.save(order);
  }
}
