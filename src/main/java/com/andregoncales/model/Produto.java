// package com.andregoncales.model;

// import jakarta.persistence.*;
// import java.math.BigDecimal;

// @Entity
// @Table(
//     name = "produto",
//     uniqueConstraints = @UniqueConstraint(columnNames = {"product", "type"})
// )
// public class Produto {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String product;
//     private Integer quantity;

//     @Column(precision = 10, scale = 2)
//     private BigDecimal price;

//     private String type;
//     private String industry;
//     private String origin;

//     // Getters e Setters

//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public String getProduct() { return product; }
//     public void setProduct(String product) { this.product = product; }

//     public Integer getQuantity() { return quantity; }
//     public void setQuantity(Integer quantity) { this.quantity = quantity; }

//     public BigDecimal getPrice() { return price; }
//     public void setPrice(BigDecimal price) { this.price = price; }

//     public String getType() { return type; }
//     public void setType(String type) { this.type = type; }

//     public String getIndustry() { return industry; }
//     public void setIndustry(String industry) { this.industry = industry; }

//     public String getOrigin() { return origin; }
//     public void setOrigin(String origin) { this.origin = origin; }
// }