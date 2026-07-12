package com.shopkart.ui.locators;

public final class Xp {

    private Xp() {
    }

    // LOGIN PAGE

    public static final String EMAIL =
            "//input[@name='email']";

    public static final String PASSWORD =
            "//input[@name='password']";

    public static final String SIGN_IN =
            "//button[normalize-space()='Sign in']";


    // HOME PAGE

    public static final String PRODUCT =
            "//div[contains(@class,'product')]"
                    + "[.//*[normalize-space()='%s']]";

    public static final String ADD_TO_CART =
            PRODUCT
                    + "//button[normalize-space()='Add to cart']";


    // CART PAGE

    public static final String CART_LINE =
            "//tr[contains(@class,'cart-line')]"
                    + "[td[normalize-space()='%s']]";

    public static final String LINE_TOTAL =
            ".//td[@class='line-total']";

    public static final String CART_TOTAL =
            "//*[@data-role='cart-total']";

    public static final String CHECKOUT =
            "//button[normalize-space()='Checkout']";


    // CHECKOUT PAGE

    public static final String ADDRESS =
            "//textarea[@name='address']";

    public static final String PLACE_ORDER =
            "//button[normalize-space()='Place order']";


    // ORDER PAGE

    public static final String ORDER_STATUS =
            "//*[@data-field='order-status']";

    public static final String ORDER_TOTAL =
            "//*[@data-field='order-total']";


    // HEADER

    public static final String CART_LINK =
            "//a[contains(@href,'/cart')]";


    public static String product(
            String productName
    ) {

        return String.format(
                PRODUCT,
                productName
        );
    }


    public static String addToCart(
            String productName
    ) {

        return String.format(
                ADD_TO_CART,
                productName
        );
    }


    public static String cartLine(
            String sku
    ) {

        return String.format(
                CART_LINE,
                sku
        );
    }
}
