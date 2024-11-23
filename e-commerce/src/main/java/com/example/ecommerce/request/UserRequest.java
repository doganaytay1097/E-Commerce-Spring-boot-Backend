package com.example.ecommerce.request;

import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.Role;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserRequest {

    private Long id;

    private String username;

    private String password;

    private String email;

    private Long phone;

}
