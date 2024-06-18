package View.AjouterPreavis;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import Location.Bien;
import Location.Location;
import Location.Preavis;
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
import javafx.stage.Stage;

public class AjouterPreavisController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    List<Bien> biens;

    jdbcDataAccess jdbcDataAccess = new jdbcDataAccess();

    private int idBien;
    private int idLocataire;

    private Preavis preavisToModify;

    @FXML
    public Boolean isAjouter = true;

    @FXML
    public TextField dateFin;

    @FXML
    public TextField motif;
    
    @FXML
    public MenuButton menuButton;

    private int idLoc;

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

    public void setData(Preavis preavis) {
        preavisToModify = preavis;
        isAjouter = false;
        dateFin.setText(preavis.getDateDepart());
        motif.setText(preavis.getMotif());
    }

    public void setLocation(int idLocation) {
        idLoc = idLocation;
    }

    @FXML
    public void ajouterPreavis(ActionEvent event) {
        try {
            if (isAjouter) {
                String dateDebut = LocalDate.now().toString();
                Location location = new Location();
                Preavis preavis = new Preavis(dateDebut, dateFin.getText(), motif.getText(), location.getLocationById(idLoc));
            } else {
                Location location = new Location();
                Utilisateur locataire = new Utilisateur();
                if (idLocataire != 0) {
                    locataire = locataire.getUtilisateurById(idLocataire);
                } else {
                    locataire = null;
                }
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
