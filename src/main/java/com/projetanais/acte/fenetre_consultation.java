package com.projetanais.acte;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class fenetre_consultation {
    private Scene scene;
    private Stage stage;
    private BorderPane root;
    private int i = 0;

    public fenetre_consultation() {
        root = new BorderPane();
        initialisation_Menu();
        root.setCenter(page_de_consultation());
    }

    public void start(Stage stage) {
        this.stage = stage;
        scene = new Scene(root, 1000, 700);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("style_consu.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Consultation de la liste");
        stage.setResizable(false);
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("teste.jpg")));

        stage.show();
    }

    private void initialisation_Menu() {
        icon_menu = new Image(getClass().getResourceAsStream("menuB.png"));
        icon_ajout = new Image(getClass().getResourceAsStream("creer.png"));
        icon_consu = new Image(getClass().getResourceAsStream("visualisation-de-donnees.png"));
        icon_accueil = new Image(getClass().getResourceAsStream("maison.png"));
        icon_param = new Image(getClass().getResourceAsStream("parametres.png"));

        image_menu = new ImageView(icon_menu);
        image_menu.setOpacity(0.6);
        image_ajout = new ImageView(icon_ajout);
        image_ajout.setOpacity(0.4);
        image_consu = new ImageView(icon_consu);
        image_consu.setOpacity(0.4);
        image_modif = new ImageView(icon_accueil);
        image_modif.setOpacity(0.4);
        image_param = new ImageView(icon_param);
        image_param.setOpacity(0.4);

        // gauche
        image_ajout.setFitWidth(24);
        image_ajout.setFitHeight(24);
        image_consu.setFitWidth(24);
        image_consu.setFitHeight(24);
        image_modif.setFitWidth(24);
        image_modif.setFitHeight(24);
        image_param.setFitWidth(24);
        image_param.setFitHeight(24);

        // haut
        image_menu.setFitWidth(20);
        image_menu.setFitHeight(20);
        image_menu.setId("image_menu");

        bar = new VBox();
        bar.setId("bar-left");
        bar.setPrefWidth(40);
        bar.setAlignment(Pos.CENTER);
        bar.setSpacing(60);

        menu_ajout = new Label("Ajouter");
        menu_consu = new Label("Consulter");
        menu_accueil = new Label("Modifier");
        menu_param = new Label("Paramètres");

        // classe
        menu_ajout.getStyleClass().add("label-menu");
        menu_consu.getStyleClass().add("label-menu");
        menu_accueil.getStyleClass().add("label-menu");
        menu_param.getStyleClass().add("label-menu");

        HBox hboxAjout = new HBox(image_ajout, menu_ajout);
        hboxAjout.setPrefHeight(50);
        hboxAjout.setSpacing(20);

        HBox hboxConsu = new HBox(image_consu, menu_consu);
        hboxConsu.setSpacing(20);
        hboxConsu.setPrefHeight(50);

        HBox hbox_accueil = new HBox(image_modif, menu_accueil);
        hbox_accueil.setSpacing(20);
        hbox_accueil.setPrefHeight(50);

        HBox hboxParam = new HBox(image_param, menu_param);
        hboxParam.setSpacing(20);
        hboxParam.setPrefHeight(50);

        menu_ajout.setVisible(false);
        menu_consu.setVisible(false);
        menu_accueil.setVisible(false);
        menu_param.setVisible(false);

        hboxAjout.setOnMouseClicked(event -> {
            nouvelle_enfant ajouter = new nouvelle_enfant();
            Stage stage_ajout = new Stage();
            ajouter.start(stage_ajout);
            stage.close();
        });

        hbox_accueil.setOnMouseClicked(event -> {
            App accueil = new App();
            Stage stage_accueil = new Stage();
            accueil.start(stage_accueil);
            stage.close();
        });

        menu_icon = new Label();
        menu_icon.setGraphic(image_menu);
        menu_icon.setId("menu_icon");
        menu_icon.setOnMouseClicked(event -> {
            if (i == 0) {
                bar.setPrefWidth(200);
                menu_ajout.setVisible(true);
                menu_consu.setVisible(true);
                menu_accueil.setVisible(true);
                menu_param.setVisible(true);
                menu_icon.setStyle("-fx-translate-x: -50px;");
                i = 1;
            } else if (i == 1) {
                bar.setPrefWidth(70);
                menu_ajout.setVisible(false);
                menu_consu.setVisible(false);
                menu_accueil.setVisible(false);
                menu_param.setVisible(false);
                menu_icon.setStyle("-fx-translate-x: 0;");
                i = 0;
            }
        });

        bar.getChildren().addAll(menu_icon, hbox_accueil, hboxAjout, hboxConsu, hboxParam);

        root.setLeft(bar);
    }

    private VBox page_de_consultation() {
        VBox box_consultation = new VBox();
        box_consultation.setSpacing(10);
        box_consultation.setPadding(new Insets(20));

        HBox filterBox = new HBox();
        filterBox.setSpacing(5);

        ComboBox<String> filterComboBox = new ComboBox<>();
        filterComboBox.getItems().addAll("Nom Enfant", "Date de naissance", "Lieu de naissance", "Nom des parents");
        filterComboBox.setPromptText("Filtrer par");

        TextField edit_chercher = new TextField();
        edit_chercher.setPromptText("Entrez votre recherche");

        Button chercher = new Button("Rechercher");

        Region espaceVide = new Region();
        HBox.setHgrow(espaceVide, Priority.ALWAYS);

        filterBox.getChildren().addAll(filterComboBox, espaceVide, edit_chercher, chercher);

        TableView<Enfant> tableView = new TableView<>();

        TableColumn<Enfant, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getId())));

        TableColumn<Enfant, String> dateColumn = new TableColumn<>("Date de Naissance");
        dateColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDateNaissance()));

        TableColumn<Enfant, String> nomColumn = new TableColumn<>("Nom de l'Enfant");
        nomColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNom()));

        TableColumn<Enfant, String> prenomColumn = new TableColumn<>("Prénom");
        prenomColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrenom()));

        TableColumn<Enfant, String> lieuColumn = new TableColumn<>("Lieu de Naissance");
        lieuColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLieuNaissance()));

        TableColumn<Enfant, String> parentColumn = new TableColumn<>("Nom de Parent");
        parentColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNomParent()));

        TableColumn<Enfant, String> sexeColumn = new TableColumn<>("Sexe");
        sexeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSexe()));

        tableView.getColumns().addAll(idColumn, dateColumn, nomColumn, prenomColumn, lieuColumn, parentColumn, sexeColumn);

        ObservableList<Enfant> enfants = FXCollections.observableArrayList(
            new Enfant(001, "28 janvier 2003", "Dupont", "Jean", "Paris", "Dupont Père", "M"),
            new Enfant(002, "15 mars 2005", "Durand", "Marie", "Lyon", "Durand Mère", "F")
        );

        tableView.setItems(enfants);

        box_consultation.getChildren().addAll(filterBox, tableView);

        return box_consultation;
    }

    private VBox bar;
    private Image icon_menu, icon_ajout, icon_consu, icon_accueil, icon_param;
    private ImageView image_menu, image_ajout, image_consu, image_modif, image_param;
    private Label menu_icon, menu_ajout, menu_consu, menu_accueil, menu_param;
}
