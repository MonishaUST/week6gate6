package com.shopkart.ui.locators;

public final class HeaderLocators {

    private HeaderLocators() {
    }

    public static final String BRAND =
            "//button[@aria-label='ShopKart home']";

    public static final String CATALOG =
            "//nav[@aria-label='Primary navigation']//button[normalize-space()='Catalog']";

    public static final String CART =
            "//nav[@aria-label='Primary navigation']//button[normalize-space()='Cart']";

    public static final String SIGN_IN =
            "//nav[@aria-label='Primary navigation']//button[normalize-space()='Sign in']";

    public static final String LOGGED_IN_USER =
            "//span[@class='signed-in']";

    public static final String SIGN_OUT =
            "//button[@aria-label='Sign out']";
}
