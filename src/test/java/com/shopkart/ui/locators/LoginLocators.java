package com.shopkart.ui.locators;

public final class LoginLocators {

    private LoginLocators() {
    }

    public static final String EMAIL =
            "//input[@id='email']";

    public static final String PASSWORD =
            "//input[@id='password']";

    public static final String SIGN_IN =
            "//button[@type='submit' and normalize-space()='Sign in']";

    public static final String ERROR_MESSAGE =
            "//div[@role='alert']";
}