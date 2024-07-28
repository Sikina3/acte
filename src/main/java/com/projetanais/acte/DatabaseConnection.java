package com.projetanais.acte;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/kopi";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection connection = getConnect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM amis");

            while (resultSet.next()) {
                String nom = resultSet.getString("nom");
                System.out.println("Nom de l'ami : " + nom);
            }
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
