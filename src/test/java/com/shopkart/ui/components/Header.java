package com.shopkart.ui.components;

import com.codeborne.selenide.Condition;
import com.shopkart.ui.pages.CartPage;
import com.shopkart.ui.pages.LoginPage;

import static com.codeborne.selenide.Selenide.$x;
import static com.shopkart.ui.locators.HeaderLocators.CART;
import static com.shopkart.ui.locators.HeaderLocators.SIGN_IN;

public class Header {

    public LoginPage clickSignIn() {

        $x(SIGN_IN)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .click();

        return new LoginPage();
    }

    public CartPage openCart() {

        $x(CART)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .click();

        return new CartPage();
    }
}