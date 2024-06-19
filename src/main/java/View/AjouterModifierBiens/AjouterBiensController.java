package View.AjouterModifierBiens;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Component.Header.HeaderController;
import Location.Bien;
import Location.Caracteristique;
import Location.CaracteristiqueBien;
import Location.Photographie;
import Location.Utilisateur;
import Persist.jdbcDataAccess;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AjouterBiensController implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent root;

    jdbcDataAccess jdbcDataAccess = new jdbcDataAccess();

    private int idProprietaire;

    List<Utilisateur> utilisateurs;
    List<Integer> idCaracteristiques = new ArrayList<>();
    List<Caracteristique> caracteristiques;

    @FXML
    private int idBien;

    @FXML
    private boolean isAjouter = true;

    @FXML
    private VBox layout;

    @FXML
    private MenuButton menuButton;

    @FXML
    private ListView<?> listView;

    @FXML
    private TextField nbPiece;

    @FXML
    private TextField surface;

    @FXML
    private TextField loyer;

    @FXML
    private TextField codePostal;

    @FXML
    private TextField adresse;

    @FXML
    private TextField title;

    @FXML
    private TextField description;

    @FXML
    private TextField type;

    @FXML
    private HBox listCaracteristiques;

    @FXML
    private TextField newCaracteristique;

    private File selectedFile;

    @FXML
    private Button selectPhotoButton;

    @FXML
    private Button savePhotoButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Component/Header/Header.fxml"));
        try {
            Parent component = loader.load();
            HeaderController controller = loader.getController();
            layout.getChildren().add(0, component);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            utilisateurs = jdbcDataAccess.getTiers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Utilisateur utilisateur : utilisateurs) {
            MenuItem menuItem = new MenuItem(utilisateur.getNom());
            menuItem.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    menuButton.setText(utilisateur.getNom());
                    idProprietaire = utilisateur.getId();
                }
            });
            menuButton.getItems().add(menuItem);
        }

        try {
            caracteristiques = jdbcDataAccess.getCaracteristiques();
            for (Caracteristique caracteristique : caracteristiques) {
                Button button = new Button(caracteristique.getNom());
                if (idCaracteristiques.contains(caracteristique.getId())) {
                    button.setStyle("-fx-background-color: rgba(151, 71, 255, 1)");
                } else {
                    button.setStyle("-fx-background-color: lightgrey");
                }
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (idCaracteristiques.contains(caracteristique.getId())) {
                            idCaracteristiques.remove((Integer) caracteristique.getId());
                            button.setStyle("-fx-background-color: lightgrey");
                            return;
                        } else {
                            idCaracteristiques.add(caracteristique.getId());
                            button.setStyle("-fx-background-color: rgba(151, 71, 255, 1)");
                        }
                    }
                });
                listCaracteristiques.getChildren().add(button);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        loyer.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                loyer.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        nbPiece.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                nbPiece.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        surface.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[\\d,\\.]*")) {
                surface.setText(newValue.replaceAll("[^\\d,\\.]", ""));
            }
        });
    }

    @FXML
    public void clickOnSaveButton(ActionEvent event) throws IOException{
        if (isAjouter) {
            Utilisateur proprietaire = new Utilisateur();
            Bien bien = new Bien(
                title.getText(),
                adresse.getText(),
                codePostal.getText(),
                Integer.parseInt(nbPiece.getText()),
                Integer.parseInt(surface.getText()),
                description.getText(),
                Integer.parseInt(loyer.getText()),
                type.getText(),
                proprietaire.getUtilisateurById(idProprietaire)
            );
            for (int idCaracteristique : idCaracteristiques) {
                CaracteristiqueBien caracteristiqueBien = new CaracteristiqueBien(new Caracteristique().getCaracteristiqueById(idCaracteristique), bien);
            }
            Photographie photographie = new Photographie(selectedFile.getName(), bien);
        } else {
            Utilisateur proprietaire = new Utilisateur();
            Bien bien = new Bien();
            bien.updateAll(idBien, title.getText(), adresse.getText(), codePostal.getText(), Integer.parseInt(nbPiece.getText()), Integer.parseInt(surface.getText()), description.getText(), Integer.parseInt(loyer.getText()), type.getText(), proprietaire.getUtilisateurById(idProprietaire));
            Bien bienUpdated = bien.getBienById(idBien);
            for (int idCaracteristique : idCaracteristiques) {
                CaracteristiqueBien caracteristiqueBien = new CaracteristiqueBien(new Caracteristique().getCaracteristiqueById(idCaracteristique), bienUpdated);
            }
            Photographie photographie = new Photographie(selectedFile.getName(), bienUpdated);
        }

        root = FXMLLoader.load(getClass().getResource("../Biens/Biens.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void clickOnStopButton(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../Biens/Biens.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void addingCaracteristique(ActionEvent event) {
        Caracteristique Caracteristique = new Caracteristique(newCaracteristique.getText());
        listCaracteristiques.getChildren().clear();
        try {
            caracteristiques = jdbcDataAccess.getCaracteristiques();
            for (Caracteristique caracteristique : caracteristiques) {
                Button button = new Button(caracteristique.getNom());
                button.setStyle("-fx-background-color: lightgrey");
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (idCaracteristiques.contains(caracteristique.getId())) {
                            idCaracteristiques.remove((Integer) caracteristique.getId());
                            button.setStyle("-fx-background-color: lightgrey");
                            return;
                        } else {
                            idCaracteristiques.add(caracteristique.getId());
                            button.setStyle("-fx-background-color: rgba(151, 71, 255, 1)");
                        }
                    }
                });
                listCaracteristiques.getChildren().add(button);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setData(Bien bien) {
        isAjouter = false;
        idBien = bien.getId();
        title.setText(bien.getNom());
        adresse.setText(bien.getAdresse());
        codePostal.setText(bien.getCodePostal());
        nbPiece.setText(String.valueOf(bien.getNbPieces()));
        surface.setText(String.valueOf(bien.getSurface()));
        description.setText(bien.getDescription());
        loyer.setText(String.valueOf(bien.getLoyer()));
        type.setText(bien.getType());
        menuButton.setText(bien.getProprietaire().getNom());
        for (CaracteristiqueBien caracteristiqueBien : bien.getCaracteristiqueBiens()) {
            idCaracteristiques.add(caracteristiqueBien.getCaracteristique().getId());
        }
        idProprietaire = bien.getProprietaire().getId();

        listCaracteristiques.getChildren().clear();
        try {
            caracteristiques = jdbcDataAccess.getCaracteristiques();
            for (Caracteristique caracteristique : caracteristiques) {
                Button button = new Button(caracteristique.getNom());
                if (idCaracteristiques.contains(caracteristique.getId())) {
                    button.setStyle("-fx-background-color: rgba(151, 71, 255, 1)");
                } else {
                    button.setStyle("-fx-background-color: lightgrey");
                }
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (idCaracteristiques.contains(caracteristique.getId())) {
                            idCaracteristiques.remove((Integer) caracteristique.getId());
                            button.setStyle("-fx-background-color: lightgrey");
                            return;
                        } else {
                            idCaracteristiques.add(caracteristique.getId());
                            button.setStyle("-fx-background-color: rgba(151, 71, 255, 1)");
                        }
                    }
                });
                listCaracteristiques.getChildren().add(button);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void handleSelectPhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner une photo");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        Stage stage = (Stage) selectPhotoButton.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);
    }

    @FXML
    public void handleSavePhoto(ActionEvent event) {
        if (selectedFile != null) {
            File destFile = new File("./Photos/", selectedFile.getName());
            try (FileInputStream fis = new FileInputStream(selectedFile);
                 FileOutputStream fos = new FileOutputStream(destFile)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                }
                System.out.println("Photo enregistrée avec succès !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Aucun fichier sélectionné.");
        }
    }

}
