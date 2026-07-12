package com.shopkart.api.tests;

import com.shopkart.support.BaseApiTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CartApiTest
        extends BaseApiTest {

    @Test
    void twoBagsHaveTotalOf99800Paise() {

        String token =
                authClient.tokenFor("alice");

        Response createCartResponse =
                cartClient.create(token);

        assertEquals(
                201,
                createCartResponse.statusCode()
        );

        String cartId =
                createCartResponse
                        .jsonPath()
                        .getString("cartId");

        assertNotNull(cartId);

        Response addItemResponse =
                cartClient.addItem(
                        token,
                        cartId,
                        "SKU-BAG",
                        2
                );

        assertEquals(
                200,
                addItemResponse.statusCode()
        );

        int totalPaise =
                addItemResponse
                        .jsonPath()
                        .getInt("totalPaise");

        assertEquals(
                99800,
                totalPaise
        );

        Response getCartResponse =
                cartClient.get(
                        token,
                        cartId
                );

        assertEquals(
                200,
                getCartResponse.statusCode()
        );

        assertEquals(
                99800,
                getCartResponse
                        .jsonPath()
                        .getInt("totalPaise")
        );
    }
}