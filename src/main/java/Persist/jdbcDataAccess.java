package Persist;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Location.Bien;
import Location.Caracteristique;
import Location.Location;
import Location.Utilisateur;

import io.github.cdimascio.dotenv.*;

public class jdbcDataAccess {
    private static Connection connection;
    private static final String URL = Dotenv.load().get("BDD_URL") ;
    private static final String USER = Dotenv.load().get("BDD_USER");
    private static final String PASSWORD = Dotenv.load().get("BDD_PASSWD");

    public Connection jdbcDataAccess() throws SQLException, SQLException {
        connection = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }

    public List<Utilisateur> getTiers() throws SQLException {
        try {
            String sql = "SELECT * FROM utilisateur";
            Connection connection = jdbcDataAccess();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            List<Utilisateur> utilisateurs = new ArrayList<>();
            Utilisateur utilisateur = new Utilisateur();
            if (resultSet == null) {
                System.out.println("Aucun utilisateur trouvé");
            } else {
                while (resultSet.next()) {
                    utilisateurs.add(utilisateur.getUtilisateurById(resultSet.getInt("id")));
                }
            }
            return utilisateurs;
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }
        return null;
    }

    public List<Bien> getBiens() throws SQLException {
        try {
            String sql = "SELECT * FROM bien";
            Connection connection = jdbcDataAccess();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            List<Bien> biens = new ArrayList<>();
            if (resultSet == null) {
                System.out.println("Aucun bien trouvé");
            } else {
                while (resultSet.next()) {
                    Bien bien = new Bien(resultSet.getInt("id"), resultSet.getString("nom"),
                            resultSet.getString("adresse"), resultSet.getString("codePostal") , resultSet.getInt("nbPieces"),
                            resultSet.getInt("surface"), resultSet.getString("description"), resultSet.getInt("loyer"), 
                            resultSet.getString("type"));
                    biens.add(bien);
                }
            }
            return biens;
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }
        return null;
    }

    public List<Bien> getBienWithoutLocation() throws SQLException {
        try {
            String sql = "SELECT b.*, l.*\n" + //
                                "FROM Bien b\n" + //
                                "LEFT JOIN Location l ON b.id = l.idBien\n" + //
                                "WHERE l.id IS NULL \n" + //
                                "   OR (l.dateFin IS NOT NULL AND l.dateFin != \"\" AND l.dateFin < CURRENT_DATE);";
            Connection connection = jdbcDataAccess();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            List<Bien> biens = new ArrayList<>();
            if (resultSet == null) {
                System.out.println("Aucun bien trouvé");
            } else {
                while (resultSet.next()) {
                    Bien bien = new Bien(resultSet.getInt("id"), resultSet.getString("nom"),
                            resultSet.getString("adresse"), resultSet.getString("codePostal") , resultSet.getInt("nbPieces"),
                            resultSet.getInt("surface"), resultSet.getString("description"), resultSet.getInt("loyer"), 
                            resultSet.getString("type"));
                    biens.add(bien);
                }
            }
            return biens;
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }
        return null;
    }

    public List<Location> getLocations() throws SQLException {
        try {
            String sql = "SELECT * FROM location";
            Connection connection = jdbcDataAccess();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            List<Location> locations = new ArrayList<>();
            Location location = new Location();
            if (resultSet == null) {
                System.out.println("Aucune location trouvée");
            } else {
                while (resultSet.next()) {
                    locations.add(location.getLocationById(resultSet.getInt("id")));
                }
            }
            return locations;
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }
        return null;
    }

    public List<Caracteristique> getCaracteristiques() throws SQLException {
        try {
            String sql = "SELECT * FROM caracteristique";
            Connection connection = jdbcDataAccess();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            List<Caracteristique> caracteristiques = new ArrayList<>();
            Caracteristique caracteristique = new Caracteristique();
            if (resultSet == null) {
                System.out.println("Aucun bien trouvé");
            } else {
                while (resultSet.next()) {
                    caracteristiques.add(caracteristique.getCaracteristiqueById(resultSet.getInt("id")));
                }
            }
            return caracteristiques;
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }
        return null;
    }
}
