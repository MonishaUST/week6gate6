package com.shopkart.stepdefs;

import com.shopkart.api.AuthClient;
import com.shopkart.api.OrderClient;
import com.shopkart.support.WorldContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CancelRulesSteps {

    private final WorldContext world;

    private final AuthClient authClient =
            new AuthClient();

    private final OrderClient orderClient =
            new OrderClient();

    private Response firstCancelResponse;

    private Response secondCancelResponse;

    public CancelRulesSteps(
            WorldContext world
    ) {

        this.world = world;
    }

    @When(
            "{string} cancels that order for the first time"
    )
    public void customerCancelsOrderFirstTime(
            String persona
    ) {

        String orderId =
                world.getOrderId();

        assertNotNull(
                orderId,
                "Order ID must exist before cancellation"
        );

        String token =
                authClient.tokenFor(
                        persona
                );

        firstCancelResponse =
                orderClient.cancel(
                        token,
                        Integer.parseInt(orderId)
                );
    }

    @Then(
            "the first cancellation response status is {int}"
    )
    public void firstCancellationStatusIs(
            int expectedStatus
    ) {

        assertNotNull(
                firstCancelResponse,
                "First cancellation response is missing"
        );

        assertEquals(
                expectedStatus,
                firstCancelResponse.statusCode(),
                "Unexpected first cancellation response status"
        );
    }

    @Then(
            "the cancelled order status is {string}"
    )
    public void cancelledOrderStatusIs(
            String expectedStatus
    ) {

        assertNotNull(
                firstCancelResponse,
                "First cancellation response is missing"
        );

        String actualStatus =
                firstCancelResponse
                        .jsonPath()
                        .getString("status");

        assertEquals(
                expectedStatus,
                actualStatus,
                "Unexpected cancelled order status"
        );
    }

    @When(
            "{string} cancels the same order again"
    )
    public void customerCancelsSameOrderAgain(
            String persona
    ) {

        String orderId =
                world.getOrderId();

        assertNotNull(
                orderId,
                "Order ID must exist before second cancellation"
        );

        String token =
                authClient.tokenFor(
                        persona
                );

        secondCancelResponse =
                orderClient.cancel(
                        token,
                        Integer.parseInt(orderId)
                );
    }

    @Then(
            "the second cancellation response status is {int}"
    )
    public void secondCancellationStatusIs(
            int expectedStatus
    ) {

        assertNotNull(
                secondCancelResponse,
                "Second cancellation response is missing"
        );

        assertEquals(
                expectedStatus,
                secondCancelResponse.statusCode(),
                "Unexpected second cancellation response status"
        );
    }
}
