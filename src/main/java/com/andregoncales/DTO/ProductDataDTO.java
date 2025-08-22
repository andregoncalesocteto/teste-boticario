package com.andregoncales;

public class ProductDataDTO {
    private String product;
    private Integer quantity;
    private String price;
    private String type;
    private String industry;
    private String description;
    private String origin; // Adicionado

    public ProductDataDTO() {}

    public ProductDataDTO(String product, Integer quantity, String price, String type, String industry, String description, String origin) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
        this.industry = industry;
        this.description = description;
        this.origin = origin;
    }

    // getters e setters

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}