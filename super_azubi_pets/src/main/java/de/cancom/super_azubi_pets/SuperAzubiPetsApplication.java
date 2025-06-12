package de.cancom.super_azubi_pets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.cancom.super_azubi_pets.Models.Animal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;


@SpringBootApplication
public class SuperAzubiPetsApplication {


    /**
     * Testklasse, fügt Tiere zur Datenbank hinzu
     * @param args
     * @throws SQLException
     */
	public static void main(String[] args) throws SQLException {
		//SpringApplication.run(SuperAzubiPetsApplication.class, args);

		try (Connection connection = DatabaseConnection.getConnection()) {


            System.out.println("Verbindung zur PostgreSQL-Datenbank hergestellt.");

			insertAnimal(connection, "Max", 10, 3, "Flying");
			insertAnimal(connection, "Elisa", 30, 15, "Flying");
			insertAnimal(connection, "Tobi", 14, 22, "Flying");
		}catch (SQLException e) {
            System.err.println("Datenbankfehler: " + e.getMessage());
            e.printStackTrace();
        }



        Animal test = new Animal(1);
        System.out.println("Animal Name: " + test.getAnimalName());
        System.out.println("Hearts: " + test.getHearts());
        System.out.println("Attack: " + test.getAttack());


	}

	private static int insertAnimal(Connection conn, String name, int hearts, int attack, String ability) throws SQLException {
        String sql = "INSERT INTO animals (name, hearts, attack, ability) VALUES (?, ?, ?, ?) RETURNING id";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setInt(2, hearts);
            stmt.setInt(3, attack);
            stmt.setString(4, ability);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                throw new SQLException("Kein ID zurückgegeben");
            }
        }
    }

}
