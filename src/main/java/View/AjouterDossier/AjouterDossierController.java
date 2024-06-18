package View.AjouterDossier;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import Location.Bien;
import Location.Dossier;
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
import javafx.stage.Stage;

public class AjouterDossierController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private int idCandidat;

    @FXML
    public MenuButton menuButton;

    @FXML
    public int idBien;
    
    @FXML
    public void ajouterDossier(ActionEvent event) {
        System.out.println(idCandidat);
        String today = new java.sql.Date(System.currentTimeMillis()).toString();
        Utilisateur candidat = new Utilisateur();
        Bien bien = new Bien();
        Dossier dossier = new Dossier(today, "temp", candidat.getUtilisateurById(idCandidat), bien.getBienById(idBien));
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

    public void setData(Location location) {
        idBien = location.getBien().getId();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jdbcDataAccess jdbc = new jdbcDataAccess();
        try {
            List<Utilisateur> utilisateurs = jdbc.getTiers();
            for (Utilisateur utilisateur : utilisateurs) {
                MenuItem menuItem = new MenuItem(utilisateur.getNom() + " " + utilisateur.getPrenom());
                menuItem.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        menuButton.setText(utilisateur.getNom() + " " + utilisateur.getPrenom());
                        idCandidat = utilisateur.getId();
                    }
                });
                menuButton.getItems().add(menuItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}