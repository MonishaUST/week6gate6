package com.shopkart.data.db;

import com.shopkart.data.secret.Secrets;
import org.flywaydb.core.Flyway;

public final class FlywaySupport {

    private FlywaySupport() {
    }

    public static void migrate() {

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

        Flyway flyway =
                Flyway.configure()
                        .dataSource(
                                jdbcUrl,
                                user,
                                password
                        )
                        .locations(
                                "classpath:db/migration"
                        )
                        .load();

        flyway.migrate();
    }
}