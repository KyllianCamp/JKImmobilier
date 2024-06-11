package View.Location;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Component.CadreLocation.CadreLocationController;
import Location.Location;
import Persist.jdbcDataAccess;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LocationController implements Initializable{
    @FXML 
    private VBox layout;

    @FXML
    private HBox listLocation;

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
}
