package com.shopkart.api.tests;

import com.shopkart.support.BaseApiTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationApiTest
        extends BaseApiTest {

    @Test
    void aliceCanLogin() {

        Response response =
                authClient.loginAs("alice");

        assertEquals(
                200,
                response.statusCode()
        );

        String token =
                response.jsonPath()
                        .getString("token");

        String customerId =
                response.jsonPath()
                        .getString("customerId");

        assertNotNull(token);

        assertFalse(token.isBlank());

        assertNotNull(customerId);
    }

    @Test
    void invalidPasswordReturns401() {

        Response response =
                authClient.login(
                        "alice@shopkart.test",
                        "definitely-wrong-password"
                );

        assertEquals(
                401,
                response.statusCode()
        );
    }
}