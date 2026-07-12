package com.shopkart.data.db;

import com.shopkart.data.secret.Secrets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class PostgresSupport {

    private PostgresSupport() {
    }

    public static Connection getConnection() {

        String host =
                System.getProperty(
                        "db.host",
                        "localhost"
                );

        String port =
                System.getProperty(
                        "db.port",
                        "3306"
                );

        String database =
                System.getProperty(
                        "db.name",
                        "shopkart"
                );

        String user =
                System.getProperty(
                        "db.user",
                        "root"
                );

        String password =
                Secrets.get(
                        "DB_PASSWORD",
                        "db.password"
                );

        String jdbcUrl =
                "jdbc:mysql://"
                        + host
                        + ":"
                        + port
                        + "/"
                        + database;

        try {

            return DriverManager.getConnection(
                    jdbcUrl,
                    user,
                    password
            );

        } catch (SQLException exception) {

            throw new IllegalStateException(
                    "Unable to connect to ShopKart database",
                    exception
            );
        }
    }
}