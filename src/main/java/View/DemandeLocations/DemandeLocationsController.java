package View.DemandeLocations;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class DemandeLocationsController implements Initializable{
    @FXML 
    private VBox layout;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Component/Header/Header.fxml"));
        try {
            Parent component = loader.load();
            layout.getChildren().add(0, component);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
