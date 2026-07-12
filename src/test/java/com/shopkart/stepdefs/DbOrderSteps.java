package com.shopkart.stepdefs;

import com.shopkart.data.db.OrderRepository;
import com.shopkart.support.WorldContext;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DbOrderSteps {

    private final WorldContext world;

    private final OrderRepository orderRepository =
            new OrderRepository();

    public DbOrderSteps(
            WorldContext world
    ) {

        this.world = world;
    }

    @Then(
            "the orders table has exactly one {string} row for {string}"
    )
    public void ordersTableHasExactlyOneRow(
            String expectedStatus,
            String persona
    ) {

        String orderId =
                world.getOrderId();

        assertNotNull(
                orderId,
                "Order ID must be captured before DB verification"
        );

        int orderCount =
                orderRepository.countOrders(
                        Long.parseLong(orderId),
                        expectedStatus,
                        persona
                );

        assertEquals(
                1,
                orderCount,
                "Expected exactly one matching order row in database"
        );
    }
}