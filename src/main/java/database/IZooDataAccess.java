/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package database;

/**
 *
 * @author 2417033
 */

import model.Animal;
import model.Enclos;
import java.util.List;

public interface IZooDataAccess {
    void ajouterAnimal(Animal animal);
    List<Animal> listerAnimaux();
    void mettreAJourAnimal(Animal animal);
    void supprimerAnimal(int id);
    
    List<Enclos> listerEnclos();
}
