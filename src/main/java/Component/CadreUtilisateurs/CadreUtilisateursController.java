package Component.CadreUtilisateurs;

import java.io.IOException;

import Location.Bien;
import Location.Utilisateur;
import View.AjouterModifierBiens.AjouterBiensController;
import View.AjouterUsers.AjouterModifierUsersController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CadreUtilisateursController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public Label nom;

    @FXML
    public Label prenom;

    @FXML
    public Label mail;

    @FXML
    public Label telephone;

    @FXML
    private Button modifyButton;
    @FXML
    private Button deleteButton;

    public void setData(Utilisateur utilisateur) {
        nom.setText(utilisateur.getNom());
        prenom.setText(utilisateur.getPrenom());
        mail.setText(utilisateur.getMail());
        telephone.setText(utilisateur.getTelephone());

        modifyButton.setOnAction(e -> modifyUser(utilisateur));
        deleteButton.setOnAction(e -> deleteUser(utilisateur));
    }

    private void modifyUser(Utilisateur utilisateur) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/AjouterUsers/AjouterModifierUsers.fxml"));
            root = loader.load();
            AjouterModifierUsersController controller = loader.getController();
            controller.setData(utilisateur);
            stage = (Stage) modifyButton.getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteUser(Utilisateur utilisateur) {
        utilisateur.delete();
        try {
            root = FXMLLoader.load(getClass().getResource("../../View/Utilisateurs/Utilisateurs.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = (Stage) deleteButton.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
