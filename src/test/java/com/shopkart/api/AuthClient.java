package com.shopkart.api;

import com.shopkart.data.secret.Secrets;
import com.shopkart.models.LoginRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthClient
        extends BaseApiClient {

    public Response login(
            String email,
            String password
    ) {

        LoginRequest request =
                new LoginRequest(
                        email,
                        password
                );

        return given()
                .spec(publicSpec())
                .body(request)
                .when()
                .post("/auth/login");
    }

    public Response loginAs(
            String persona
    ) {

        String email =
                emailFor(persona);

        String password =
                Secrets.passwordFor(persona);

        return login(
                email,
                password
        );
    }

    public String tokenFor(
            String persona
    ) {

        Response response =
                loginAs(persona);

        if (response.statusCode() != 200) {

            throw new IllegalStateException(
                    "Login failed for persona "
                            + persona
                            + ". Expected 200 but received "
                            + response.statusCode()
            );
        }

        String token =
                response
                        .jsonPath()
                        .getString("token");

        if (token == null
                || token.isBlank()) {

            throw new IllegalStateException(
                    "Login succeeded but token was missing for persona: "
                            + persona
            );
        }

        return token;
    }

    public String customerIdFor(
            String persona
    ) {

        Response response =
                loginAs(persona);

        if (response.statusCode() != 200) {

            throw new IllegalStateException(
                    "Login failed for persona: "
                            + persona
            );
        }

        return response
                .jsonPath()
                .getString("customerId");
    }

    private String emailFor(
            String persona
    ) {

        return switch (
                persona.toLowerCase()
                ) {

            case "alice" ->
                    "alice@shopkart.test";

            case "bob" ->
                    "bob@shopkart.test";

            case "carol" ->
                    "carol@shopkart.test";

            default ->
                    throw new IllegalArgumentException(
                            "Unknown persona: "
                                    + persona
                    );
        };
    }
}