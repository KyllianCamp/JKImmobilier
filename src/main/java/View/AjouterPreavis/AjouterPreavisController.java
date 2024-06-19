package View.AjouterPreavis;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import Component.Header.HeaderController;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
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
    private DatePicker dateFin;

    @FXML
    public Boolean isAjouter = true;

    @FXML
    public TextField motif;
    
    @FXML
    public MenuButton menuButton;

    private int idLoc;

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

    public void setData(Preavis preavis) {
        preavisToModify = preavis;
        isAjouter = false;
        dateFin.setValue(LocalDate.parse(preavis.getDateDepart()));
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
                String dateFinString=" ";
                if(dateFin.getValue() != null) {
                    dateFinString = dateFin.getValue().toString();
                }
                Preavis preavis = new Preavis(dateDebut, dateFinString , motif.getText(), location.getLocationById(idLoc));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Component/Header/Header.fxml"));
        try {
            Parent component = loader.load();
            HeaderController controller = loader.getController();
            layout.getChildren().add(0, component);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
