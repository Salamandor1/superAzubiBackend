package de.cancom.super_azubi_pets;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {


    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "user";
    private static final String PASSWORD = "1234";


    private static Connection connection;

      public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("org.postgresql.Driver"); // optional ab Java 6+
                connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new SQLException("PostgreSQL JDBC-Treiber nicht gefunden.");
            }
        }
        return connection;
    }
}
