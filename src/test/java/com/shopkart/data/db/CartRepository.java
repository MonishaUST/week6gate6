package com.shopkart.data.db;

public class CartRepository {

    public int findItemQuantity(
            long cartId,
            String sku
    ) {

        String sql =
                """
                SELECT qty
                FROM cart_items
                WHERE cart_id = ?
                  AND sku = ?
                """;

        return DatabaseHelper.queryForInt(
                sql,
                cartId,
                sku
        );
    }
}