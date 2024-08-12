package com.projetanais.acte;

import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

public class fenetre_modification {
    private Scene scene;
    private Stage stage;
    private BorderPane root;
    private Enfant enfant;
    private int i = 0;

    public fenetre_modification(Enfant enfant) {
        this.enfant = enfant;
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
        stage.setTitle("Modification");
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
        image_menu.setOpacity(0.7);
        image_ajout = new ImageView(icon_ajout);
        image_ajout.setOpacity(1);
        image_consu = new ImageView(icon_consu);
        image_consu.setOpacity(0.7);
        image_modif = new ImageView(icon_accueil);
        image_modif.setOpacity(0.7);
        image_param = new ImageView(icon_param);
        image_param.setOpacity(0.7);

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
        bar.setSpacing(40);

        menu_ajout = new Label("Ajouter");
        menu_consu = new Label("Consulter");
        menu_accueil = new Label("Accueil");
        menu_param = new Label("Quitter");

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

        hboxAjout.getStyleClass().add("conteneur");
        hboxConsu.getStyleClass().add("conteneur");
        hboxParam.getStyleClass().add("conteneur");
        hbox_accueil.getStyleClass().add("conteneur");

        hboxAjout.getStyleClass().add("icon_nav");
        hboxConsu.getStyleClass().add("icon_nav");
        hboxParam.getStyleClass().add("icon_nav");
        hbox_accueil.getStyleClass().add("icon_nav");

        menu_ajout.setVisible(false);
        menu_consu.setVisible(false);
        menu_accueil.setVisible(false);
        menu_param.setVisible(false);

        hbox_accueil.setOnMouseClicked(event -> {
            App accueil = new App();
            Stage stage_accueil = new Stage();

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Voulez-vous vraiment quitter la modification ?");
            alert.setContentText("Toutes les données non enregistrées seront perdues.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {   
                accueil.start(stage_accueil);
                stage.close();
            }
        });

        hboxConsu.setOnMouseClicked(event -> {
            fenetre_consultation consultation = new fenetre_consultation();
            Stage stage_consu = new Stage();
            
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Voulez-vous vraiment quitter la modification ?");
            alert.setContentText("Toutes les données non enregistrées seront perdues.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {   
                consultation.start(stage_consu);
                stage.close();
            }
        });

        hboxAjout.setOnMouseClicked(event ->{
            nouvelle_enfant nouvel = new nouvelle_enfant();
            Stage stage_nouv = new Stage();

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Voulez-vous vraiment quitter la modification ?");
            alert.setContentText("Toutes les données non enregistrées seront perdues.");

            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK){
                nouvel.start(stage_nouv);
                stage.close();
            }
        });

        hboxParam.setOnMouseClicked(event -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de sortie");
            alert.setHeaderText("Voulez-vous vraiment quitter le logiciel ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Fermez l'application
                stage.close();
            }
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

        root.setLeft(bar);
    }

    private ScrollPane page_de_creation() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(30); // Espacement horizontal entre les colonnes
        gridPane.setVgap(20); // Espacement vertical entre les lignes
        gridPane.setPadding(new Insets(30, 0, 120, 100));

        initialisation_TextField();
        initialisation_DateComboBoxes();
        initialisation_heure();

        ComboBox<String> sexeComboBox = new ComboBox<>();
        sexeComboBox.getItems().addAll("Masculin", "Féminin");
        sexeComboBox.setValue(enfant.getSexe());
        sexeComboBox.setPrefWidth(300);

        HBox dateNaissanceEnfant = new HBox(10); // Espacement de 10 entre les ComboBox
        dateNaissanceEnfant.getChildren().addAll(jourComboBox, moisComboBox, anneeComboBox);

        HBox dateNaissancePere = new HBox(10); // Espacement de 10 entre les ComboBox
        dateNaissancePere.getChildren().addAll(jourComboBox1, moisComboBox1, anneeComboBox1);

        HBox dateNaissanceMere = new HBox(10); // Espacement de 10 entre les ComboBox
        dateNaissanceMere.getChildren().addAll(jourComboBox2, moisComboBox2, anneeComboBox2);

        HBox dateRealisation = new HBox(10); // Espacement de 10 entre les ComboBox
        dateRealisation.getChildren().addAll(jourComboBox3, moisComboBox3, anneeComboBox3);

        HBox heureNaissanceEnfant = new HBox(10);
        heureNaissanceEnfant.getChildren().addAll(heureNais, new Label("h"), minutesNais);

        HBox heureActeNaissace = new HBox(10);
        heureActeNaissace.getChildren().addAll(heureActeNais, new Label("h"), minutesActe);

        // Ajouter les labels et les champs de texte au GridPane
        int row = 0;
        gridPane.add(createSectionLabel("Informations sur l'enfant"), 0, row++, 2, 1);
        gridPane.add(createLabeledField("Nom :", textField1), 0, row);
        gridPane.add(createLabeledField("Prénom:", textField2), 1, row++);
        gridPane.add(createComboNais("Date de naissance:", dateNaissanceEnfant), 0, row);
        gridPane.add(createLabeledField("Lieu de naissance:", textField4), 1, row++);
        gridPane.add(createComboNais("Heure de naissance", heureNaissanceEnfant), 0, row);
        gridPane.add(createLabeledField("Condition : ", textField18), 1, row++);
        gridPane.add(createCombo("Sexe :", sexeComboBox), 0, row++);

        // Section sur les parents
        gridPane.add(createSectionLabel("Informations sur le père"), 0, row++, 2, 1);
        gridPane.add(createLabeledField("Nom du père:", textField5), 0, row);
        gridPane.add(createComboNais("Date de naissance:", dateNaissancePere), 1, row++);
        gridPane.add(createLabeledField("Lieu de naissance:", textField7), 0, row);
        gridPane.add(createLabeledField("Profession:", textField8), 1, row++);

        gridPane.add(createSectionLabel("Informations sur la mère"), 0, row++, 2, 1);
        gridPane.add(createLabeledField("Nom de la mère:", textField9), 0, row);
        gridPane.add(createComboNais("Date de naissance:", dateNaissanceMere), 1, row++);
        gridPane.add(createLabeledField("Lieu de naissance:", textField11), 0, row);
        gridPane.add(createLabeledField("Profession:", textField12), 1, row++);

        gridPane.add(createLabeledField("Adresse des parents:", textField13), 0, row++, 2, 1);

        // Section sur l'acte
        gridPane.add(createSectionLabel("Informations sur l'acte"), 0, row++, 2, 1);
        gridPane.add(createLabeledField("Distric de :", textField19), 0, row);
        gridPane.add(createLabeledField("Commune de :", textField20), 1, row++);
        gridPane.add(createLabeledField("Nom du docteur:", textField15), 0, row);
        gridPane.add(createComboNais("Date de realisation:", dateRealisation), 1, row++);
        gridPane.add(createComboNais("Heure", heureActeNaissace), 0, row);
        gridPane.add(createLabeledField("Condition :", textField21), 1, row++);
        gridPane.add(createLabeledField("Nom de la responsable:", textField22), 0, row++);

        enregistrer = new Button("Enregistrer");
        enregistrer.setId("enregistrer");
        gridPane.add(enregistrer, 1, row);

        enregistrer.setOnAction(event -> {
            // Recuperation des donnee des champs de texte pour l'enfant
            String nom_enfant = textField1.getText();
            String prenom_enfant = textField2.getText();
            String dateNaissance = jourComboBox.getValue().concat(" ").concat(moisComboBox.getValue()).concat(" ")
                    .concat(anneeComboBox.getValue());
            String lieuNaissance = textField4.getText();
            String heureNaissance = heureNais.getValue().concat("h").concat(minutesNais.getValue());
            String condition = textField18.getText();
            String sexe = sexeComboBox.getValue();

            // Recuperation des donnee des champs de texte pour le père
            String nomPere = textField5.getText();
            String dateP = jourComboBox1.getValue().concat(" ").concat(moisComboBox1.getValue()).concat(" ")
                    .concat(anneeComboBox1.getValue());
            String lieuNaissancePere = textField7.getText();
            String professionPere = textField8.getText();

            // Recuperation des donnee des champs de texte pour la mère
            String nomMere = textField9.getText();
            String dateM = jourComboBox2.getValue().concat(" ").concat(moisComboBox2.getValue()).concat(" ")
                    .concat(anneeComboBox2.getValue());
            String lieuNaissanceMere = textField11.getText();
            String professionMere = textField12.getText();

            String adresse = textField13.getText();

            // Recuperation des donnee des champs de texte pour l'acte
            String distric = textField19.getText();
            String commune = textField20.getText();
            String nomDocteur = textField15.getText();
            String dateCreationActe = jourComboBox3.getValue().concat(" ").concat(moisComboBox3.getValue()).concat(" ")
                    .concat(anneeComboBox3.getValue());
            String heureActe = heureActeNais.getValue().concat("h").concat(minutesActe.getValue());
            String conditionActe = textField21.getText();
            String nomResponsable = textField22.getText();

            Parents parents = new Parents(nomPere, nomMere, dateP, dateM, lieuNaissancePere,
                    lieuNaissanceMere, professionPere, professionMere, adresse);
            parents.modifier(enfant.getIdParents());

            
            // enfant = new Enfant(parents.getId(), nom_enfant, prenom_enfant, dateNaissance, lieuNaissance,
            //         heureNaissance, condition, sexe);
            enfant.setNom(nom_enfant);
            enfant.setPrenom(prenom_enfant);
            enfant.setDateNaissance(dateNaissance);
            enfant.setLieuNaissance(lieuNaissance);
            enfant.setMatinSoir(condition);
            enfant.setHeureNaissance(heureNaissance);
            enfant.setSexe(sexe);
            enfant.modifier(enfant.getId());

            Acte acte = new Acte(enfant.getId(), dateCreationActe, heureActe, conditionActe, nomDocteur, nomResponsable,
                    distric, commune);
            System.out.println("id de l'acte: " + acte.getId_enfants());
            acte.modifier(acte.getId_enfants());

            System.out.println("Les informations ont été enregistrées avec succès !");
        });

        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);

        return scrollPane;
    }

    private void initialisation_DateComboBoxes() {
        ObservableList<String> mois = FXCollections.observableArrayList("Janoary", "Febroary", "Marsa", "Aprily", "May",
                "Jiona", "Jiolay", "Aogositra", "Septambra",
                "Oktobra", "Novambra", "Desambra");
        moisComboBox = new ComboBox<>(mois);
        moisComboBox.setPrefWidth(150);
        moisComboBox1 = new ComboBox<>(mois);
        moisComboBox1.setPrefWidth(150);
        moisComboBox2 = new ComboBox<>(mois);
        moisComboBox2.setPrefWidth(150);
        moisComboBox3 = new ComboBox<>(mois);
        moisComboBox3.setPrefWidth(150);

        ObservableList<String> jours = FXCollections.observableArrayList();
        for (int i = 1; i <= 31; i++) {
            jours.add(String.format("%02d", i));
        }
        jourComboBox = new ComboBox<>(jours);
        jourComboBox.setPrefWidth(100);
        jourComboBox1 = new ComboBox<>(jours);
        jourComboBox1.setPrefWidth(100);
        jourComboBox2 = new ComboBox<>(jours);
        jourComboBox2.setPrefWidth(100);
        jourComboBox3 = new ComboBox<>(jours);
        jourComboBox3.setPrefWidth(100);

        ObservableList<String> annees = FXCollections.observableArrayList();
        for (int i = 1980; i <= java.time.Year.now().getValue(); i++) {
            annees.add(String.valueOf(i));
        }
        anneeComboBox = new ComboBox<>(annees);
        anneeComboBox.setPrefWidth(100);
        anneeComboBox1 = new ComboBox<>(annees);
        anneeComboBox1.setPrefWidth(100);
        anneeComboBox2 = new ComboBox<>(annees);
        anneeComboBox2.setPrefWidth(100);
        anneeComboBox3 = new ComboBox<>(annees);
        anneeComboBox3.setPrefWidth(100);

        int idParents = enfant.getIdParents();

        Parents parents = Parents.chargerParId(idParents);
        Acte acte = Acte.chargerParId(enfant.getId());

        String[] dateEnfant = enfant.getDateNaissance().split("-");
        ConversionMois conv = new ConversionMois();
        String moisE = conv.ConversionInverse(dateEnfant[1]);
        String[] datePere = parents.getDnPere().split("-");
        String moisP = conv.ConversionInverse(datePere[1]);
        String[] dateMere = parents.getDnMere().split("-");
        String moisM = conv.ConversionInverse(dateMere[1]);
        String[] dateReal = acte.getDateCreation().split("-");
        String moisR = conv.ConversionInverse(dateReal[1]);

        if (enfant != null) {
            jourComboBox.setValue(dateEnfant[2]);
            anneeComboBox.setValue(dateEnfant[0]);
            moisComboBox.setValue(moisE);
        }
        if (parents != null) {
            jourComboBox1.setValue(datePere[2]);
            anneeComboBox1.setValue(datePere[0]);
            moisComboBox1.setValue(moisP);
            jourComboBox2.setValue(dateMere[2]);
            anneeComboBox2.setValue(dateMere[0]);
            moisComboBox2.setValue(moisM);
        }
        if (acte != null) {
            jourComboBox3.setValue(dateReal[2]);
            anneeComboBox3.setValue(dateReal[0]);
            moisComboBox3.setValue(moisR);
        }
    }

    private void initialisation_heure(){
        ObservableList<String> heure = FXCollections.observableArrayList();
        for(int i = 0; i <= 23; i++){
            heure.add(String.format("%02d", i));
        }
        heureNais = new ComboBox<>(heure);
        heureNais.setPrefWidth(80);
        heureActeNais = new ComboBox<>(heure);
        heureActeNais.setPrefWidth(80);

        ObservableList<String>minutes = FXCollections.observableArrayList();
        for(int i = 0; i <= 59; i++){
            minutes.add(String.format("%02d", i));
        }
        minutesNais = new ComboBox<>(minutes);
        minutesNais.setPrefWidth(80);
        minutesActe = new ComboBox<>(minutes);
        minutesActe.setPrefWidth(80);

        Acte acte = Acte.chargerParId(enfant.getId());

        String[] heureEnfant = enfant.getHeureNaissance().split(":");
        String[] heureStrActe = acte.getHeure().split(":");

        if(enfant != null){
            heureNais.setValue(heureEnfant[0]);
            minutesNais.setValue(heureEnfant[1]);
        }
        if(acte != null){
            heureActeNais.setValue(heureStrActe[0]);
            minutesActe.setValue(heureStrActe[1]);
        }
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

    private VBox createComboNais(String labelText, HBox hbox) {
        Label label = new Label(labelText);
        label.setAlignment(Pos.CENTER_LEFT);
        hbox.setAlignment(Pos.CENTER_LEFT);
        VBox vBox = new VBox(label, hbox);
        vBox.setSpacing(10);
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
        initialisation_DateComboBoxes();
        textField1 = new TextField();
        textField1.setText("");
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
        textField18 = new TextField();
        textField18.setPromptText("matin ou soir");
        textField19 = new TextField();
        textField19.setPromptText("disc ");
        textField20 = new TextField();
        textField20.setPromptText("com ");
        textField21 = new TextField();
        textField21.setPromptText("matin ou soir");
        textField22 = new TextField();
        textField22.setPromptText("resp ");

        int idParents = enfant.getIdParents();

        Parents parents = Parents.chargerParId(idParents);
        Acte acte = Acte.chargerParId(enfant.getId());

        if (enfant != null) {
            textField1.setText(enfant.getNom());
            textField2.setText(enfant.getPrenom());
            textField4.setText(enfant.getLieuNaissance());
            textField16.setText(enfant.getHeureNaissance());
            textField18.setText(enfant.getMatinSoir());
        }

        if (parents != null) {
            textField5.setText(parents.getNomPere());
            textField9.setText(parents.getNomMere());
            textField6.setText(parents.getDnPere());
            textField10.setText(parents.getDnMere());
            textField7.setText(parents.getLnPere());
            textField11.setText(parents.getLnMere());
            textField8.setText(parents.getProfPere());
            textField12.setText(parents.getProfMere());
            textField13.setText(parents.getAdresse());
        }

        if (acte != null) {
            textField14.setText(acte.getDateCreation());
            textField17.setText(acte.getHeure());
            textField21.setText(acte.getMatinSoir());
            textField15.setText(acte.getNomDocteur());
            textField22.setText(acte.getNomResponsable());
            textField19.setText(acte.getDistric());
            textField20.setText(acte.getCommune());
        }
    }

    private VBox bar;
    private Image icon_menu, icon_ajout, icon_consu, icon_accueil, icon_param;
    private ImageView image_menu, image_ajout, image_consu, image_modif, image_param;
    private Label menu_icon, menu_ajout, menu_consu, menu_accueil, menu_param;
    private TextField textField1, textField2, textField3, textField4, textField5, textField6, textField7, textField8,
            textField9, textField10, textField11, textField12, textField13, textField14, textField15, textField16,
            textField17, textField18, textField19, textField20, textField21, textField22;
    private Button enregistrer;
    private ComboBox<String> jourComboBox, moisComboBox, anneeComboBox;
    private ComboBox<String> jourComboBox1, moisComboBox1, anneeComboBox1;
    private ComboBox<String> jourComboBox2, moisComboBox2, anneeComboBox2;
    private ComboBox<String> jourComboBox3, moisComboBox3, anneeComboBox3;
    private ComboBox<String> heureNais, minutesNais, heureActeNais, minutesActe;
}
