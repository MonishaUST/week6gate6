package com.shopkart.config;

public final class Env {

    private Env() {
    }

    public static String current() {

        return System.getProperty(
                "test.env",
                "local"
        );
    }
}