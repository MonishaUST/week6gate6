package com.shopkart.stepdefs;

import com.shopkart.support.WorldContext;
import io.cucumber.java.en.Then;

public class OrderSteps {

    private final WorldContext world;

    public OrderSteps(
            WorldContext world
    ) {

        this.world = world;
    }

    @Then(
            "the order confirmation shows status {string}"
    )
    public void orderShowsStatus(
            String expectedStatus
    ) {

        world
                .getOrderPage()
                .shouldShowOrderNumber()
                .shouldHaveStatus(expectedStatus)
                .shouldShowOrderTotal()
                .shouldShowDeliveryAddress(
                        world.getDeliveryAddress()
                );

        String orderId =
                world
                        .getOrderPage()
                        .getOrderId();

        world.setOrderId(orderId);
    }
}