package View.AjouterModifierBiens;

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

public class AjouterBiensController implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent root;

    jdbcDataAccess jdbcDataAccess = new jdbcDataAccess();

    private int idProprietaire;

    List<Utilisateur> utilisateurs;

    @FXML
    private int idBien;

    @FXML
    private boolean isAjouter = true;

    @FXML
    private VBox layout;

    @FXML
    private MenuButton menuButton;

    @FXML
    private ListView<?> listView;

    @FXML
    private TextField nbPiece;

    @FXML
    private TextField surface;

    @FXML
    private TextField loyer;

    @FXML
    private TextField codePostal;

    @FXML
    private TextField adresse;

    @FXML
    private TextField title;

    @FXML
    private TextField description;

    @FXML
    private TextField type;

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
        
        try {
            utilisateurs = jdbcDataAccess.getTiers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Utilisateur utilisateur : utilisateurs) {
            MenuItem menuItem = new MenuItem(utilisateur.getNom());
            menuItem.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    menuButton.setText(utilisateur.getNom());
                    idProprietaire = utilisateur.getId();
                }
            });
            menuButton.getItems().add(menuItem);
        }

        nbPiece.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                nbPiece.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        surface.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[\\d,\\.]*")) {
                surface.setText(newValue.replaceAll("[^\\d,\\.]", ""));
            }
        });
    }

    @FXML
    public void clickOnSaveButton(ActionEvent event) throws IOException{
        if (isAjouter) {
            Utilisateur proprietaire = new Utilisateur();
            Bien bien = new Bien(
                title.getText(),
                adresse.getText(),
                codePostal.getText(),
                Integer.parseInt(nbPiece.getText()),
                Integer.parseInt(surface.getText()),
                description.getText(),
                Integer.parseInt(loyer.getText()),
                type.getText(),
                proprietaire.getUtilisateurById(idProprietaire)
            );
        } else {
            Utilisateur proprietaire = new Utilisateur();
            Bien bien = new Bien();
            bien.updateAll(idBien, title.getText(), adresse.getText(), codePostal.getText(), Integer.parseInt(nbPiece.getText()), Integer.parseInt(surface.getText()), description.getText(), Integer.parseInt(loyer.getText()), type.getText(), proprietaire.getUtilisateurById(idProprietaire));
        }
        root = FXMLLoader.load(getClass().getResource("../Biens/Biens.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void clickOnStopButton(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../Biens/Biens.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setData(Bien bien) {
        isAjouter = false;
        idBien = bien.getId();
        title.setText(bien.getNom());
        adresse.setText(bien.getAdresse());
        codePostal.setText(bien.getCodePostal());
        nbPiece.setText(String.valueOf(bien.getNbPieces()));
        surface.setText(String.valueOf(bien.getSurface()));
        description.setText(bien.getDescription());
        loyer.setText(String.valueOf(bien.getLoyer()));
        type.setText(bien.getType());
        menuButton.setText(bien.getProprietaire().getNom());
        idProprietaire = bien.getProprietaire().getId();
    }

}
