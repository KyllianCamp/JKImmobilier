package Component.CadreDossier;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import Location.Bien;
import Location.Dossier;
import Location.Location;
import Persist.jdbcDataAccess;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CadreDossierController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML 
    private Label dateSoumission;

    @FXML
    private Label statut;

    @FXML
    private Label candidat;

    @FXML
    private Label bien;

    @FXML
    private VBox background;

    public void setData(Dossier dossier) {
        dateSoumission.setText(dossier.getDateSoumission());
        statut.setText(dossier.getStatut());
        candidat.setText(dossier.getCandidat().getNom());
        bien.setText(dossier.getBien().getAdresse());

        if (dossier.getStatut().equals("temp")) {
            Button buttonValidate = new Button("Valider");
            background.getChildren().add(buttonValidate);
            buttonValidate.setOnAction(e -> {
                try {
                    validateDossier(dossier);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            });
            buttonValidate.setLayoutX(370);
            buttonValidate.setLayoutY(195);

            Button buttonRefuse = new Button("Refuser");
            background.getChildren().add(buttonRefuse);
            buttonRefuse.setOnAction(e -> refuseDossier(dossier));
            buttonRefuse.setLayoutX(370);
            buttonRefuse.setLayoutY(195);
        }
    }

    private void refuseDossier(Dossier dossier) {
        dossier.setStatut("Refusé");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/Dossier/Dossier.fxml"));
            root = loader.load();
            scene = new Scene(root);
            stage = (Stage) background.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validateDossier(Dossier dossier) throws SQLException {
        jdbcDataAccess dataAccess = new jdbcDataAccess();
        dossier.setStatut("Validé");
        List<Dossier> dossiers = dataAccess.getDossiersOfBien(dossier.getBien().getId());
        for (Dossier d : dossiers) {
            if (d.getStatut().equals("temp")) {
                d.setStatut("Refusé");
            }
        }
        Location location = dataAccess.getLastLocationOfBien(dossier.getBien().getId());
        location.updateAll(location.getId(), location.getDateDebut(), location.getDateFin(), location.getCommentaire(), dossier.getCandidat(), dossier.getBien());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/Dossier/Dossier.fxml"));
            root = loader.load();
            scene = new Scene(root);
            stage = (Stage) background.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
