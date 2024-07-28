package com.projetanais.acte;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Acte {
    private int id, id_enfants;
    private String dateCreation, heure, matinSoir, nomDocteur, nomResponsable, distric, commune;

    public Acte(int id_enfants, String dateCreation, String heure, String matinSoir, String nomDocteur,
            String nomResponsable, String distric, String commune) {
        this.id_enfants = id_enfants;
        this.dateCreation = dateCreation;
        this.heure = heure;
        this.nomResponsable = nomResponsable;
        this.nomDocteur = nomDocteur;
        this.distric = distric;
        this.commune = commune;
        this.matinSoir = matinSoir;
    }

    public int getId() {
        return id;
    }

    public int getId_enfants() {
        return id_enfants;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public String getHeure() {
        return heure;
    }

    public String getMatinSoir() {
        return matinSoir;
    }

    public String getNomDocteur() {
        return nomDocteur;
    }

    public String getNomResponsable() {
        return nomResponsable;
    }

    public String getDistric() {
        return distric;
    }

    public String getCommune() {
        return commune;
    }

    public void sauvegarder() {
        String query = "INSERT INTO acte (id_enfants, date_creation, heure, matin_soir, nom_docteur, nom_responsable, distric, commune) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnect();
                PreparedStatement statement = connection.prepareStatement(query,
                        PreparedStatement.RETURN_GENERATED_KEYS)) {
            ConversionMois conv = new ConversionMois();

            Date sqlDate = Date.valueOf(conv.ConversionMois(dateCreation));
            Time sqlHeure = convertirHeure(heure);

            statement.setInt(1, id_enfants);
            statement.setDate(2, sqlDate);
            statement.setTime(3, sqlHeure);
            statement.setString(4, matinSoir);
            statement.setString(5, nomDocteur);
            statement.setString(6, nomResponsable);
            statement.setString(7, distric);
            statement.setString(8, commune);

            statement.executeUpdate();

            try (ResultSet idGenerer = statement.getGeneratedKeys()) {
                if (idGenerer.next()) {
                    this.id = idGenerer.getInt(1);
                } else {
                    throw new SQLException("Creating acte failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Time convertirHeure(String heure) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH'h'mm");
            LocalTime localTime = LocalTime.parse(heure, formatter);
            return Time.valueOf(localTime);
        } catch (DateTimeParseException e) {
            System.out.println("Erreur de format d'heure : " + e.getMessage());
            return null;
        }
    }
}
