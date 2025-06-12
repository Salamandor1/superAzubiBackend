package de.cancom.super_azubi_pets.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import de.cancom.super_azubi_pets.DatabaseConnection;

/**
 * Represents an animal in the Super Azubi Pets game.
 * This class retrieves animal data from the database based on the provided ID.
 */
public class Animal {

    private String animalName;
    private int hearts;
    private int attack;
    private String ability;
    private int id;


    /**
     * Creates the animal requested by ID by retrieving the data such as name etc. from the database
     * 
     * @param id ID of the animal to be loaded could, for example, be randomly generated beforehand
     * @throws SQLException f something goes wrong in the database, e.g. if the ID does not exist
     */
    public Animal(int id) throws SQLException{

        this.id = id;
        String sql = "SELECT * FROM animals WHERE id = ?";

        //uses the DatabaseConnection class to get a connection to the database
        try(Connection connection = DatabaseConnection.getConnection()){
            
            // Prepare the SQL statement to prevent SQL injection
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                this.animalName = rs.getString("name");
                this.hearts = rs.getInt("hearts");
                this.attack = rs.getInt("attack");
            } else {
                throw new SQLException("Animal with ID " + id + " not found.");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }



     //Getter-Setter methods for the attributes
    public String getAnimalName() {
        return animalName;
    }
    public int getHearts() {
        return hearts;
    }
    public int getAttack() {
        return attack;
    }
    public String getAbility() {
        return ability;
    }
    public int getId() {
        return id;
    }
}