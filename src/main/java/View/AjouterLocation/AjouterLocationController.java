package View.AjouterLocation;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.scene.control.DatePicker;
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
    private int idLocataire;

    private Location locationToModify;

    @FXML
    public Boolean isAjouter = true;

    @FXML
    public DatePicker dateDebut;

    @FXML
    public DatePicker dateFin;

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
                Bien bien = new Bien();
                String dateDebutString = dateDebut.getValue().toString();
                Location location = new Location(dateDebutString, commentaire.getText(), bien.getBienById(idBien)); 
            } else {
                Location location = new Location();
                Utilisateur locataire = new Utilisateur();
                if (idLocataire != 0) {
                    locataire = locataire.getUtilisateurById(idLocataire);
                } else {
                    locataire = null;
                }
                Bien bien = locationToModify.getBien();
                String dateDebutString = dateDebut.getValue().toString();
                String dateFinString = dateFin.getValue().toString();
                location.updateAll(locationToModify.getId(), dateDebutString, dateFinString, commentaire.getText(), locataire, bien.getBienById(idBien));
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
        dateDebut.setValue(dateDebut.getConverter().fromString(location.getDateDebut()));
        dateFin.setValue(dateFin.getConverter().fromString(location.getDateFin()));
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
