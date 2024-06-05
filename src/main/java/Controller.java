import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import Location.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Controller implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private AnchorPane layout;

    @FXML
    private ListView<String> listView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Utilisateur utilisateur = new Utilisateur();
        Utilisateur utilisateur2 = utilisateur.getUtilisateurById(1);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            list.add(utilisateur2.getNom());
        }
        System.out.println(list);
    }

    @FXML
    public void onBtnClick(ActionEvent event) throws IOException {

        System.out.println("Button Clicked!");
        // Parent root = FXMLLoader.load(getClass().getResource("Biens.fxml"));
        // stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        // scene = new Scene(root);

        //Add a button to the scene
        Button button = new Button("Click me!");
        button.setOnAction(e -> System.out.println("Button Clicked!"));

        //Add the button to the scene
        layout.getChildren().add(button);
        
        // stage.setScene(scene);
        // stage.show();
    }

}