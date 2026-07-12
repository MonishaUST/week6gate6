package com.shopkart.support;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.shopkart.data.db.FlywaySupport;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    private static boolean databaseMigrated = false;

    @Before(order = 0)
    public void prepareCiDatabase() {

        String environment =
                System.getProperty(
                        "test.env",
                        "local"
                );

        if (
                "ci".equalsIgnoreCase(environment)
                        && !databaseMigrated
        ) {

            FlywaySupport.migrate();

            databaseMigrated = true;
        }
    }

    @Before(
            value = "@ui or @e2e",
            order = 1
    )
    public void configureUi() {

        Configuration.baseUrl =
                System.getProperty(
                        "baseUrl",
                        "http://localhost:8080"
                );

        Configuration.browser =
                System.getProperty(
                        "browser",
                        "chrome"
                );

        Configuration.headless =
                Boolean.parseBoolean(
                        System.getProperty(
                                "headless",
                                "false"
                        )
                );

        Configuration.timeout =
                10_000;

        Configuration.browserSize =
                "1440x900";
    }

    @After("@ui or @e2e")
    public void closeBrowser() {

        Selenide.closeWebDriver();
    }
}