package com.shopkart.ui.locators;

public final class OrderLocators {

    private OrderLocators() {
    }

    public static final String ORDER_NUMBER =
            "//section[contains(@class,'order-confirmation')]//p";

    public static final String ORDER_STATUS =
            "//dd[@data-field='order-status']";

    public static final String ORDER_TOTAL =
            "//dd[@data-field='order-total']";

    public static final String DELIVERY_ADDRESS =
            "//dt[normalize-space()='Delivery address']/following-sibling::dd";

    public static final String RETURN_TO_CATALOG =
            "//button[normalize-space()='Return to catalog']";
}