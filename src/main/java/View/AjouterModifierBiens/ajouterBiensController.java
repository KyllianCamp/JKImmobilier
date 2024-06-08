package View.AjouterModifierBiens;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.stage.Stage;

public class AjouterBiensController implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private AnchorPane layout;

    @FXML
    private ListView<?> listView;

    @FXML
    private TextField numberField;

    @FXML
    private TextField numberFieldSurface;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("AjouterModifierBiens");
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
