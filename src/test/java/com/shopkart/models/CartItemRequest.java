package com.shopkart.models;

public record CartItemRequest(
        String sku,
        int qty
) {
}