package com.projetanais.acte;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    int i = 0;

    public App() {
        root = new BorderPane();
        initialisation_Menu();
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        scene = new Scene(root, 1000, 700);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Acte de naissance");
        stage.setResizable(false);
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("teste.jpg")));

        stage.show();
    }

    private void initialisation_Menu() {
        icon_menu = new Image(getClass().getResourceAsStream("menumenu.png"));
        icon_ajout = new Image(getClass().getResourceAsStream("ajouter.png"));
        icon_consu = new Image(getClass().getResourceAsStream("liste.png"));
        icon_accueil = new Image(getClass().getResourceAsStream("accueil.png"));
        icon_param = new Image(getClass().getResourceAsStream("partir.png"));

        image_menu = new ImageView(icon_menu);
        image_menu.setOpacity(0.8);
        image_ajout = new ImageView(icon_ajout);
        image_ajout.setOpacity(1);
        image_consu = new ImageView(icon_consu);
        image_consu.setOpacity(1);
        image_modif = new ImageView(icon_accueil);
        image_modif.setOpacity(0.8);
        image_param = new ImageView(icon_param);
        image_param.setOpacity(1);

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
        bar.setPrefWidth(20);
        bar.setAlignment(Pos.CENTER);
        bar.setSpacing(60);

        menu_ajout = new Label("Ajouter");
        menu_consu = new Label("Consulter");
        menu_accueil = new Label("Accueil");
        menu_param = new Label("Paramètres");

        // classe
        menu_ajout.getStyleClass().add("label-menu");
        menu_consu.getStyleClass().add("label-menu");
        menu_accueil.getStyleClass().add("label-menu");
        menu_param.getStyleClass().add("label-menu");

        HBox hboxAjout = new HBox(image_ajout, menu_ajout);
        //hboxAjout.setAlignment(Pos.CENTER);
        hboxAjout.setPrefHeight(50);
        hboxAjout.setSpacing(20);

        HBox hboxConsu = new HBox(image_consu, menu_consu);
        //hboxConsu.setAlignment(Pos.CENTER);
        hboxConsu.setSpacing(20);
        hboxConsu.setPrefHeight(50);

        HBox hbox_accueil = new HBox(image_modif, menu_accueil);
        //hbox_accueil.setAlignment(Pos.CENTER);
        hbox_accueil.setSpacing(20);
        hbox_accueil.setPrefHeight(50);

        HBox hboxParam = new HBox(image_param, menu_param);
        //hboxParam.setAlignment(Pos.CENTER);
        hboxParam.setSpacing(20);
        hboxParam.setPrefHeight(50);

        hboxAjout.getStyleClass().add("conteneur");
        hboxConsu.getStyleClass().add("conteneur");
        hboxParam.getStyleClass().add("conteneur");
        hbox_accueil.getStyleClass().add("conteneur");

        menu_ajout.setVisible(false);
        menu_consu.setVisible(false);
        menu_accueil.setVisible(false);
        menu_param.setVisible(false);

        hboxAjout.setOnMouseClicked(event -> {
            nouvelle_enfant nouvelleEnfant = new nouvelle_enfant();
            Stage stage_nouv = new Stage();
            nouvelleEnfant.start(stage_nouv);

            stage.close();
        });

        hboxConsu.setOnMouseClicked(event -> {
            fenetre_consultation consultation = new fenetre_consultation();
            Stage stage_consu = new Stage();
            consultation.start(stage_consu);
            stage.close();
        });

        menu_icon = new Label();
        menu_icon.setGraphic(image_menu);
        menu_icon.setId("menu_icon");
        menu_icon.setOnMouseClicked(event -> {
            if (i == 0) {
                bar.setPrefWidth(170);
                menu_ajout.setVisible(true);
                menu_consu.setVisible(true);
                menu_accueil.setVisible(true);
                menu_param.setVisible(true);
                menu_icon.setStyle("-fx-translate-x: -60px;");
                i = 1;
            } else if (i == 1) {
                bar.setPrefWidth(40);
                menu_ajout.setVisible(false);
                menu_consu.setVisible(false);
                menu_accueil.setVisible(false);
                menu_param.setVisible(false);
                menu_icon.setStyle("-fx-translate-x: 0;");
                i = 0;
            }
        });

        bar.getChildren().addAll(menu_icon, hbox_accueil, hboxAjout, hboxConsu, hboxParam);

        root.setCenter(createHomePage());
        root.setLeft(bar);
    }

    private ScrollPane createHomePage() {
        VBox homePage = new VBox();
        homePage.setAlignment(Pos.CENTER);
        homePage.setId("home");
        homePage.setSpacing(20);

        HBox box_cadre = new HBox();
        box_cadre.setId("cadreBox");
        box_cadre.setSpacing(60);
        box_cadre.setPrefSize(10, 100);

        box_distric = new VBox();
        box_distric.setPrefSize(300, 50);
        box_distric.setId("box_distric");
        box_nombre_enfant = new VBox();
        box_nombre_enfant.setId("box_nb");
        box_nombre_enfant.setPrefSize(300, 50);
        box_cadre.getChildren().addAll(box_distric, box_nombre_enfant);
        distric = new Label("Toamasina");
        nb_enfant = new Label("0");
        box_distric.getChildren().addAll(new Label("Distric de "), distric);
        box_nombre_enfant.getChildren().addAll(nb_enfant, new Label("Enfants enregistrer"));
        box_nombre_enfant.setSpacing(20);

        VBox diagramme = new VBox();
        PieChart pie = new PieChart();
        pie.setTitle("Diagrammes des enfants enregistrer");
        pie.setTitleSide(Side.BOTTOM);
        PieChart.Data part1 = new PieChart.Data("Garcons", 60);
        PieChart.Data part2 = new PieChart.Data("Filles", 40);
        pie.getData().add(part1);
        pie.getData().add(part2);
        pie.setLabelLineLength(20);
        pie.setLegendSide(Side.LEFT);
        diagramme.getChildren().add(pie);

        homePage.getChildren().addAll(box_cadre, diagramme);

        ScrollPane scrollPane = new ScrollPane(homePage);
        scrollPane.setFitToWidth(true);
        return scrollPane;
    }

    public static void main(String[] args) {
        launch(args);
    }

    private Stage stage;
    private Scene scene;
    private BorderPane root;
    private VBox bar, box_distric, box_nombre_enfant;
    private Image icon_menu, icon_ajout, icon_consu, icon_accueil, icon_param;
    private ImageView image_menu, image_ajout, image_consu, image_modif, image_param;
    private Label menu_icon, menu_ajout, menu_consu, menu_accueil, menu_param;
    private Label distric, nb_enfant;
}
