package com.shopkart.models;

public record LoginRequest(
        String email,
        String password
) {
}