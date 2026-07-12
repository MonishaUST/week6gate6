package com.shopkart.support;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before("@ui or @e2e")
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

        String ci =
                System.getenv("CI");

        boolean runningInCi =
                ci != null
                        && ci.equalsIgnoreCase("true");

        Configuration.headless =
                Boolean.parseBoolean(
                        System.getProperty(
                                "headless",
                                String.valueOf(runningInCi)
                        )
                );

        Configuration.timeout = 10_000;

        Configuration.browserSize =
                "1440x900";

        if (runningInCi) {

            Configuration.browserCapabilities
                    .setCapability(
                            "goog:chromeOptions",
                            chromeOptions()
                    );
        }

        System.out.println(
                "Running in CI: "
                        + runningInCi
        );

        System.out.println(
                "Headless mode: "
                        + Configuration.headless
        );
    }

    private org.openqa.selenium.chrome.ChromeOptions
    chromeOptions() {

        org.openqa.selenium.chrome.ChromeOptions options =
                new org.openqa.selenium.chrome.ChromeOptions();

        options.addArguments(
                "--headless=new",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--disable-gpu",
                "--window-size=1440,900"
        );

        return options;
    }

    @After("@ui or @e2e")
    public void closeBrowser() {

        Selenide.closeWebDriver();
    }
}