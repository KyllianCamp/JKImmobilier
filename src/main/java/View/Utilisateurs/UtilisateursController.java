package View.Utilisateurs;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Component.CadreBien.BiensController;
import Component.CadreUtilisateurs.CadreUtilisateursController;
import Component.Header.HeaderController;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UtilisateursController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private VBox layout;

    @FXML
    private HBox dynamicContainer;

    @FXML
    private TextField textField;

    @FXML
    public void goToAjouterUser(ActionEvent event) throws IOException {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/AjouterUsers/ajouterModifierUsers.fxml"));
            root = loader.load();
            
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

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
        jdbcDataAccess jdbcDataAccess = new jdbcDataAccess();
        List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
        try {
            utilisateurs = jdbcDataAccess.getTiers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Utilisateur utilisateur : utilisateurs) {
            loader = new FXMLLoader(getClass().getResource("../../Component/CadreUtilisateurs/CadreUtilisateurs.fxml"));
            Utilisateur newUtilisateur = utilisateur.getUtilisateurById(utilisateur.getId());
            try {
                Parent component = loader.load();
                CadreUtilisateursController controller = loader.getController();
                controller.setData(newUtilisateur);
                dynamicContainer.getChildren().add(component);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void filter(ActionEvent event) {
        jdbcDataAccess jdbcDataAccess = new jdbcDataAccess();
        List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
        try {
            utilisateurs = jdbcDataAccess.getTiers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dynamicContainer.getChildren().clear();
        for (Utilisateur utilisateur : utilisateurs) {
            if ((!utilisateur.getNom().contains(textField.getText())) && (!utilisateur.getPrenom().contains(textField.getText()))){
                continue;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Component/CadreUtilisateurs/CadreUtilisateurs.fxml"));
            Utilisateur newUtilisateur = utilisateur.getUtilisateurById(utilisateur.getId());
            try {
                Parent component = loader.load();
                CadreUtilisateursController controller = loader.getController();
                controller.setData(newUtilisateur);
                dynamicContainer.getChildren().add(component);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
