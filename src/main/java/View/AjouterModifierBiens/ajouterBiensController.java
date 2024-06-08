package View.AjouterModifierBiens;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ajouterBiensController implements Initializable{

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

}
