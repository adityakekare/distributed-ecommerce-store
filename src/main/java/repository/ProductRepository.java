package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
  Product findProductByName(String product);
}
