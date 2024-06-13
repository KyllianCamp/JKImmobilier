package View.AjouterLocation;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import Location.Bien;
import Location.Location;
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
import javafx.stage.Stage;

public class AjouterLocationController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    List<Bien> biens;

    jdbcDataAccess jdbcDataAccess = new jdbcDataAccess();

    private int idBien;

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
                Location location = new Location(dateDebut.getText(), dateFin.getText(), commentaire.getText(), null);
            } else {
                // Location location = new Location(dateDebut.getText(), dateFin.getText(), commentaire.getText(), null);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {   
            biens = jdbcDataAccess.getBiens();
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
