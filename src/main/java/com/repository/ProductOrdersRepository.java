package com.repository;

import com.model.Order;
import com.model.ProductOrders;
import com.model.ProductOrdersPK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrdersRepository extends JpaRepository<ProductOrders, ProductOrdersPK> {
}
