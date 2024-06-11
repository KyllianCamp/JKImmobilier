package View.Location;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
        for (int i = 0; i < 2; i++) {
            loader = new FXMLLoader(getClass().getResource("../../Component/CadreLocation/CadreLocation.fxml"));
            try {
                Parent component = loader.load();
                listLocation.getChildren().add(component);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
