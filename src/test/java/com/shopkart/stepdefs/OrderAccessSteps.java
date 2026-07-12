package com.shopkart.stepdefs;

import com.shopkart.api.AuthClient;
import com.shopkart.api.OrderClient;
import com.shopkart.data.builders.OrderBuilder;
import com.shopkart.support.WorldContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderAccessSteps {

    private final WorldContext world;

    private final AuthClient authClient =
            new AuthClient();

    private final OrderClient orderClient =
            new OrderClient();

    private Response response;

    public OrderAccessSteps(
            WorldContext world
    ) {

        this.world = world;
    }

    @Given(
            "{string} has a PLACED order"
    )
    public void customerHasPlacedOrder(
            String persona
    ) {

        int orderId =
                OrderBuilder
                        .anOrder()
                        .forCustomer(persona)
                        .withItem(
                                "SKU-BAG",
                                1
                        )
                        .build();

        world.setOrderId(
                String.valueOf(orderId)
        );
    }

    @When(
            "{string} requests GET API for that order"
    )
    public void customerRequestsOrder(
            String persona
    ) {

        String token =
                authClient.tokenFor(
                        persona
                );

        int orderId =
                Integer.parseInt(
                        world.getOrderId()
                );

        response =
                orderClient.get(
                        token,
                        orderId
                );
    }

    @Then(
            "the response status is {int}"
    )
    public void responseStatusIs(
            int expectedStatus
    ) {

        assertEquals(
                expectedStatus,
                response.statusCode(),
                "Unexpected API response status"
        );
    }
}
