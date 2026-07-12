package com.shopkart.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.shopkart.ui.locators.CartLocators.CART_LINE;
import static com.shopkart.ui.locators.CartLocators.CART_TOTAL;
import static com.shopkart.ui.locators.CartLocators.CHECKOUT;

public class CartPage {

    private SelenideElement cartLine(
            String sku
    ) {

        String xpath =
                String.format(
                        CART_LINE,
                        sku
                );

        return $x(xpath);
    }

    public CartPage shouldContainProduct(
            String sku
    ) {

        cartLine(sku)
                .shouldBe(Condition.visible);

        return this;
    }

    public CartPage shouldShowCartTotal() {

        $x(CART_TOTAL)
                .shouldBe(Condition.visible);

        return this;
    }

    public CheckoutPage checkout() {

        $x(CHECKOUT)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .click();

        return new CheckoutPage();
    }
}