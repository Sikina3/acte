package com.projetanais.acte;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Parents {
    private int id;
    private String nomPere, nomMere, dnPere, dnMere, lnPere, lnMere, profPere, profMere, adresse;

    public Parents(String nomPere, String nomMere, String dnPere, String dnMere, String lnPere, String lnMere,
            String profPere, String profMere, String adresse) {
        this.nomPere = nomPere;
        this.nomMere = nomMere;
        this.dnPere = dnPere;
        this.dnMere = dnMere;
        this.lnPere = lnPere;
        this.lnMere = lnMere;
        this.profPere = profPere;
        this.profMere = profMere;
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomPere() {
        return nomPere;
    }

    public String getNomMere() {
        return nomMere;
    }

    public String getDnPere() {
        return dnPere;
    }

    public String getDnMere() {
        return dnMere;
    }

    public String getLnPere() {
        return lnPere;
    }

    public String getLnMere() {
        return lnMere;
    }

    public String getProfPere() {
        return profPere;
    }

    public String getProfMere() {
        return profMere;
    }

    public String getAdresse() {
        return adresse;
    }

    public void sauvegarder() {
        try (Connection connection = DatabaseConnection.getConnect()) {
            String insertSQL = "INSERT INTO parents (nom_pere, nom_mere, dn_pere, dn_mere, ln_pere, ln_mere, prof_pere, prof_mere, adresse) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertSQL,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, nomPere);
            statement.setString(2, nomMere);
            ConversionMois conv = new ConversionMois();

            Date sqlPere = Date.valueOf(conv.ConversionMois(dnPere));
            Date sqlMere = Date.valueOf(conv.ConversionMois(dnMere));

            statement.setDate(3, sqlPere);
            statement.setDate(4, sqlMere);
            statement.setString(5, lnPere);
            statement.setString(6, lnMere);
            statement.setString(7, profPere);
            statement.setString(8, profMere);
            statement.setString(9, adresse);
            statement.executeUpdate();

            try (ResultSet idGenerer = statement.getGeneratedKeys()) {
                if (idGenerer.next()) {
                    this.id = idGenerer.getInt(1);
                } else {
                    throw new SQLException("Creation de parents echouer.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'enregistrement des parents : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void modifier(int id) {
        String updateSQL = "UPDATE parents SET nom_pere = ?, nom_mere = ?, dn_pere = ?, dn_mere = ?, ln_pere = ?, ln_mere = ?, prof_pere = ?, prof_mere = ?, adresse = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnect();
             PreparedStatement statement = connection.prepareStatement(updateSQL)) {

            ConversionMois conv = new ConversionMois();
            Date sqlDnPere = Date.valueOf(conv.ConversionMois(dnPere));
            Date sqlDnMere = Date.valueOf(conv.ConversionMois(dnMere));

            statement.setString(1, nomPere);
            statement.setString(2, nomMere);
            statement.setDate(3, sqlDnPere);
            statement.setDate(4, sqlDnMere);
            statement.setString(5, lnPere);
            statement.setString(6, lnMere);
            statement.setString(7, profPere);
            statement.setString(8, profMere);
            statement.setString(9, adresse);
            statement.setInt(10, id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Mise à jour des parents échouée, aucune ligne affectée.");
            } else {
                System.out.println("id parents: " + id);
                System.out.println("tsy misy erreur (nen'i parent)");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour des parents : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Parents chargerParId(int id) {
        String query = "SELECT * FROM parents WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnect();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Parents(
                        resultSet.getString("nom_pere"),
                        resultSet.getString("nom_mere"),
                        resultSet.getString("dn_pere"),
                        resultSet.getString("dn_mere"),
                        resultSet.getString("ln_pere"),
                        resultSet.getString("ln_mere"),
                        resultSet.getString("prof_pere"),
                        resultSet.getString("prof_mere"),
                        resultSet.getString("adresse")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
