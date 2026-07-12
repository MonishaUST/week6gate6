package com.shopkart.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class AppConfig {

    private static final Properties PROPERTIES =
            new Properties();

    static {

        String configFile =
                "config/application-"
                        + Env.current()
                        + ".properties";

        try (InputStream input =
                     AppConfig.class
                             .getClassLoader()
                             .getResourceAsStream(configFile)) {

            if (input == null) {

                throw new IllegalStateException(
                        "Configuration file not found: "
                                + configFile
                );
            }

            PROPERTIES.load(input);

        } catch (IOException exception) {

            throw new IllegalStateException(
                    "Unable to load configuration: "
                            + configFile,
                    exception
            );
        }
    }

    private AppConfig() {
    }

    public static String baseUrl() {

        return PROPERTIES.getProperty(
                "app.base.url"
        );
    }

    public static String apiBaseUrl() {

        return PROPERTIES.getProperty(
                "api.base.url"
        );
    }

    public static String environment() {

        return PROPERTIES.getProperty(
                "environment"
        );
    }
}
