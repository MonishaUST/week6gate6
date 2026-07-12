package com.shopkart.api.tests;

import com.shopkart.support.BaseApiTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OutOfStockApiTest
        extends BaseApiTest {

    @Test
    void addingOutOfStockCapReturns409() {

        String token =
                authClient.tokenFor("alice");

        Response cartResponse =
                cartClient.create(token);

        assertEquals(
                201,
                cartResponse.statusCode()
        );

        String cartId =
                cartResponse
                        .jsonPath()
                        .getString("cartId");

        Response addResponse =
                cartClient.addItem(
                        token,
                        cartId,
                        "SKU-CAP",
                        1
                );

        assertEquals(
                409,
                addResponse.statusCode()
        );
    }
}
