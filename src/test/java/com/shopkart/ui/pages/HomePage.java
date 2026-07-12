package com.shopkart.ui.pages;

import com.codeborne.selenide.Condition;
import com.shopkart.ui.components.Header;
import com.shopkart.ui.components.ProductCard;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static com.shopkart.ui.locators.HomeLocators.SEARCH_BOX;
import static com.shopkart.ui.locators.HomeLocators.SEARCH_BUTTON;

public class HomePage {

    private final Header header =
            new Header();

    public HomePage openPage() {

        open("/");

        return this;
    }

    public Header header() {

        return header;
    }

    public HomePage searchFor(String productName) {

        $x(SEARCH_BOX)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.editable)
                .setValue(productName);

        $x(SEARCH_BUTTON)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .click();

        return this;
    }

    public ProductCard product(String productName) {

        return new ProductCard(productName);
    }
}