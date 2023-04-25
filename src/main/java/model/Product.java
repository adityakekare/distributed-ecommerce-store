package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "products")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ManyToOne(fetch = FetchType.LAZY)
  private Integer id;

  private @NotBlank String name;

  private @NotBlank float price;

  @Column(name = "stock_left")
  private int stockLeft;

  public Product(@NotBlank String name, @NotBlank float price) {
    this.name = name;
    this.price = price;
    this.stockLeft = 1;
  }

  public Product(String name, float price, int stockLeft) {
    this.name = name;
    this.price = price;
    this.stockLeft = stockLeft;
  }

  @Override
  public String toString() {
    return "Product{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", price=" + price +
            ", stockLeft=" + stockLeft +
            '}';
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public int getStockLeft() {
    return stockLeft;
  }

  public void setStockLeft(int stockLeft) {
    this.stockLeft = stockLeft;
  }
}
