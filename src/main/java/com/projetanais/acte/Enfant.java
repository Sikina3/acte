package com.projetanais.acte;

public class Enfant {
    private int id;
    private String dateNaissance;
    private String nom;
    private String prenom;
    private String lieuNaissance;
    private String nomParent;
    private String sexe;

    public Enfant(int id, String dateNaissance, String nom, String prenom, String lieuNaissance, String nomParent, String sexe) {
        this.id = id;
        this.dateNaissance = dateNaissance;
        this.nom = nom;
        this.prenom = prenom;
        this.lieuNaissance = lieuNaissance;
        this.nomParent = nomParent;
        this.sexe = sexe;
    }

    public int getId() { return id; }
    public String getDateNaissance() { return dateNaissance; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getLieuNaissance() { return lieuNaissance; }
    public String getNomParent() { return nomParent; }
    public String getSexe() { return sexe; }
}