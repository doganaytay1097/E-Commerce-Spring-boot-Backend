package com.example.ecommerce.dto;

import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.Product;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class CartDto {

    private Long id;
    private Double totalPrice;
    private List<ProductDtoWithQuantity> products;

    public CartDto(Cart entity) {
        this.id = entity.getId();
        this.products = entity.getProducts().entrySet().stream()
                .map(entry -> new ProductDtoWithQuantity(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        this.totalPrice = this.products.stream()
                .mapToDouble(product -> product.getPrice() * product.getQuantity())
                .sum();
    }
}
