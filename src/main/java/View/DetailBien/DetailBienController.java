package View.DetailBien;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Location.Bien;
import Location.CaracteristiqueBien;
import Location.Location;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DetailBienController implements Initializable{

    @FXML
    private VBox layout;

    @FXML
    private ImageView imageV;

    @FXML
    private Label titleLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private Label detailsLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label nomProprio;

    @FXML
    private Label contactProprio;

    @FXML
    private Label telProprio;

    @FXML
    private HBox caracteristiques;

    @FXML
    private HBox locations;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Component/Header/Header.fxml"));
        try {
            Parent component = loader.load();
            layout.getChildren().add(0, component);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setData(Bien bien) {
        if (bien.getPhotographies().size() > 0) {
            File file = new File("./Photos/" + bien.getPhotographies().get(bien.getPhotographies().size()-1).getLien());
            Image image = new Image(file.toURI().toString());
            imageV.setImage(image);
        }

        titleLabel.setText(bien.getNom());
        locationLabel.setText(bien.getAdresse() + ", " + bien.getCodePostal());
        detailsLabel.setText(bien.getNbPieces() + " pièces, " + bien.getSurface() + " m², " + bien.getLoyer() + " €");
        descriptionLabel.setText(bien.getDescription());

        nomProprio.setText(bien.getProprietaire().getNom() + " " + bien.getProprietaire().getPrenom());
        contactProprio.setText(bien.getProprietaire().getMail());
        telProprio.setText(bien.getProprietaire().getTelephone());

        
        for (CaracteristiqueBien caract : bien.getCaracteristiqueBiens()) {
                Label label = new Label(caract.getCaracteristique().getNom() + ",");
                label.setFont(new javafx.scene.text.Font("Arial", 20));
                caracteristiques.getChildren().add(label);
        }

        for (Location location : bien.getLocations()) {
            if (location.getLocataire() == null) continue;
            Label label = new Label(location.getLocataire().getNom() + " " + location.getLocataire().getPrenom() + " : " + location.getDateDebut() + " - " + location.getDateFin());
            label.setFont(new javafx.scene.text.Font("Arial", 20));
            locations.getChildren().add(label);
        }
    }
}
