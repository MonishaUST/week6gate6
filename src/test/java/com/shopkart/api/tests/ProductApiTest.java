package com.shopkart.api.tests;

import com.shopkart.support.BaseApiTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProductApiTest
        extends BaseApiTest {

    @Test
    void searchForBagReturnsMetroCarryall() {

        Response response =
                productClient.search("bag");

        assertEquals(
                200,
                response.statusCode()
        );

        List<Map<String, Object>> products =
                response.jsonPath()
                        .getList("$");

        assertFalse(products.isEmpty());

        boolean productFound =
                products.stream()
                        .anyMatch(
                                product ->
                                        "SKU-BAG".equals(
                                                product.get("sku")
                                        )
                                                &&
                                                "Metro Carryall".equals(
                                                        product.get("name")
                                                )
                        );

        assertTrue(
                productFound,
                "SKU-BAG Metro Carryall was not returned"
        );
    }
}