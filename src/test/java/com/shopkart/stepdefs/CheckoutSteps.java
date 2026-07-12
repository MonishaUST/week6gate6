package com.shopkart.stepdefs;

import com.shopkart.support.WorldContext;
import com.shopkart.ui.pages.OrderPage;
import io.cucumber.java.en.When;

public class CheckoutSteps {

    private final WorldContext world;

    public CheckoutSteps(WorldContext world) {
        this.world = world;
    }

    @When("she checks out with a valid address")
    public void checkoutWithValidAddress() {

        String address =
                "UST Campus, Trivandrum, Kerala";

        world.setDeliveryAddress(address);

        world
                .getCartPage()
                .shouldContainProduct(
                        world.getProductSku()
                )
                .shouldShowCartTotal();

        OrderPage orderPage =
                world
                        .getCartPage()
                        .checkout()
                        .enterAddress(address)
                        .placeOrder();

        world.setOrderPage(orderPage);
    }
}
