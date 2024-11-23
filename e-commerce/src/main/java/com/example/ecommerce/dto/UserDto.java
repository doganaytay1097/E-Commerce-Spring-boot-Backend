package com.example.ecommerce.dto;

import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.Role;
import com.example.ecommerce.entity.User;
import lombok.Data;

import java.util.Set;

@Data
public class UserDto {

    private Long id;

    private String username;

    private String password;

    private String email;

    private Long phone;

    private String refreshToken;

    private Set<Role> roles;




    public UserDto(User entity) {

        this.id = entity.getId();
        this.username = entity.getUsername();
        this.email = entity.getEmail();
        this.phone = entity.getPhone();
        this.roles = entity.getRoles();
        this.refreshToken = entity.getRefreshToken();


    }
}
