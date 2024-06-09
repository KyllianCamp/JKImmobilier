package View.AjouterModifierBiens;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Component.Header.HeaderController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AjouterBiensController implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private VBox layout;

    @FXML
    private ListView<?> listView;

    @FXML
    private TextField numberField;

    @FXML
    private TextField numberFieldSurface;

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
        numberField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                numberField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        numberFieldSurface.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[\\d,\\.]*")) {
                numberFieldSurface.setText(newValue.replaceAll("[^\\d,\\.]", ""));
            }
        });
    }

    @FXML
    public void clickOnSaveButton(ActionEvent event) throws IOException{
        System.out.println("Save");
    }
    
    @FXML
    public void clickOnStopButton(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../Biens/Biens.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
