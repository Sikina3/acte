package com.projetanais.acte;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

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
        icon_menu = new Image(getClass().getResourceAsStream("menumenu.png"));
        icon_ajout = new Image(getClass().getResourceAsStream("ajouter.png"));
        icon_consu = new Image(getClass().getResourceAsStream("liste.png"));
        icon_accueil = new Image(getClass().getResourceAsStream("accueil.png"));
        icon_param = new Image(getClass().getResourceAsStream("partir.png"));

        image_menu = new ImageView(icon_menu);
        image_menu.setOpacity(0.7);
        image_ajout = new ImageView(icon_ajout);
        image_ajout.setOpacity(0.7);
        image_consu = new ImageView(icon_consu);
        image_consu.setOpacity(1);
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

        hboxAjout.setOnMouseClicked(event -> {
            nouvelle_enfant nouvelleEnfant = new nouvelle_enfant();
            Stage stage_nouv = new Stage();
            nouvelleEnfant.start(stage_nouv);

            stage.close();
        });

        hbox_accueil.setOnMouseClicked(event -> {
            App accueil = new App();
            Stage stage_accueil = new Stage();
            accueil.start(stage_accueil);

            stage.close();
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

    private VBox page_de_consultation() {
        VBox box_consultation = new VBox();
        box_consultation.setSpacing(10);
        box_consultation.setPadding(new Insets(10));

        HBox filterBox = new HBox();
        filterBox.setSpacing(5);

        ComboBox<String> filterComboBox = new ComboBox<>();
        filterComboBox.getItems().addAll("Numero", "Nom Enfant", "Date de naissance", "Commune", "Nom des parents");
        filterComboBox.setPromptText("Rechercher par");
        filterBox.setAlignment(Pos.CENTER_RIGHT);

        TextField edit_chercher = new TextField();
        edit_chercher.setPromptText("Entrez votre recherche");

        Button chercher = new Button("Rechercher");

        filterBox.getChildren().addAll(filterComboBox, edit_chercher, chercher);

        TableView<Enfant> tableView = new TableView<>();

        TableColumn<Enfant, String> idColumn = new TableColumn<>("Numero");
        idColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getId())));

        TableColumn<Enfant, String> dateColumn = new TableColumn<>("Date de Naissance");
        dateColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDateNaissance()));
        dateColumn.setPrefWidth(130);

        TableColumn<Enfant, String> nomColumn = new TableColumn<>("Nom de l'Enfant");
        nomColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNom()));
        nomColumn.setPrefWidth(250);

        TableColumn<Enfant, String> prenomColumn = new TableColumn<>("Prénom");
        prenomColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrenom()));
        prenomColumn.setPrefWidth(100);

        TableColumn<Enfant, String> lieuColumn = new TableColumn<>("Lieu");
        lieuColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLieuNaissance()));

        TableColumn<Enfant, String> sexeColumn = new TableColumn<>("Sexe");
        sexeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSexe()));

        TableColumn<Enfant, String> parentsColumn = new TableColumn<>("Noms des Parents");
        parentsColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNomsParents()));
        parentsColumn.setPrefWidth(230);

        tableView.getColumns().addAll(idColumn, dateColumn, nomColumn, prenomColumn, lieuColumn, sexeColumn,
                parentsColumn);

        ObservableList<Enfant> enfants = FXCollections.observableArrayList(Enfant.getAllEnfants());
        FilteredList<Enfant> filteredEnfants = new FilteredList<>(enfants, p -> true);

        tableView.setRowFactory(tv -> {
            TableRow<Enfant> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    Enfant clickedEnfant = row.getItem();
                    try {
                        showPopup(clickedEnfant);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });

        chercher.setOnAction(event -> {
            String filterText = edit_chercher.getText();
            String filterCategory = filterComboBox.getValue();

            if (filterCategory == null || filterCategory.isEmpty() || filterText == null || filterText.isEmpty()) {
                filteredEnfants.setPredicate(p -> true);
            } else {
                filteredEnfants.setPredicate(enfant -> {
                    switch (filterCategory) {
                        case "Numero":
                            return String.valueOf(enfant.getId()).contains(filterText);
                        case "Nom Enfant":
                            return enfant.getNom().toLowerCase().contains(filterText.toLowerCase());
                        case "Date de naissance":
                            return enfant.getDateNaissance().contains(filterText);
                        case "Commune":
                            return enfant.getLieuNaissance().toLowerCase().contains(filterText.toLowerCase());
                        case "Nom des parents":
                            return enfant.getNomsParents().toLowerCase().contains(filterText.toLowerCase());
                        default:
                            return false;
                    }
                });
            }
        });

        tableView.setItems(filteredEnfants);
        tableView.setPrefHeight(600);

        box_consultation.getChildren().addAll(filterBox, tableView);

        return box_consultation;
    }

    private void showPopup(Enfant enfant) throws SQLException {
        Parents parents = Parents.chargerParId(enfant.getId());
        Acte acte = Acte.chargerParId(enfant.getId());
    
        // Création du popup de confirmation
        Stage confirmationStage = new Stage();
        confirmationStage.setTitle("Choix");
        confirmationStage.setWidth(300);
        confirmationStage.setHeight(150);
    
        VBox mainLayout = new VBox();
        mainLayout.setPadding(new Insets(20));
        mainLayout.setSpacing(10);
        mainLayout.setAlignment(Pos.CENTER);
    
        Label confirmationLabel = new Label("Que souhaitez-vous faire ?");
    
        // HBox pour aligner les boutons côte à côte
        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10); // Espacement entre les boutons
        buttonBox.setAlignment(Pos.CENTER);
    
        Button modifierBtn = new Button("Modifier");
        Button visualiserBtn = new Button("Visualiser l'état");
    
        modifierBtn.setOnAction(event -> {
            fenetre_modification modif = new fenetre_modification(enfant);
            System.out.println("id enfant: " + enfant.getId());
            Stage stage_modif = new Stage();
            modif.start(stage_modif);
            confirmationStage.close();
            stage.close(); 
        });
    
        visualiserBtn.setOnAction(event -> {
            try {
                showReport(enfant, parents, acte);
            } catch (JRException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            confirmationStage.close();
        });
    
        buttonBox.getChildren().addAll(modifierBtn, visualiserBtn);
    
        mainLayout.getChildren().addAll(confirmationLabel, buttonBox);
    
        Scene scene = new Scene(mainLayout);
        confirmationStage.setScene(scene);
        confirmationStage.initOwner(stage);
        confirmationStage.showAndWait();
    }

    private void showReport(Enfant enfant, Parents parents, Acte acte) throws JRException, SQLException {
        String reportPath = "lib/acte.jasper";
        
        ConversionMois conversionMois = new ConversionMois();
        ChiffreEnLettre chiLettre = new ChiffreEnLettre();
    
        Date sqlDateNaissance = Date.valueOf(enfant.getDateNaissance());
        String dateFormatee = conversionMois.formatDate(sqlDateNaissance);
        String datelettre = conversionMois.formatDate_enLettre(sqlDateNaissance);
    
        String heureNaiString = enfant.getHeureNaissance();
        String heureNais = chiLettre.ConvertHeure(heureNaiString);
        
        Date sqlDateNaissancePere = Date.valueOf(parents.getDnPere());
        String dateNaissancePereEnLettre = conversionMois.formatDate_enLettre(sqlDateNaissancePere);
    
        Date sqlDateNaissanceMere = Date.valueOf(parents.getDnMere());
        String dateNaissanceMereEnLettre = conversionMois.formatDate_enLettre(sqlDateNaissanceMere);
    
        Date sqlDateCreation = Date.valueOf(acte.getDateCreation());
        String dateCreationEnLettre = conversionMois.formatDate_enLettre(sqlDateCreation);
        
        String heureActeString = acte.getHeure();
        String heureActeEnLettre = chiLettre.ConvertHeure(heureActeString);
    
        // Préparation des paramètres pour JasperReport
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id_enfant", enfant.getId());
        parameters.put("date_naissance_formatee", dateFormatee);
        parameters.put("date_en_lettre", datelettre);
        parameters.put("heure_naissance", heureNais);
        parameters.put("date_naissance_pere_en_lettre", dateNaissancePereEnLettre);
        parameters.put("date_naissance_mere_en_lettre", dateNaissanceMereEnLettre);
        parameters.put("date_creation_en_lettre", dateCreationEnLettre);
        parameters.put("heure_creation", heureActeEnLettre);
    
        JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parameters, DatabaseConnection.getConnect());
    
        // Afficher le rapport
        JasperViewer viewer = new JasperViewer(jasperPrint, false);
        viewer.setZoomRatio(0.5f);
        viewer.setSize(800, 800); 
        viewer.toFront();
        stage.setIconified(true);
        viewer.setVisible(true);
    }
    
    
    

    private VBox bar;
    private Image icon_menu, icon_ajout, icon_consu, icon_accueil, icon_param;
    private ImageView image_menu, image_ajout, image_consu, image_modif, image_param;
    private Label menu_icon, menu_ajout, menu_consu, menu_accueil, menu_param;

}
