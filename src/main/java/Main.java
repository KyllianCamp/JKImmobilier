import java.io.IOException;

import Location.Utilisateur;
import io.github.cdimascio.dotenv.Dotenv;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application{
    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.OFF);
         // Chargez les variables d'environnement à partir du fichier .env
        Dotenv dotenv = Dotenv.load();

        Utilisateur utilisateur = new Utilisateur();
        Utilisateur utilisateur2 = utilisateur.getUtilisateurById(1);


        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/Biens/Biens.fxml"));
        root.getStylesheets().add(getClass().getResource("View/Biens/styles.css").toExternalForm());
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
