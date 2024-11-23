package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CartDto;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }



    @GetMapping("/user/{userId}")
    public ResponseEntity<CartDto> getCartByUserId(@PathVariable Long userId) {
        CartDto cartDto = cartService.getCartByUserId(userId);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @PutMapping("/{cartId}/update-product-quantity")
    public ResponseEntity<CartDto> updateProductQuantity(
            @PathVariable Long cartId,
            @RequestParam Long productId,
            @RequestParam int quantity) {

        CartDto updatedCart = cartService.updateProductQuantity(cartId, productId, quantity);
        return ResponseEntity.ok(updatedCart);
    }


//    @PostMapping("/{userId}")
//    public ResponseEntity<CartDto> createCart(@PathVariable Long userId) {
//        CartDto cartDto = cartService.createCart(userId);
//        return ResponseEntity.status(HttpStatus.CREATED).body(cartDto);
//    }

    @PostMapping("/{cartId}/add-product")
    public ResponseEntity<CartDto> addProductToCart(@PathVariable Long cartId, @RequestBody ProductDto productDto) {
        CartDto updatedCart = cartService.addProductToCart(cartId, productDto);
        return ResponseEntity.ok(updatedCart);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCartById(@PathVariable Long cartId) {
        CartDto cartDto = cartService.getCartById(cartId);
        return ResponseEntity.ok(cartDto);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> clearCart(@PathVariable Long cartId) {
        cartService.clearCart(cartId);
        return ResponseEntity.noContent().build();
    }
}
