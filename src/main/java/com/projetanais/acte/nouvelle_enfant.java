package com.projetanais.acte;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class nouvelle_enfant {
    private Scene scene;
    private Stage stage;
    private BorderPane root;
    private int i = 0;

    public nouvelle_enfant() {
        root = new BorderPane();
        initialisation_Menu();
        root.setCenter(page_de_creation());
    }

    public void start(Stage stage) {
        this.stage = stage;
        scene = new Scene(root, 1000, 700);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("style_ajout.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Nouvelle Enfant");
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
        // hboxAjout.setAlignment(Pos.CENTER);
        hboxAjout.setPrefHeight(50);
        hboxAjout.setSpacing(20);

        HBox hboxConsu = new HBox(image_consu, menu_consu);
        // hboxConsu.setAlignment(Pos.CENTER);
        hboxConsu.setSpacing(20);
        hboxConsu.setPrefHeight(50);

        HBox hbox_accueil = new HBox(image_modif, menu_accueil);
        // hbox_accueil.setAlignment(Pos.CENTER);
        hbox_accueil.setSpacing(20);
        hbox_accueil.setPrefHeight(50);

        HBox hboxParam = new HBox(image_param, menu_param);
        // hboxParam.setAlignment(Pos.CENTER);
        hboxParam.setSpacing(20);
        hboxParam.setPrefHeight(50);

        menu_ajout.setVisible(false);
        menu_consu.setVisible(false);
        menu_accueil.setVisible(false);
        menu_param.setVisible(false);

        hboxConsu.setOnMouseClicked(event -> {
            fenetre_consultation consultation = new fenetre_consultation();
            Stage stage_consu = new Stage();
            consultation.start(stage_consu);
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

    private ScrollPane page_de_creation() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(30); // Espacement horizontal entre les colonnes
        gridPane.setVgap(20); // Espacement vertical entre les lignes
        gridPane.setPadding(new Insets(20));

        initialisation_TextField();

        ComboBox<String> sexeComboBox = new ComboBox<>();
        sexeComboBox.getItems().addAll("Masculin", "Féminin");
        sexeComboBox.setPrefWidth(300);

        // Ajouter les labels et les champs de texte au GridPane
        int row = 0;
        Label intro = new Label("Creation d'un acte de Naissance");
        intro.setId("intro");

        // Section sur l'enfant
        gridPane.add(intro, 0, row++);
        gridPane.add(createSectionLabel("Informations sur l'enfant"), 0, row++, 2, 1);
        gridPane.add(createLabeledField("Nom :", textField1), 0, row);
        gridPane.add(createLabeledField("Prénom:", textField2), 1, row++);
        gridPane.add(createLabeledField("Date de naissance:", textField3), 0, row);
        gridPane.add(createLabeledField("Lieu de naissance:", textField4), 1, row++);
        gridPane.add(createCombo("Sexe :", sexeComboBox), 0, row);
        gridPane.add(createLabeledField("Heure de naissance", textField16), 1, row++);

        // Section sur les parents
        gridPane.add(createSectionLabel("Informations sur le père"), 0, row++, 2, 1);
        gridPane.add(createLabeledField("Nom du père:", textField5), 0, row);
        gridPane.add(createLabeledField("Date de naissance:", textField6), 1, row++);
        gridPane.add(createLabeledField("Lieu de naissance:", textField7), 0, row);
        gridPane.add(createLabeledField("Profession:", textField8), 1, row++);

        gridPane.add(createSectionLabel("Informations sur la mère"), 0, row++, 2, 1);
        gridPane.add(createLabeledField("Nom de la mère:", textField9), 0, row);
        gridPane.add(createLabeledField("Date de naissance:", textField10), 1, row++);
        gridPane.add(createLabeledField("Lieu de naissance:", textField11), 0, row);
        gridPane.add(createLabeledField("Profession:", textField12), 1, row++);

        gridPane.add(createLabeledField("Adresse des parents:", textField13), 0, row++, 2, 1);

        // Section sur l'acte
        gridPane.add(createSectionLabel("Informations sur l'acte"), 0, row++, 2, 1);
        gridPane.add(createLabeledField("Date de la réalisation de l'acte:", textField14), 0, row);
        gridPane.add(createLabeledField("Heure", textField17), 1, row++);
        gridPane.add(createLabeledField("Nom du docteur:", textField15), 0, row++);

        enregistrer = new Button("Enregistrer");
        gridPane.add(enregistrer, 1, row);

        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);

        return scrollPane;
    }

    private VBox createLabeledField(String labelText, TextField textField) {
        Label label = new Label(labelText);
        label.setAlignment(Pos.CENTER_LEFT);
        textField.setPrefWidth(300);
        textField.setAlignment(Pos.CENTER_LEFT);

        VBox vBox = new VBox(label, textField);
        vBox.setSpacing(10);

        return vBox;
    }

    private VBox createCombo(String labelText, ComboBox<String> comboBox) {
        Label label = new Label(labelText);
        label.setAlignment(Pos.CENTER_LEFT);
        comboBox.setPrefWidth(300);
        VBox vBox = new VBox(label, comboBox);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER_LEFT);

        return vBox;
    }

    private Label createSectionLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
        label.setAlignment(Pos.CENTER_LEFT);
        if (!label.getText().equals("Informations sur l'enfant")) {
            label.setPadding(new Insets(50, 0, 0, 0));
        }

        return label;
    }

    private void initialisation_TextField() {
        textField1 = new TextField();
        textField1.setPromptText("Nom...");
        textField2 = new TextField();
        textField2.setPromptText("Prénom...");
        textField3 = new TextField();
        textField3.setPromptText("EX: 12 Janvier 2024");
        textField4 = new TextField();
        textField4.setPromptText("Lieu de naissance...");
        textField5 = new TextField();
        textField5.setPromptText("Nom du père...");
        textField6 = new TextField();
        textField6.setPromptText("Ex: 5 Avril 1989");
        textField7 = new TextField();
        textField7.setPromptText("Lieu de naissance du père...");
        textField8 = new TextField();
        textField8.setPromptText("Profession du père...");
        textField9 = new TextField();
        textField9.setPromptText("Nom de la mère...");
        textField10 = new TextField();
        textField10.setPromptText("Ex: 5 Avril 1989");
        textField11 = new TextField();
        textField11.setPromptText("Lieu de naissance de la mère...");
        textField12 = new TextField();
        textField12.setPromptText("Profession de la mère...");
        textField13 = new TextField();
        textField13.setPromptText("Adresse des parents...");
        textField14 = new TextField();
        textField14.setPromptText("Date de réalisation de l'acte...");
        textField15 = new TextField();
        textField15.setPromptText("Nom du docteur...");
        textField16 = new TextField();
        textField16.setPromptText("Ex: 16h 30");
        textField17 = new TextField();
        textField17.setPromptText("Ex: 16h 30");
    }

    private VBox bar;
    private Image icon_menu, icon_ajout, icon_consu, icon_accueil, icon_param;
    private ImageView image_menu, image_ajout, image_consu, image_modif, image_param;
    private Label menu_icon, menu_ajout, menu_consu, menu_accueil, menu_param;
    private TextField textField1, textField2, textField3, textField4, textField5, textField6, textField7, textField8,
            textField9, textField10, textField11, textField12, textField13, textField14, textField15, textField16, textField17;
    private Button enregistrer;

}
