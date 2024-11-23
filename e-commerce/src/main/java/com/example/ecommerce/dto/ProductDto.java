package com.example.ecommerce.dto;

import com.example.ecommerce.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String productName;
    private String productDescription;
    private double price;
    private String productCategory;
    private int stock;
    private int quantity; // Sepete eklenen miktarı belirtmek için quantity alanı eklendi

    public ProductDto(Product entity) {
        this.id = entity.getId();
        this.productName = entity.getProductName();
        this.productDescription = entity.getProductDescription();
        this.price = entity.getPrice();
        this.productCategory = entity.getProductCategory();
        this.stock = entity.getStock();
        // quantity burada doğrudan entity’den alınmaz, sepet işlemlerinde kullanıcı tarafından belirlenir.
    }
}
