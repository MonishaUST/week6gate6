package com.shopkart.api.tests;

import com.shopkart.support.BaseApiTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CancelRulesApiTest
        extends BaseApiTest {

    @Test
    void cancelledOrderCannotBeCancelledAgain() {

        String token =
                authClient.tokenFor("alice");

        Response cartResponse =
                cartClient.create(token);

        assertEquals(
                201,
                cartResponse.statusCode()
        );

        int cartId =
                cartResponse
                        .jsonPath()
                        .getInt("cartId");

        assertTrue(
                cartId > 0
        );

        Response addResponse =
                cartClient.addItem(
                        token,
                        String.valueOf(cartId),
                        "SKU-PEN",
                        1
                );

        assertEquals(
                200,
                addResponse.statusCode()
        );

        Response orderResponse =
                orderClient.place(
                        token,
                        cartId,
                        "UST Campus, Trivandrum, Kerala"
                );

        assertEquals(
                201,
                orderResponse.statusCode()
        );

        int orderId =
                orderResponse
                        .jsonPath()
                        .getInt("orderId");

        assertTrue(
                orderId > 0
        );

        assertEquals(
                "PLACED",
                orderResponse
                        .jsonPath()
                        .getString("status")
        );

        Response firstCancel =
                orderClient.cancel(
                        token,
                        orderId
                );

        assertEquals(
                200,
                firstCancel.statusCode()
        );

        assertEquals(
                "CANCELLED",
                firstCancel
                        .jsonPath()
                        .getString("status")
        );

        Response secondCancel =
                orderClient.cancel(
                        token,
                        orderId
                );

        assertEquals(
                409,
                secondCancel.statusCode()
        );
    }
}