package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.request.ProductRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductDto::new).collect(Collectors.toList());
    }

    public ProductDto getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);

        return product.map(ProductDto::new).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }
    public ProductDto getProductByName(String name) {
        Optional<Product> product = productRepository.findByProductName(name);

        return product.map(ProductDto::new).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }
    public List<ProductDto> getProductsByCategory(String category) {
        List<Product> productList = productRepository.findByProductCategoryContaining(category);

        return productList.stream().map(ProductDto::new).collect(Collectors.toList());
    }

    public void changeProduct(Product product, ProductRequest productRequest) {

        product.setId(productRequest.getId());
        product.setProductName(productRequest.getProductName());
        product.setProductDescription(productRequest.getProductDescription());
        product.setProductCategory(productRequest.getProductCategory());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());

        productRepository.save(product);

    }


    public Product createProduct(ProductRequest productRequest) {
        Product product = new Product();

        changeProduct(product, productRequest);
        product.setCreatedAt(LocalDateTime.now());

        return product;
    }

    public Product convertToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setProductName(productDto.getProductName());
        product.setProductDescription(productDto.getProductDescription());
        product.setPrice(productDto.getPrice());
        product.setProductCategory(productDto.getProductCategory());
        product.setStock(productDto.getStock());
        return product;
    }

//    @Transactional
//    public Product updateProductQuantity(Long productId, int quantity) {
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));
//
//        product.setQuantity(quantity);
//        return productRepository.save(product);
//    }


    public Product updateProduct(Long productId, ProductRequest productRequest) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            changeProduct(product.get(), productRequest);
            product.get().setUpdatedAt(LocalDateTime.now());
            return productRepository.save(product.get());
        }else
            throw new ResourceNotFoundException("Product not updated because not found");
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
