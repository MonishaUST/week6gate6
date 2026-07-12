package com.shopkart.support;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseUiTest {

    @BeforeAll
    static void configureSelenide() {

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

        Configuration.screenshots =
                true;

        Configuration.savePageSource =
                true;

        System.out.println(
                "SHOPKART UI BASE URL = "
                        + Configuration.baseUrl
        );
    }
}