/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author 2417033
 */
import java.util.List;
import database.ZooDatabaseManager;
import model.Animal;
import model.Enclos;

public class ZooService {
    private ZooDatabaseManager dbManager = ZooDatabaseManager.getInstance();

    public void ajouterAnimal(int id,String nom, String espece, int age, String regimeAlimentaire) {
        dbManager.ajouterAnimal(new Animal(id, nom, espece, age, regimeAlimentaire));
    }

    public List<Animal> listerAnimaux() {
        return dbManager.listerAnimaux();
    }
    
    public List<Enclos> listerEnclos() {
        return dbManager.listerEnclos();
    }
    public void mettreAJourAnimal(int id, String nom, String espece, int age, String regimeAlimentaire) {
        dbManager.mettreAJourAnimal(new Animal(id, nom, espece, age, regimeAlimentaire));
    }


    public void supprimerAnimal(int id) {
        dbManager.supprimerAnimal(id);
    }
}
