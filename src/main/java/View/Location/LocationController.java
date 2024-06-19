package View.Location;

import java.beans.EventHandler;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Component.CadreLocation.CadreLocationController;
import Location.Location;
import Persist.jdbcDataAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LocationController implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML 
    private VBox layout;

    @FXML
    private HBox listLocation;

    @FXML
    private TextField textField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Component/Header/Header.fxml"));
        try {
            Parent component = loader.load();
            layout.getChildren().add(0, component);
        } catch (IOException e) {
            e.printStackTrace();
        }
        jdbcDataAccess dataAccess = new jdbcDataAccess();
        List<Location> locations = new ArrayList<>();
        try {
            locations = dataAccess.getLocations();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Location locationObj : locations) {
            loader = new FXMLLoader(getClass().getResource("../../Component/CadreLocation/CadreLocation.fxml"));
            try {
                Parent component = loader.load();
                CadreLocationController controller = loader.getController();
                controller.setValues(locationObj);
                listLocation.getChildren().add(component);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void goToAjouterLocation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../AjouterLocation/AjouterLocation.fxml"));
            root = loader.load();
            scene = new Scene(root);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void filter(ActionEvent event) {
        jdbcDataAccess dataAccess = new jdbcDataAccess();
        List<Location> locations = new ArrayList<>();
        try {
            locations = dataAccess.getLocations();
        } catch (Exception e) {
            e.printStackTrace();
        }
        listLocation.getChildren().clear();
        for (Location locationObj : locations) {
            if (!locationObj.getBien().getNom().contains(textField.getText())) {
                continue;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Component/CadreLocation/CadreLocation.fxml"));
            try {
                Parent component = loader.load();
                CadreLocationController controller = loader.getController();
                controller.setValues(locationObj);
                listLocation.getChildren().add(component);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
