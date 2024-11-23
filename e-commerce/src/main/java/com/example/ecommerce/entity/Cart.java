package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Setter
@Table(name = "cart")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ElementCollection
    @CollectionTable(name = "cart_products", joinColumns = @JoinColumn(name = "cart_id"))
    @MapKeyJoinColumn(name = "product_id", referencedColumnName = "id")  // Ürün ID yerine doğrudan Product referansı
    @Column(name = "quantity")
    private Map<Product, Integer> products = new HashMap<>();

    private Double totalPrice = 0.0;


    public void addProduct(Product product, int quantity) {
        products.merge(product, quantity, Integer::sum);
        recalculateTotalPrice();
    }

    public void updateProductQuantity(Product product, int quantity) {
        if (products.containsKey(product)) {
            products.put(product, quantity);
            recalculateTotalPrice(); // Miktar değiştirildiğinde fiyatı yeniden hesapla
        }
    }

    private void recalculateTotalPrice() {
        this.totalPrice = products.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }
}
