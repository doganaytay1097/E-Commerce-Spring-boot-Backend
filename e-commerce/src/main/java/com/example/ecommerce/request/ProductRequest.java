package com.example.ecommerce.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {

    private Long id;

    private String productName;

    private String productDescription;

    private String productCategory;

    private double price;

    private int stock;
}
