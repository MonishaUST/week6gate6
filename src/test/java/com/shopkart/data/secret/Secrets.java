package com.shopkart.data.secret;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public final class Secrets {

    private static final Properties LOCAL_SECRETS =
            new Properties();

    static {

        Path secretFile =
                Path.of(
                        "secrets.local.properties"
                );

        if (Files.exists(secretFile)) {

            try (InputStream input =
                         Files.newInputStream(secretFile)) {

                LOCAL_SECRETS.load(input);

            } catch (IOException exception) {

                throw new IllegalStateException(
                        "Unable to load local secrets file",
                        exception
                );
            }
        }
    }

    private Secrets() {
    }

    public static String get(
            String environmentKey,
            String localKey
    ) {

        String value =
                System.getenv(environmentKey);

        if (isMissing(value)) {

            value =
                    System.getProperty(
                            environmentKey
                    );
        }

        if (isMissing(value)) {

            value =
                    LOCAL_SECRETS.getProperty(
                            localKey
                    );
        }

        if (isMissing(value)) {

            throw new IllegalStateException(
                    "Missing required secret. "
                            + "Environment key: "
                            + environmentKey
                            + ", local key: "
                            + localKey
            );
        }

        return value;
    }

    public static String passwordFor(
            String persona
    ) {

        return switch (
                persona.toLowerCase()
                ) {

            case "alice" ->
                    get(
                            "SHOPKART_ALICE_PASSWORD",
                            "alice.password"
                    );

            case "bob" ->
                    get(
                            "SHOPKART_BOB_PASSWORD",
                            "bob.password"
                    );

            case "carol" ->
                    get(
                            "SHOPKART_CAROL_PASSWORD",
                            "carol.password"
                    );

            default ->
                    throw new IllegalArgumentException(
                            "Unknown persona: "
                                    + persona
                    );
        };
    }

    private static boolean isMissing(
            String value
    ) {

        return value == null
                || value.isBlank();
    }
}