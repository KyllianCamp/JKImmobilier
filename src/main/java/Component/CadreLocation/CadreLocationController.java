package Component.CadreLocation;

import Location.Location;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CadreLocationController {

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

    public void setValues(Location location) {
        System.out.println(location);
        nom.setText(location.getBien().getNom());
        position.setText(location.getBien().getAdresse());
        // paiement.setText(String.valueOf(location.getPaiements().size()));
        if (location.getLocataire() == null) {
            paiement.setText("Paiement : Aucun locataire");
            locataire.setText("Aucun locataire");
        } else {
            paiement.setText("Locataire : " + location.getLocataire().getNom() + " " + location.getLocataire().getPrenom());
            locataire.setText(location.getLocataire().getNom() + " " + location.getLocataire().getPrenom() + ", " + location.getLocataire().getMail() + " (" + location.getLocataire().getTelephone() + ")");
        }
        prix.setText(location.getBien().getLoyer() + "€" );
        proprietaire.setText(location.getBien().getProprietaire().getNom() + " " + location.getBien().getProprietaire().getPrenom() + ", " + location.getBien().getProprietaire().getMail() + " (" + location.getBien().getProprietaire().getTelephone() + ")");
    }
}
