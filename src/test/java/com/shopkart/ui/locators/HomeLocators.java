package com.shopkart.ui.locators;

public final class HomeLocators {

    private HomeLocators() {
    }

    public static final String SEARCH_BOX =
            "//input[@id='catalog-search']";

    public static final String SEARCH_BUTTON =
            "//button[@type='submit' and normalize-space()='Search']";

    public static final String PRODUCT_CARD =
            "//div[contains(@class,'product-card')]";

    public static final String PRODUCT_BY_NAME =
            "//div[contains(@class,'product-card')][.//h2/button[normalize-space()='%s']]";
}