/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

/**
 *
 * @author 2417033
 */

import model.Animal;
import model.Enclos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ZooDatabaseManager implements IZooDataAccess {
    private static ZooDatabaseManager instance;
    private Connection connection;

    private ZooDatabaseManager() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:zoo.db");
            initialiserTables();
        } catch (SQLException e) {
            System.out.println("Erreur de connexion SQLite : " + e.getMessage());
        }
    }

    public static ZooDatabaseManager getInstance() {
        if (instance == null) {
            instance = new ZooDatabaseManager();
        }
        return instance;
    }

    private void initialiserTables() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS animaux (id INTEGER PRIMARY KEY, nom TEXT, espece TEXT, age INTEGER, regimeAlimentaire TEXT)");
        } catch (SQLException e) {
            System.out.println("Erreur création de tables : " + e.getMessage());
        }
    }

    @Override
    public void ajouterAnimal(Animal animal) {
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO animaux (id,nom, espece, age, regimeAlimentaire) VALUES (?,?, ?, ?, ?)")) {
            stmt.setInt(1, animal.getId());
            stmt.setString(2, animal.getNom());
            stmt.setString(3, animal.getEspece());
            stmt.setInt(4, animal.getAge());
            stmt.setString(5, animal.getRegimeAlimentaire());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur ajout animal : " + e.getMessage());
        }
    }

    @Override
    public List<Animal> listerAnimaux() {
        List<Animal> animaux = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM animaux")) {
            while (rs.next()) {
                animaux.add(new Animal(rs.getInt("id"), rs.getString("nom"), rs.getString("espece"), rs.getInt("age"), rs.getString("regimeAlimentaire")));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lecture animaux : " + e.getMessage());
        }
        return animaux;
    }
    @Override
    public List<Enclos> listerEnclos() {
        List<Enclos> enclosList = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM enclos")) {
            while (rs.next()) {
                enclosList.add(new Enclos(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getInt("capacite"),
                    rs.getString("typeHabitat")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des enclos : " + e.getMessage());
        }
        return enclosList;
    }
    @Override
    public void mettreAJourAnimal(Animal animal) {
        String sql = "UPDATE animaux SET nom = ?, espece = ?, age = ?, regimeAlimentaire = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, animal.getNom());
            stmt.setString(2, animal.getEspece());
            stmt.setInt(3, animal.getAge());
            stmt.setString(4, animal.getRegimeAlimentaire());
            stmt.setInt(5, animal.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de l'animal : " + e.getMessage());
        }
    }
    @Override
    public void supprimerAnimal(int id) {
        String sql = "DELETE FROM animaux WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de l'animal : " + e.getMessage());
        }
    }
}

