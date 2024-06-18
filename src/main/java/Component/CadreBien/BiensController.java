package Component.CadreBien;

import java.io.File;
import java.io.IOException;

import Location.Bien;
import View.AjouterModifierBiens.AjouterBiensController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BiensController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ImageView imageView;
    @FXML
    private Label titleLabel;
    @FXML
    private Label locationLabel;
    @FXML
    private Label detailsLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Button modifyButton;
    @FXML
    private Button deleteButton;
    @FXML
    private VBox background;

    public void setData(Bien bien) {
        if (bien.getPhotographies().size() > 0) {
            File file = new File("./Photos/" + bien.getPhotographies().get(0).getLien());
            Image image = new Image(file.toURI().toString());
            imageView.setImage(image);
        }

        titleLabel.setText(bien.getNom());
        locationLabel.setText(bien.getAdresse() + ", " + bien.getCodePostal());
        detailsLabel.setText(bien.getNbPieces() + " pièces, " + bien.getSurface() + " m², " + bien.getLoyer() + " €");
        descriptionLabel.setText(bien.getDescription());

        // Ajouter les actions des boutons
        modifyButton.setOnAction(e -> modifyBien(bien));
        deleteButton.setOnAction(e -> deleteBien(bien));
        background.setOnMouseClicked(e -> showBien(bien));
    }

    private void modifyBien(Bien bien) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/AjouterModifierBiens/ajouterModifierBiens.fxml"));
            root = loader.load();
            AjouterBiensController controller = loader.getController();
            controller.setData(bien);
            stage = (Stage) modifyButton.getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteBien(Bien bien) {
        Bien bienToDelete = new Bien();
        bienToDelete.delete(bien.getId());
        try {
            root = FXMLLoader.load(getClass().getResource("../../View/Biens/Biens.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = (Stage) deleteButton.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void showBien(Bien bien) {
        System.out.println(bien.getNom());
    }
}

