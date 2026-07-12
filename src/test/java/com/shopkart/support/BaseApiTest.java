package com.shopkart.support;

import com.shopkart.api.AuthClient;
import com.shopkart.api.CartClient;
import com.shopkart.api.OrderClient;
import com.shopkart.api.ProductClient;

public abstract class BaseApiTest {

    protected AuthClient authClient;

    protected ProductClient productClient;

    protected CartClient cartClient;

    protected OrderClient orderClient;

    protected BaseApiTest() {

        authClient =
                new AuthClient();

        productClient =
                new ProductClient();

        cartClient =
                new CartClient();

        orderClient =
                new OrderClient();
    }
}