package com.example.ecommerce.service;

import com.example.ecommerce.dto.CartDto;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;

    public CartService(CartRepository cartRepository,  ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
    }


    // Yeni bir Cart oluştur

//    public CartDto createCart(Long userId) {
//        UserDto userDto = userService.getUserById(userId);
//
//        User user = userService.convertToUser(userDto);
//
//        Cart cart = new Cart();
//        cart.setUser(user); // Sepeti kullanıcı ile ilişkilendir
//        user.setCart(cart); // Kullanıcıyı da sepet ile ilişkilendir
//        cart = cartRepository.save(cart);
//        return new CartDto(cart);
//    }
    @Transactional
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Transactional
    public CartDto getCartByUserId(Long userId) {
        return cartRepository.findCartByUserId(userId)
                .map(CartDto::new)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user with id: " + userId));
    }


    // Sepete ürün ekle
    @Transactional
    public CartDto addProductToCart(Long cartId, ProductDto productDto) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with id: " + cartId));

        // `Product` nesnesini `productService` ile alıyoruz
        Product product = productService.convertToProduct(productDto);

        // `quantity` değerini `ProductDto`'dan alarak sepete ekliyoruz
        cart.addProduct(product, productDto.getQuantity());
        cart = cartRepository.save(cart);

        return new CartDto(cart);
    }


    @Transactional
    public CartDto updateProductQuantity(Long cartId, Long productId, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with id: " + cartId));

        Product product = cart.getProducts().keySet().stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Product not found in cart with id: " + productId));

        cart.updateProductQuantity(product, quantity); // Miktarı güncelle
        cartRepository.save(cart); // Güncellenmiş sepeti kaydet

        return new CartDto(cart); // Güncellenmiş sepeti döndür
    }





    // Belirli bir sepeti getirme
    @Transactional(readOnly = true)
    public CartDto getCartById(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with id: " + cartId));
        return new CartDto(cart);
    }

    // Sepeti temizleme
    @Transactional
    public void clearCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with id: " + cartId));
        cart.getProducts().clear();
        cart.setTotalPrice(0.0);
        cartRepository.save(cart);
    }
}
