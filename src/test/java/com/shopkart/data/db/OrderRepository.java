package com.shopkart.data.db;

public class OrderRepository {

    public int countOrders(
            long orderId,
            String status,
            String persona
    ) {

        String sql =
                """
                SELECT COUNT(*)
                FROM orders o
                JOIN customers c
                    ON c.id = o.customer_id
                WHERE o.id = ?
                  AND o.status = ?
                  AND c.persona = ?
                """;

        return DatabaseHelper.queryForInt(
                sql,
                orderId,
                status,
                persona
        );
    }
}