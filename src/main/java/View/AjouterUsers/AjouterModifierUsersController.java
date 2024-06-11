package View.AjouterUsers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import Component.Header.HeaderController;
import Location.Bien;
import Location.Utilisateur;
import Persist.jdbcDataAccess;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AjouterModifierUsersController implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent root;

    jdbcDataAccess jdbcDataAccess = new jdbcDataAccess();

    @FXML
    private int idProprietaire;

    List<Utilisateur> utilisateurs;

    @FXML
    private boolean isAjouter = true; 

    @FXML
    private VBox layout;

    @FXML
    private ListView<?> listView;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField mail;

    @FXML
    private TextField telephone;


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

        telephone.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[\\d,\\.]*")) {
                telephone.setText(newValue.replaceAll("[^\\d,\\.]", ""));
            }
        });
    }

    public void setData(Utilisateur utilisateur) {
        isAjouter = false;
        nom.setText(utilisateur.getNom());
        prenom.setText(utilisateur.getPrenom());
        mail.setText(utilisateur.getMail());
        telephone.setText(utilisateur.getTelephone());
        idProprietaire = utilisateur.getId();
    }

    @FXML
    public void clickOnSaveButton(ActionEvent event) throws IOException{
        if (isAjouter) {
            Utilisateur utilisateur = new Utilisateur(nom.getText(), prenom.getText(), mail.getText(), telephone.getText());
        } else {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.updateAll(idProprietaire, nom.getText(), prenom.getText(), mail.getText(), telephone.getText());
        }
        root = FXMLLoader.load(getClass().getResource("../Utilisateurs/Utilisateurs.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void clickOnStopButton(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../Utilisateurs/Utilisateurs.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
