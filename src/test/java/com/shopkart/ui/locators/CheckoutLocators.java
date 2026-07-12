package com.shopkart.ui.locators;

public final class CheckoutLocators {

    private CheckoutLocators() {
    }

    public static final String ADDRESS =
            "//textarea[@id='address']";

    public static final String PLACE_ORDER =
            "//button[@type='submit' and normalize-space()='Place order']";
}