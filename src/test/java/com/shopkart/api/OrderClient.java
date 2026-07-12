package com.shopkart.api;

import com.shopkart.models.OrderRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderClient
        extends BaseApiClient {

    public Response place(
            String token,
            int cartId,
            String address
    ) {

        OrderRequest request =
                new OrderRequest(
                        cartId,
                        address
                );

        return given()
                .spec(
                        authenticatedSpec(token)
                )
                .body(request)
                .when()
                .post("/orders");
    }

    public Response get(
            String token,
            int orderId
    ) {

        return given()
                .spec(
                        authenticatedSpec(token)
                )
                .when()
                .get(
                        "/orders/"
                                + orderId
                );
    }

    public Response cancel(
            String token,
            int orderId
    ) {

        return given()
                .spec(
                        authenticatedSpec(token)
                )
                .when()
                .post(
                        "/orders/"
                                + orderId
                                + "/cancel"
                );
    }
}