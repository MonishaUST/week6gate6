package com.shopkart.stepdefs;

import com.shopkart.api.AuthClient;
import com.shopkart.api.OrderClient;
import com.shopkart.support.WorldContext;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApiOrderSteps {

    private final WorldContext world;

    private final AuthClient authClient =
            new AuthClient();

    private final OrderClient orderClient =
            new OrderClient();

    public ApiOrderSteps(
            WorldContext world
    ) {

        this.world = world;
    }

    @Then(
            "GET API for the created order returns {string} and totalPaise {int}"
    )
    public void apiReturnsOrderStatusAndTotal(
            String expectedStatus,
            int expectedTotalPaise
    ) {

        String orderId =
                world.getOrderId();

        assertNotNull(
                orderId,
                "Order ID must be captured from UI before API verification"
        );

        String token =
                authClient.tokenFor(
                        "alice"
                );

        Response response =
                orderClient.get(
                        token,
                        Integer.parseInt(orderId)
                );

        assertEquals(
                200,
                response.statusCode(),
                "GET order API should return HTTP 200"
        );

        String actualStatus =
                response
                        .jsonPath()
                        .getString("status");

        int actualTotalPaise =
                response
                        .jsonPath()
                        .getInt("totalPaise");

        assertEquals(
                expectedStatus,
                actualStatus,
                "Order status from API does not match UI order"
        );

        assertEquals(
                expectedTotalPaise,
                actualTotalPaise,
                "Order total from API is incorrect"
        );
    }
}