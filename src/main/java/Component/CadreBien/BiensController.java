package Component.CadreBien;

import java.io.File;

import Location.Bien;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BiensController {
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

    public void setData(Bien bien) {
        String localImagePath = "/Users/kyllian/Pictures/24h du Mans/DSC06545.JPG";
        // Convert the local file path to a URL
        File file = new File(localImagePath);
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);

        titleLabel.setText(bien.getNom());
        locationLabel.setText(bien.getAdresse() + ", " + bien.getCodePostal());
        detailsLabel.setText(bien.getNbPieces() + " pièces, " + bien.getSurface() + " m², " + bien.getLoyer() + " €");
        descriptionLabel.setText(bien.getDescription());

        // Ajouter les actions des boutons
        modifyButton.setOnAction(e -> modifyBien(bien));
        deleteButton.setOnAction(e -> deleteBien(bien));
    }

    private void modifyBien(Bien bien) {
        // Logique pour modifier le bien
        System.out.println("Modifier le bien : " + bien.getId());
    }

    private void deleteBien(Bien bien) {
        // Logique pour supprimer le bien
        System.out.println("Supprimer le bien : " + bien.getId());
    }
}

