package com.shopkart.ui.components;

import com.codeborne.selenide.SelenideElement;
import com.shopkart.ui.locators.Xp;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ProductCard {

    private final String productName;

    public ProductCard(
            String productName
    ) {

        this.productName =
                productName;
    }

    private SelenideElement productElement() {

        return $x(
                Xp.product(productName)
        );
    }

    public ProductCard shouldBeVisible() {

        productElement()
                .shouldBe(visible);

        return this;
    }

    public ProductCard addToCart() {

        $x(
                Xp.addToCart(productName)
        )
                .shouldBe(visible)
                .click();

        return this;
    }
}