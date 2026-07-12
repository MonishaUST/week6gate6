package com.shopkart.api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ProductClient
        extends BaseApiClient {

    public Response search(
            String query
    ) {

        return given()
                .spec(publicSpec())
                .queryParam(
                        "q",
                        query
                )
                .when()
                .get("/products");
    }
}