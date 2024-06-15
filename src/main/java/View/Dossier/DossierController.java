package View.Dossier;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Component.CadreBien.BiensController;
import Component.CadreDossier.CadreDossierController;
import Component.Header.HeaderController;
import Location.Bien;
import Location.Dossier;
import Persist.jdbcDataAccess;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DossierController implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML 
    private HBox dynamicContainer;
    
    @FXML
    private VBox layout;

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
        jdbcDataAccess dataAccess = new jdbcDataAccess();
        List<Dossier> dossiers = new ArrayList<>();
        try {
            dossiers = dataAccess.getDossiers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            for (Dossier dossier : dossiers) {
                FXMLLoader loaderDossier = new FXMLLoader(getClass().getResource("../../Component/CadreDossier/CadreDossier.fxml"));
                Parent componentDossier = loaderDossier.load();
                CadreDossierController controllerDossier = loaderDossier.getController();
                controllerDossier.setData(dossier);
                dynamicContainer.getChildren().add(componentDossier);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
