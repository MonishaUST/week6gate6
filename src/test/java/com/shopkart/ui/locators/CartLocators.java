package com.shopkart.ui.locators;

public final class CartLocators {

    private CartLocators() {
    }

    public static final String CART_LINE =
            "//tr[contains(@class,'cart-line')][td[normalize-space()='%s']]";

    public static final String LINE_TOTAL =
            ".//td[@class='line-total']";

    public static final String CART_TOTAL =
            "//strong[@data-role='cart-total']";

    public static final String CONTINUE_SHOPPING =
            "//button[normalize-space()='Continue shopping']";

    public static final String CHECKOUT =
            "//button[normalize-space()='Checkout']";
}