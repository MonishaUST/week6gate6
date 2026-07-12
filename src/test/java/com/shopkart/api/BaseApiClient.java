package com.shopkart.api;

import com.shopkart.config.AppConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class BaseApiClient {

    protected RequestSpecification publicSpec() {

        return new RequestSpecBuilder()
                .setBaseUri(
                        AppConfig.apiBaseUrl()
                )
                .setContentType(
                        ContentType.JSON
                )
                .setAccept(
                        ContentType.JSON
                )
                .build();
    }

    protected RequestSpecification authenticatedSpec(
            String token
    ) {

        if (token == null || token.isBlank()) {

            throw new IllegalArgumentException(
                    "Bearer token is required"
            );
        }

        return new RequestSpecBuilder()
                .setBaseUri(
                        AppConfig.apiBaseUrl()
                )
                .setContentType(
                        ContentType.JSON
                )
                .setAccept(
                        ContentType.JSON
                )
                .addHeader(
                        "Authorization",
                        "Bearer " + token
                )
                .build();
    }
}