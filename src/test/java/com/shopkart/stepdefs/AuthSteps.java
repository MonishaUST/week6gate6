package com.shopkart.stepdefs;

import com.shopkart.support.WorldContext;
import com.shopkart.ui.pages.HomePage;
import io.cucumber.java.en.Given;

public class AuthSteps {

    private final WorldContext world;

    public AuthSteps(WorldContext world) {
        this.world = world;
    }

    @Given("{string} is logged in")
    public void personaIsLoggedIn(
            String persona
    ) {

        HomePage homePage =
                new HomePage()
                        .openPage();

        homePage
                .header()
                .clickSignIn()
                .loginAs(persona);

        world.setHomePage(homePage);
    }
}