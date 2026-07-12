package com.shopkart.api.tests;

import com.shopkart.data.builders.OrderBuilder;
import com.shopkart.support.BaseApiTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderAccessApiTest
        extends BaseApiTest {

    @Test
    void bobCannotReadAliceOrder() {

        int aliceOrderId =
                OrderBuilder
                        .anOrder()
                        .forCustomer("alice")
                        .withItem(
                                "SKU-BAG",
                                1
                        )
                        .build();

        String bobToken =
                authClient.tokenFor(
                        "bob"
                );

        Response bobResponse =
                orderClient.get(
                        bobToken,
                        aliceOrderId
                );

        assertEquals(
                403,
                bobResponse.statusCode(),
                "Bob must not access Alice's order"
        );
    }
}