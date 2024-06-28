package controller;

import modele.ClientEntity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientService {

    public boolean addClient(ClientEntity client) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Configurer la connexion à la base de données
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/votre_base_de_donnees", "votre_utilisateur", "votre_mot_de_passe");

            // Préparer la requête SQL
            String sql = "INSERT INTO clients (id, prenom, nom, ville) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, client.getId());
            statement.setString(2, client.getPrenom());
            statement.setString(3, client.getNom());
            statement.setString(4, client.getVille());

            // Exécuter la requête
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Fermer les ressources
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
