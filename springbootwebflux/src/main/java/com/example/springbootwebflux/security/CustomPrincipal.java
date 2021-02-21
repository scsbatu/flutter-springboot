package com.example.springbootwebflux.security;

import lombok.Data;

@Data
public class CustomPrincipal {
    private String uid;
    private String phoneNumber;
}

