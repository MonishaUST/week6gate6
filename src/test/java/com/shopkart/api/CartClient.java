package com.shopkart.api;

import com.shopkart.models.CartItemRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CartClient
        extends BaseApiClient {

    public Response create(
            String token
    ) {

        return given()
                .spec(
                        authenticatedSpec(token)
                )
                .when()
                .post("/carts");
    }

    public Response addItem(
            String token,
            String cartId,
            String sku,
            int quantity
    ) {

        CartItemRequest request =
                new CartItemRequest(
                        sku,
                        quantity
                );

        return given()
                .spec(
                        authenticatedSpec(token)
                )
                .body(request)
                .when()
                .post(
                        "/carts/"
                                + cartId
                                + "/items"
                );
    }

    public Response get(
            String token,
            String cartId
    ) {

        return given()
                .spec(
                        authenticatedSpec(token)
                )
                .when()
                .get(
                        "/carts/"
                                + cartId
                );
    }
}
