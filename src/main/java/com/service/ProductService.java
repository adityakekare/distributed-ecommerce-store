package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.exception.ResourceNotFoundException;
import com.model.Product;
import com.repository.ProductRepository;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  public List<Product> listProducts(){
    return productRepository.findAll();
  }

  public void createProduct(Product product){
    productRepository.save(product);
  }

  public Product readProduct(String product){
    return productRepository.findProductByName(product);
  }

  public Product readProduct(Integer productId) {
    return productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
  }

  public void updateProduct(Integer productId, Product newProduct) {
    Product product = productRepository.findById(productId).get();
    product.setName(newProduct.getName());
    product.setPrice(newProduct.getPrice());
    productRepository.save(product);
  }
}
