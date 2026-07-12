package com.shopkart.stepdefs;

import com.shopkart.api.AuthClient;
import com.shopkart.api.CartClient;
import com.shopkart.data.db.CartRepository;
import com.shopkart.support.WorldContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CartSteps {

    private final WorldContext world;

    private final AuthClient authClient =
            new AuthClient();

    private final CartClient cartClient =
            new CartClient();

    private final CartRepository cartRepository =
            new CartRepository();

    private Response cartResponse;
    private Response addToCartResponse;

    public CartSteps(
            WorldContext world
    ) {

        this.world = world;
    }

    @Given(
            "{string} creates an API cart with {int} x {string}"
    )
    public void customerCreatesApiCart(
            String persona,
            int quantity,
            String sku
    ) {

        String token =
                authClient.tokenFor(
                        persona
                );

        Response createCartResponse =
                cartClient.create(
                        token
                );

        assertEquals(
                201,
                createCartResponse.statusCode(),
                "Cart creation should return HTTP 201"
        );

        String cartId =
                createCartResponse
                        .jsonPath()
                        .getString("cartId");

        assertNotNull(
                cartId,
                "Cart ID must be returned"
        );

        world.setCartId(
                cartId
        );

        world.setProductSku(
                sku
        );

        cartResponse =
                cartClient.addItem(
                        token,
                        cartId,
                        sku,
                        quantity
                );

        assertEquals(
                200,
                cartResponse.statusCode(),
                "Adding an item should return HTTP 200"
        );
    }

    @Then(
            "the API cart total is {int} paise"
    )
    public void apiCartTotalIs(
            int expectedTotalPaise
    ) {

        assertNotNull(
                cartResponse,
                "Cart API response must exist"
        );

        int actualTotalPaise =
                cartResponse
                        .jsonPath()
                        .getInt("totalPaise");

        assertEquals(
                expectedTotalPaise,
                actualTotalPaise,
                "Unexpected cart total"
        );
    }

    @Then(
            "the database cart contains quantity {int} for SKU {string}"
    )
    public void databaseCartContainsQuantity(
            int expectedQuantity,
            String sku
    ) {

        String cartId =
                world.getCartId();

        assertNotNull(
                cartId,
                "Cart ID must exist before DB verification"
        );

        int actualQuantity =
                cartRepository.findItemQuantity(
                        Long.parseLong(cartId),
                        sku
                );

        assertEquals(
                expectedQuantity,
                actualQuantity,
                "Unexpected cart item quantity in database"
        );
    }
    @Given(
            "{string} has a new API cart"
    )
    public void customerHasNewApiCart(
            String persona
    ) {

        String token =
                authClient.tokenFor(
                        persona
                );

        Response createCartResponse =
                cartClient.create(
                        token
                );

        assertEquals(
                201,
                createCartResponse.statusCode(),
                "Cart creation should return HTTP 201"
        );

        String cartId =
                createCartResponse
                        .jsonPath()
                        .getString("cartId");

        assertNotNull(
                cartId,
                "Cart ID must be returned"
        );

        world.setCartId(
                cartId
        );

        world.setCurrentPersona(
                persona
        );
    }

    @When(
            "she tries to add {int} x {string} to the API cart"
    )
    public void customerTriesToAddProduct(
            int quantity,
            String sku
    ) {

        String persona =
                world.getCurrentPersona();

        String cartId =
                world.getCartId();

        assertNotNull(
                persona,
                "Persona must exist before adding product"
        );

        assertNotNull(
                cartId,
                "Cart ID must exist before adding product"
        );

        String token =
                authClient.tokenFor(
                        persona
                );

        addToCartResponse =
                cartClient.addItem(
                        token,
                        cartId,
                        sku,
                        quantity
                );
    }

    @Then(
            "the add-to-cart API response status is {int}"
    )
    public void addToCartResponseStatusIs(
            int expectedStatus
    ) {

        assertNotNull(
                addToCartResponse,
                "Add-to-cart API response must exist"
        );

        assertEquals(
                expectedStatus,
                addToCartResponse.statusCode(),
                "Unexpected add-to-cart API response status"
        );
    }
}