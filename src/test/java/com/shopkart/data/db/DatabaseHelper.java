package com.shopkart.data.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class DatabaseHelper {

    private DatabaseHelper() {
    }

    public static int queryForInt(
            String sql,
            Object... parameters
    ) {

        try (
                Connection connection =
                        PostgresSupport.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            setParameters(
                    statement,
                    parameters
            );

            try (
                    ResultSet resultSet =
                            statement.executeQuery()
            ) {

                if (!resultSet.next()) {

                    throw new IllegalStateException(
                            "Database query returned no result"
                    );
                }

                return resultSet.getInt(1);
            }

        } catch (SQLException exception) {

            throw new IllegalStateException(
                    "Database query failed",
                    exception
            );
        }
    }

    private static void setParameters(
            PreparedStatement statement,
            Object... parameters
    ) throws SQLException {

        for (
                int index = 0;
                index < parameters.length;
                index++
        ) {

            statement.setObject(
                    index + 1,
                    parameters[index]
            );
        }
    }
}