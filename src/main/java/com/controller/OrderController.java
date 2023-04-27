package com.controller;

import com.dto.OrderDto;
import com.model.Order;
import com.model.Product;
import com.model.ProductOrders;
import com.service.OrderService;
import com.service.ProductService;
import com.service.ProductsOrderService;
import com.util.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

  @Autowired
  private OrderService orderService;

  @Autowired
  private ProductsOrderService productsOrderService;

  @Autowired
  private ProductService productService;

  @GetMapping(value = { "", "/" })
  public ResponseEntity<Iterable<Order>> getOrders() {
    Iterable<Order> body = orderService.getAllOrders();
    return new ResponseEntity<>(body, HttpStatus.OK);
  }

  @GetMapping("/{orderId}")
  public ResponseEntity<Order> getOrder(@PathVariable("orderId") Integer orderId) {
      Order body = orderService.readOrder(orderId);
      return new ResponseEntity<>(body, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Order> create(@RequestBody UserOrder order) {
    List<ProductOrders> productOrders = new ArrayList<>();
    List<OrderDto> orderDtos = order.getProductOrders();

    // create order logic
    Order newOrder = new Order();
//    newOrder.setDateCreated(LocalDate.now());
    newOrder.setPaid(true);
    newOrder = this.orderService.create(newOrder);
    // populate order with products
    for(OrderDto dto: orderDtos){
      productOrders.add(productsOrderService.create(new ProductOrders(productService.readProduct(dto
              .getProduct().getId()), newOrder, dto.getQuantity())));
    }

    newOrder.setProductOrders(productOrders);
    this.orderService.update(newOrder);

//    String uri = ServletUriComponentsBuilder
//            .fromCurrentServletMapping()
//            .path("/orders/{id}")
//            .buildAndExpand(order.getId())
//            .toString();
//    HttpHeaders headers = new HttpHeaders();
//    headers.add("Location", uri);

    return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
  }

  public static class UserOrder {

    private List<OrderDto> productOrders;

    public List<OrderDto> getProductOrders() {
      System.out.println(productOrders);
      return productOrders;
    }

    public void setProductOrders(List<OrderDto> productOrders) {
      this.productOrders = productOrders;
    }
  }
}
