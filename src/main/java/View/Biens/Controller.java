package View.Biens;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import Component.CadreBien.BiensController;
import Location.Bien;
import Location.Utilisateur;
import Persist.jdbcDataAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
    private TextField textField;

    // @FXML
    // private TextField numberField;

    // @FXML
    // private TextField numberFieldSurface;

    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jdbcDataAccess jdbcDataAccess = new jdbcDataAccess();
        List<Bien> biens = new ArrayList<Bien>();
        try {
            biens = jdbcDataAccess.getBiens();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Bien bien : biens) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Component/CadreBien/CadreBien.fxml"));
            try {
                Parent component = loader.load();
                BiensController controller = loader.getController();
                controller.setData(bien);
                component.getStylesheets().add(getClass().getResource("../../Component/CadreBien/styles.css").toExternalForm());
                layout.getChildren().add(component);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // numberField.textProperty().addListener((observable, oldValue, newValue) -> {
        //     if (!newValue.matches("\\d*")) {
        //         numberField.setText(newValue.replaceAll("[^\\d]", ""));
        //     }
        // });
        // numberFieldSurface.textProperty().addListener((observable, oldValue, newValue) -> {
        //     if (!newValue.matches("[\\d,\\.]*")) {
        //         numberFieldSurface.setText(newValue.replaceAll("[^\\d,\\.]", ""));
        //     }
        // });
    }

    @FXML
    public void onBtnClick(ActionEvent event) throws IOException {
        System.out.println(textField.getText());
    }

}