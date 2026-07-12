package com.shopkart.stepdefs;

import com.shopkart.api.ProductClient;
import com.shopkart.support.WorldContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import com.shopkart.ui.pages.HomePage;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductSteps {

    private final WorldContext world;

    private final ProductClient productClient =
            new ProductClient();

    public ProductSteps(
            WorldContext world
    ) {

        this.world = world;
    }

    @Given(
            "the ShopKart home page is open"
    )
    public void shopKartHomePageIsOpen() {

        HomePage homePage =
                new HomePage()
                        .openPage();

        world.setHomePage(
                homePage
        );
    }

    @When(
            "the customer searches for {string}"
    )
    public void customerSearchesFor(
            String searchText
    ) {

        world
                .getHomePage()
                .searchFor(searchText);
    }

    @Then(
            "the UI shows product {string}"
    )
    public void uiShowsProduct(
            String productName
    ) {

        world
                .getHomePage()
                .product(productName)
                .shouldBeVisible();
    }

    @Then(
            "GET products API for {string} returns SKU {string} with name {string}"
    )
    public void apiReturnsProduct(
            String query,
            String expectedSku,
            String expectedName
    ) {

        Response response =
                productClient.search(query);

        assertEquals(
                200,
                response.statusCode(),
                "Product API should return HTTP 200"
        );

        List<Map<String, Object>> products =
                response
                        .jsonPath()
                        .getList("$");

        assertFalse(
                products.isEmpty(),
                "Product API returned an empty product list"
        );

        boolean productFound =
                products
                        .stream()
                        .anyMatch(
                                product ->
                                        expectedSku.equals(
                                                product.get("sku")
                                        )
                                                &&
                                                expectedName.equals(
                                                        product.get("name")
                                                )
                        );

        assertTrue(
                productFound,
                "Expected product was not returned by Product API"
        );
    }

    @Given(
            "she adds {int} x {string} to her cart"
    )
    public void sheAddsProductToCart(
            int quantity,
            String sku
    ) {

        String productName =
                productNameFor(sku);

        world.setProductSku(sku);

        world.setProductName(
                productName
        );

        world
                .getHomePage()
                .searchFor(productName);

        for (
                int index = 0;
                index < quantity;
                index++
        ) {

            world
                    .getHomePage()
                    .product(productName)
                    .shouldBeVisible()
                    .addToCart();
        }

        world.setCartPage(
                world
                        .getHomePage()
                        .header()
                        .openCart()
        );
    }

    private String productNameFor(
            String sku
    ) {

        return switch (sku) {

            case "SKU-BAG" ->
                    "Metro Carryall";

            case "SKU-PEN" ->
                    "Precision Pen Set";

            case "SKU-MUG" ->
                    "Studio Ceramic Mug";

            case "SKU-CAP" ->
                    "Everyday Cap";

            default ->
                    throw new IllegalArgumentException(
                            "Unknown SKU: "
                                    + sku
                    );
        };
    }
}