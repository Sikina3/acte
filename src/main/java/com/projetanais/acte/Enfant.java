package com.projetanais.acte;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TableView;

public class Enfant {
    private int id, idParents;
    private String nom, prenom, dateNaissance, lieuNaissance, heureNaissance, matinSoir, sexe;

    public Enfant(int idParents, String nom, String prenom, String dateNaissance, String lieuNaissance, String heureNaissance, String matinSoir, String sexe) {
        this.idParents = idParents;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.heureNaissance = heureNaissance;
        this.matinSoir = matinSoir;
        this.sexe = sexe;
    }

    private String nomsParents;

    public String getNomsParents() {
        return nomsParents;
    }

    public void setNomsParents(String nomsParents) {
        this.nomsParents = nomsParents;
    }

    public int getId() {
        return id;
    }

    public int getIdParents() {
        return idParents;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public String getHeureNaissance() {
        return heureNaissance;
    }

    public String getMatinSoir() {
        return matinSoir;
    }

    public String getSexe() {
        return sexe;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdParents(int idParents) {
        this.idParents = idParents;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public void setHeureNaissance(String heureNaissance) {
        this.heureNaissance = heureNaissance;
    }

    public void setMatinSoir(String matinSoir) {
        this.matinSoir = matinSoir;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void sauvegarder() {
        try (Connection connection = DatabaseConnection.getConnect()) {
            String insertSQL = "INSERT INTO enfant (id_parents, nom_enfant, prenom_enfant, date_naissance, lieu_naissance, heure_naissance, matin_soir, sexe) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertSQL,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, idParents);
            statement.setString(2, nom);
            statement.setString(3, prenom);

            ConversionMois conv = new ConversionMois();

            Date sqlDateN = Date.valueOf(conv.ConversionMois(dateNaissance));
            Time sqlHeureN = convertirHeure(heureNaissance);

            statement.setDate(4, sqlDateN);
            statement.setString(5, lieuNaissance);
            statement.setTime(6, sqlHeureN);
            statement.setString(7, matinSoir);
            statement.setString(8, sexe);
            statement.executeUpdate();

            try (ResultSet idGenerer = statement.getGeneratedKeys()) {
                if (idGenerer.next()) {
                    this.id = idGenerer.getInt(1);
                } else {
                    throw new SQLException("Creation de l'enfant echouer.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'enregistrement de l'enfant : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<Enfant> getAllEnfants() {
        List<Enfant> enfants = new ArrayList<>();
        String query = "SELECT e.id, e.id_parents, e.nom_enfant, e.prenom_enfant, e.date_naissance, e.lieu_naissance, e.heure_naissance, e.matin_soir, e.sexe, CONCAT(p.nom_pere, ' et ', p.nom_mere) AS noms_parents "
                +
                "FROM enfant e " +
                "JOIN parents p ON e.id_parents = p.id";
        try (Connection connection = DatabaseConnection.getConnect();
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Enfant enfant = new Enfant(
                        resultSet.getInt("id_parents"),
                        resultSet.getString("nom_enfant"),
                        resultSet.getString("prenom_enfant"),
                        resultSet.getString("date_naissance"),
                        resultSet.getString("lieu_naissance"),
                        resultSet.getString("heure_naissance"),
                        resultSet.getString("matin_soir"),
                        resultSet.getString("sexe"));
                enfant.setId(resultSet.getInt("id"));
                // Ajout du nom des parents dans l'objet Enfant
                enfant.setNomsParents(resultSet.getString("noms_parents"));
                enfants.add(enfant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enfants;
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

    public static int cliquerEnfant(TableView<Enfant> tableView) {
        Enfant enfantSelectionne = tableView.getSelectionModel().getSelectedItem();
        if (enfantSelectionne != null) {
            return enfantSelectionne.getId();
        } else {
            return -1; // ou une autre valeur par défaut pour indiquer qu'aucun enfant n'a été sélectionné
        }
    }
}
