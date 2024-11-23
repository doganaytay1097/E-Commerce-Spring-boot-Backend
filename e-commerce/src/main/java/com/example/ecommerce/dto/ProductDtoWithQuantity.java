package com.example.ecommerce.dto;

import com.example.ecommerce.entity.Product;
import lombok.Data;

@Data
public class ProductDtoWithQuantity {
    private Long id;
    private String productName;
    private String productDescription;
    private double price;
    private String productCategory;
    private int stock;
    private int quantity;

    public ProductDtoWithQuantity(Product product, int quantity) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.productDescription = product.getProductDescription();
        this.price = product.getPrice();
        this.productCategory = product.getProductCategory();
        this.stock = product.getStock();
        this.quantity = quantity;
    }
}
