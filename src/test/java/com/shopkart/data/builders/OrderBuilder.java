package com.shopkart.data.builders;

import com.shopkart.api.AuthClient;
import com.shopkart.api.CartClient;
import com.shopkart.api.OrderClient;
import io.restassured.response.Response;

public class OrderBuilder {

    private final AuthClient authClient =
            new AuthClient();

    private final CartClient cartClient =
            new CartClient();

    private final OrderClient orderClient =
            new OrderClient();

    private String persona =
            "alice";

    private String sku =
            "SKU-BAG";

    private int quantity =
            1;

    private String address =
            "UST Campus, Trivandrum, Kerala";

    public static OrderBuilder anOrder() {

        return new OrderBuilder();
    }

    public OrderBuilder forCustomer(
            String persona
    ) {

        this.persona = persona;

        return this;
    }

    public OrderBuilder withItem(
            String sku,
            int quantity
    ) {

        this.sku = sku;
        this.quantity = quantity;

        return this;
    }

    public OrderBuilder withAddress(
            String address
    ) {

        this.address = address;

        return this;
    }

    public int build() {

        String token =
                authClient.tokenFor(
                        persona
                );

        Response cartResponse =
                cartClient.create(
                        token
                );

        if (cartResponse.statusCode() != 201) {

            throw new IllegalStateException(
                    "Unable to create cart for "
                            + persona
                            + ". Status: "
                            + cartResponse.statusCode()
                            + ". Body: "
                            + cartResponse.asString()
            );
        }

        int cartId =
                cartResponse
                        .jsonPath()
                        .getInt("cartId");

        if (cartId <= 0) {

            throw new IllegalStateException(
                    "Cart was created but cartId is invalid"
            );
        }

        Response addItemResponse =
                cartClient.addItem(
                        token,
                        String.valueOf(cartId),
                        sku,
                        quantity
                );

        if (addItemResponse.statusCode() != 200) {

            throw new IllegalStateException(
                    "Unable to add "
                            + sku
                            + " to cart. Status: "
                            + addItemResponse.statusCode()
                            + ". Body: "
                            + addItemResponse.asString()
            );
        }

        Response orderResponse =
                orderClient.place(
                        token,
                        cartId,
                        address
                );

        if (orderResponse.statusCode() != 201) {

            throw new IllegalStateException(
                    "Unable to place order for "
                            + persona
                            + ". Status: "
                            + orderResponse.statusCode()
                            + ". Body: "
                            + orderResponse.asString()
            );
        }

        int orderId =
                orderResponse
                        .jsonPath()
                        .getInt("orderId");

        if (orderId <= 0) {

            throw new IllegalStateException(
                    "Order was created but orderId is invalid"
            );
        }

        return orderId;
    }
}