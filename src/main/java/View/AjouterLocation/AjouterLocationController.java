package View.AjouterLocation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import Component.Header.HeaderController;
import Location.Bien;
import Location.Location;
import Location.Utilisateur;
import Persist.jdbcDataAccess;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AjouterLocationController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    List<Bien> biens;

    jdbcDataAccess jdbcDataAccess = new jdbcDataAccess();

    private int idBien;
    private int idLocataire;

    private Location locationToModify;

    @FXML
    public Boolean isAjouter = true;

    @FXML
    public TextField dateDebut;

    @FXML
    public TextField dateFin;

    @FXML
    public TextField commentaire;
    
    @FXML
    public MenuButton menuButton;

    @FXML
    public VBox layout;

    @FXML
    public void goToLocation(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("../Location/Locations.fxml"));
            stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void ajouterLocation(ActionEvent event) {
        try {
            if (isAjouter) {
                Bien bien = new Bien();
                Location location = new Location(dateDebut.getText(), dateFin.getText(), commentaire.getText(), bien.getBienById(idBien)); 
            } else {
                Location location = new Location();
                Utilisateur locataire = new Utilisateur();
                if (idLocataire != 0) {
                    locataire = locataire.getUtilisateurById(idLocataire);
                } else {
                    locataire = null;
                }
                Bien bien = locationToModify.getBien();
                location.updateAll(locationToModify.getId(), dateDebut.getText(), dateFin.getText(), commentaire.getText(), locataire, bien.getBienById(idBien));
            }
            root = FXMLLoader.load(getClass().getResource("../Location/Locations.fxml"));
            stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setData(Location location) {
        locationToModify = location;
        isAjouter = false;
        dateDebut.setText(location.getDateDebut());
        dateFin.setText(location.getDateFin());
        commentaire.setText(location.getCommentaire());
        menuButton.setText(location.getBien().getNom());
        idBien = location.getBien().getId();
        if (location.getLocataire() != null) {
            idLocataire = location.getLocataire().getId();
        } else {
            idLocataire = 0;
        }
    }

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
            biens = jdbcDataAccess.getBienWithoutLocation();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Bien bien : biens) {
            MenuItem menuItem = new MenuItem(bien.getNom());
            menuItem.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    menuButton.setText(bien.getNom());
                    idBien = bien.getId();
                }
            });
            menuButton.getItems().add(menuItem);
        }
    }
}
