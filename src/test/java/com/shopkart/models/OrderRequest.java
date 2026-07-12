package com.shopkart.models;

public record OrderRequest(
        int cartId,
        String address
) {
}