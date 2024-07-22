package com.projetanais.acte;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class fenetre_modification {
    private Scene scene;
    private Stage stage;
    private BorderPane root;
    private int i = 0;

    public fenetre_modification() {
        root = new BorderPane();
        initialisation_Menu();
        // Ajoutez ici votre logique d'initialisation
    }

    public void start(Stage stage) {
        this.stage = stage;
        scene = new Scene(root, 1000, 700);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Modification");
        stage.setResizable(false);
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("teste.jpg")));

        stage.show();
    }

    private void initialisation_Menu() {
        icon1 = new Image(getClass().getResourceAsStream("soins.png"));
        icon_menu = new Image(getClass().getResourceAsStream("menuB.png"));
        icon_ajout = new Image(getClass().getResourceAsStream("creer.png"));
        icon_consu = new Image(getClass().getResourceAsStream("visualisation-de-donnees.png"));
        icon_modif = new Image(getClass().getResourceAsStream("crayon.png"));
        icon_param = new Image(getClass().getResourceAsStream("parametres.png"));

        logo = new ImageView(icon1);
        logo.setOpacity(1);
        image_menu = new ImageView(icon_menu);
        image_menu.setOpacity(0.6);
        image_ajout = new ImageView(icon_ajout);
        image_ajout.setOpacity(0.4);
        image_consu = new ImageView(icon_consu);
        image_consu.setOpacity(0.4);
        image_modif = new ImageView(icon_modif);
        image_modif.setOpacity(0.4);
        image_param = new ImageView(icon_param);
        image_param.setOpacity(0.4);

        // gauche
        logo.setFitWidth(35);
        logo.setFitHeight(35);
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

        logo_label = new Label();
        logo_label.setGraphic(logo);

        bar = new VBox();
        bar.setId("bar-left");
        bar.setPrefWidth(70);
        bar.setAlignment(Pos.CENTER);
        bar.setSpacing(60);

        menu_ajout = new Label("Ajouter");
        menu_consu = new Label("Consulter");
        menu_modif = new Label("Modifier");
        menu_param = new Label("Paramètres");

        // classe
        menu_ajout.getStyleClass().add("label-menu");
        menu_consu.getStyleClass().add("label-menu");
        menu_modif.getStyleClass().add("label-menu");
        menu_param.getStyleClass().add("label-menu");

        HBox hboxAjout = new HBox(image_ajout, menu_ajout);
        hboxAjout.setAlignment(Pos.CENTER);
        hboxAjout.setPrefHeight(50);
        hboxAjout.setSpacing(20);
        HBox hboxConsu = new HBox(image_consu, menu_consu);
        hboxConsu.setAlignment(Pos.CENTER);
        hboxConsu.setSpacing(20);
        hboxConsu.setPrefHeight(50);
        HBox hboxModif = new HBox(image_modif, menu_modif);
        hboxModif.setAlignment(Pos.CENTER);
        hboxModif.setSpacing(20);
        hboxModif.setPrefHeight(50);
        HBox hboxParam = new HBox(image_param, menu_param);
        hboxParam.setAlignment(Pos.CENTER);
        hboxParam.setSpacing(20);
        hboxParam.setPrefHeight(50);

        menu_ajout.setVisible(false);
        menu_consu.setVisible(false);
        menu_modif.setVisible(false);
        menu_param.setVisible(false);

        hboxAjout.setOnMouseClicked(event -> {
                nouvelle_enfant nouvelleEnfant = new nouvelle_enfant();
                Stage newStage = new Stage();
                nouvelleEnfant.start(newStage);
                // Optionnel : Fermer la fenêtre actuelle si vous ne voulez pas l'afficher en même temps
                stage.close();
        });

        hboxConsu.setOnMouseClicked(event ->{
            fenetre_consultation consultation = new fenetre_consultation();
            Stage stage_consu = new Stage();
            consultation.start(stage_consu);
            stage.close();
        });

        hboxModif.setOnMouseClicked(event ->{
            //rien
        });
        
        
        hboxParam.setOnMouseClicked(event ->{
            App accueil = new App();
            Stage stage_accueil = new Stage();
            accueil.start(stage_accueil);
            stage.close();
        });

        bar.getChildren().addAll(hboxAjout, hboxConsu, hboxModif, hboxParam);

        b = new MenuBar();
        b.setId("bar-top");
        b.setPrefWidth(Double.MAX_VALUE);

        logo_top = new Label();
        logo_top.setId("logo");
        logo_top.setGraphic(logo_label);

        menu_icon = new Label();
        menu_icon.setGraphic(image_menu);
        menu_icon.setId("menu_icon");
        menu_icon.setOnMouseClicked(event -> {
            if (i == 0) {
                bar.setPrefWidth(200);
                menu_ajout.setVisible(true);
                menu_consu.setVisible(true);
                menu_modif.setVisible(true);
                menu_param.setVisible(true);
                i = 1;
            } else if (i == 1) {
                bar.setPrefWidth(70);
                menu_ajout.setVisible(false);
                menu_consu.setVisible(false);
                menu_modif.setVisible(false);
                menu_param.setVisible(false);
                i = 0;
            }
        });

        Menu m = new Menu("");
        Menu n = new Menu("");
        n.setId("n");
        m.setId("m");
        b.getMenus().add(n);
        b.getMenus().add(m);
        b.getMenus().get(0).setGraphic(logo_top);
        b.getMenus().get(1).setGraphic(menu_icon);

        root.setLeft(bar);
        root.setTop(b);
    }

    private MenuBar b;
    private VBox bar;
    private Image icon1, icon_menu, icon_ajout, icon_consu, icon_modif, icon_param;
    private ImageView logo, image_menu, image_ajout, image_consu, image_modif, image_param;
    private Label logo_label, logo_top, menu_icon, menu_ajout, menu_consu, menu_modif, menu_param;

}
