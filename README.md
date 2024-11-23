📦 E-Commerce Backend Project
This project is a Spring Boot-based backend implementation for an e-commerce platform. It provides APIs for user authentication, product management, cart operations, and token-based authentication using JWT (JSON Web Token).

🚀 Features
User Management

User Registration & Login
Password encryption using BCrypt
Role-based access control
Refresh token mechanism for secure re-authentication
JWT Authentication

Access Token: Valid for 15 minutes.
Refresh Token: Valid for 7 days to reissue access tokens without re-login.
Cart Management

Add, remove, and update products in the cart.
Fetch user's cart details.
Product Management

List all products with categories and prices.
Fetch product details.
Exception Handling

Centralized exception handling using @ControllerAdvice.
Security

Spring Security integration with JWT filters for request validation.
📚 API Endpoints
Authentication
POST /v1/user/register - Register a new user.
POST /v1/user/login - Login and receive tokens.
POST /v1/user/refresh-token - Refresh access token using refresh token.
Cart
GET /api/v1/cart/{cartId} - Retrieve cart items.
POST /api/v1/cart/{cartId}/add-product - Add a product to the cart.
PUT /api/v1/cart/{cartId}/update-product-quantity - Update product quantity in the cart.
DELETE /api/v1/cart/{cartId}/remove-product - Remove a product from the cart.
Product
GET /api/v1/product - Retrieve all products.
GET /api/v1/product/{id} - Retrieve a specific product.
⚙️ Technologies Used
Spring Boot 3.x
Spring Security for JWT Authentication
Hibernate/JPA for ORM
H2 Database for testing (can be replaced with MySQL or PostgreSQL)
Lombok for cleaner code
Gradle as the build tool.
📁 Project Structure
bash
Kodu kopyala
src/
├── main/
│   ├── java/com/example/ecommerce
│   │   ├── controller/   # REST Controllers
│   │   ├── entity/       # JPA Entities
│   │   ├── repository/   # Spring Data Repositories
│   │   ├── service/      # Business Logic
│   │   ├── request/      # Request DTOs
│   │   ├── dto/          # Response DTOs
│   │   ├── exception/    # Custom Exception Handling
│   │   ├── security/     # JWT and Security Configuration
│   ├── resources/
│   │   ├── application.yml   # Configurations
│   │   ├── data.sql          # Test Data (Optional)
└── ...
💡 Key Highlights
JWT Refresh Token Flow

The system generates both an access token and a refresh token during login.
Refresh token is securely stored in the database and can be used to re-authenticate users without requiring login credentials.
Layered Architecture

Decoupled layers for better maintainability:
Controller for HTTP API endpoints.
Service for business logic.
Repository for database interactions.
Centralized Exception Handling

Custom exceptions for improved API responses.
