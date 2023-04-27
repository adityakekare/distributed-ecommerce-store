package com.controller;

import com.model.Product;
import com.service.ProductService;
import com.util.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/product")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping(value = { "", "/" })
  public ResponseEntity<List<Product>> getCategories() {
    List<Product> body = productService.listProducts();
    return new ResponseEntity<>(body, HttpStatus.OK);
  }

  @PostMapping("/create")
  public ResponseEntity<ApiResponse> createProduct(@Valid @RequestBody Product product) {
    if (Objects.nonNull(productService.readProduct(product.getName()))) {
      return new ResponseEntity<>(new
              ApiResponse(false, "product already exists"), HttpStatus.CONFLICT);
    }
    productService.createProduct(product);
    return new ResponseEntity<>(new
            ApiResponse(true, "product created"), HttpStatus.CREATED);
  }

  @PostMapping("/update/{productID}")
  public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productID") Integer productId, @Valid @RequestBody Product product) {
    if (Objects.nonNull(productService.readProduct(productId))) {
      productService.updateProduct(productId, product);
      return new ResponseEntity<>(new ApiResponse(true, "product update complete"), HttpStatus.OK);
    }

    return new ResponseEntity<>(new ApiResponse(false, "product does not exist"), HttpStatus.NOT_FOUND);
  }
}
