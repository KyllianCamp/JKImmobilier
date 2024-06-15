package Component.CadreLocation;

import java.io.IOException;

import Location.Location;
import View.AjouterDossier.AjouterDossierController;
import View.AjouterLocation.AjouterLocationController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CadreLocationController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label nom;

    @FXML
    private Label position;

    @FXML
    private Label paiement;

    @FXML
    private Label proprietaire;

    @FXML
    private Label locataire;

    @FXML
    private Label prix;

    @FXML
    private Button modifyButton;
    @FXML
    private Button deleteButton;
    @FXML 
    private AnchorPane background;

    public void setValues(Location location) {
        nom.setText(location.getBien().getNom());
        position.setText(location.getBien().getAdresse());
        if (location.getLocataire() == null) {
            paiement.setText("Paiement : Aucun locataire");
            locataire.setText("Locataire : Aucun locataire");
        } else {
            paiement.setText("Locataire : " + location.getLocataire().getNom() + " " + location.getLocataire().getPrenom());
            locataire.setText("Locataire : " + location.getLocataire().getNom() + " " + location.getLocataire().getPrenom() + ", " + location.getLocataire().getMail() + " (" + location.getLocataire().getTelephone() + ")");
        }
        prix.setText(location.getBien().getLoyer() + "€" );
        proprietaire.setText("Propriétaire : " + location.getBien().getProprietaire().getNom() + " " + location.getBien().getProprietaire().getPrenom() + ", " + location.getBien().getProprietaire().getMail() + " (" + location.getBien().getProprietaire().getTelephone() + ")");

        modifyButton.setOnAction(e -> modifyLocation(location));

        //Add Button component to background if location is not null
        if (location.getLocataire() == null) {
            Button button = new Button("Ajouter un dossier locataire");
            background.getChildren().add(button);
            button.setOnAction(e -> addDossier(location));
            button.setLayoutX(370);
            button.setLayoutY(195);
        }
    }

    private void modifyLocation(Location location) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/AjouterLocation/AjouterLocation.fxml"));
            root = loader.load();
            AjouterLocationController controller = loader.getController();
            controller.setData(location);
            Stage stage = (Stage) nom.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addDossier(Location location) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/AjouterDossier/AjouterDossier.fxml"));
            root = loader.load();
            AjouterDossierController controller = loader.getController();
            controller.setData(location);
            Stage stage = (Stage) nom.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
