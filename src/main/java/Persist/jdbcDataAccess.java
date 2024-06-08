package Persist;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Location.Bien;
import Location.Utilisateur;

public class jdbcDataAccess {
    private static Connection connection;
    private static final String URL = "jdbc:mysql://localhost:8889/JKimmobilier";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

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
            if (resultSet == null) {
                System.out.println("Aucun utilisateur trouvé");
            } else {
                while (resultSet.next()) {
                    Utilisateur utilisateur = new Utilisateur(resultSet.getInt("id"), resultSet.getString("nom"),
                            resultSet.getString("prenom"), resultSet.getString("mail"), resultSet.getString("telephone"));
                    utilisateurs.add(utilisateur);
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
}
