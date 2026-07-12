package com.shopkart.ui.pages;

import com.codeborne.selenide.Condition;
import com.shopkart.data.secret.Secrets;

import static com.codeborne.selenide.Selenide.$x;
import static com.shopkart.ui.locators.LoginLocators.EMAIL;
import static com.shopkart.ui.locators.LoginLocators.PASSWORD;
import static com.shopkart.ui.locators.LoginLocators.SIGN_IN;

public class LoginPage {

    public LoginPage enterEmail(String email) {

        $x(EMAIL)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.editable)
                .setValue(email);

        return this;
    }

    public LoginPage enterPassword(String password) {

        $x(PASSWORD)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.editable)
                .setValue(password);

        return this;
    }

    public HomePage clickSignIn() {

        $x(SIGN_IN)
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .click();

        return new HomePage();
    }

    public HomePage loginAs(String persona) {

        String email = emailFor(persona);
        String password = Secrets.passwordFor(persona);

        enterEmail(email);
        enterPassword(password);

        return clickSignIn();
    }

    private String emailFor(String persona) {

        return switch (persona.toLowerCase()) {

            case "alice" ->
                    "alice@shopkart.test";

            case "bob" ->
                    "bob@shopkart.test";

            case "carol" ->
                    "carol@shopkart.test";

            default ->
                    throw new IllegalArgumentException(
                            "Unknown persona: " + persona
                    );
        };
    }
}