package com.shopkart.models;

public record Product(
        String sku,
        String name,
        int pricePaise,
        int stock
) {
}