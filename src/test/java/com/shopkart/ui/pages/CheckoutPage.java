package com.shopkart.ui.pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;
import static com.shopkart.ui.locators.CheckoutLocators.ADDRESS;
import static com.shopkart.ui.locators.CheckoutLocators.PLACE_ORDER;

public class CheckoutPage {

    public CheckoutPage enterAddress(
            String address
    ) {

        $x(ADDRESS)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.editable)
                .setValue(address);

        return this;
    }

    public OrderPage placeOrder() {

        $x(PLACE_ORDER)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .click();

        return new OrderPage();
    }
}