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
import View.AjouterModifierBiens.AjouterBiensController;
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
    private TextField textField;

    @FXML
    private HBox dynamicContainer;

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
                dynamicContainer.getChildren().add(component);
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
    public void goToAjouterBiens(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../AjouterModifierBiens/ajouterModifierBiens.fxml"));
            root = loader.load();
            AjouterBiensController controller = loader.getController();
            
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}