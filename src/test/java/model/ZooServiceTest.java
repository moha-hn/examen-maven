/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author 2417033
 */

import controller.ZooService;
import database.ZooDatabaseManager;
import model.Animal;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Assure un ordre d'exécution des tests
class ZooServiceTest {
    private static ZooService service;

    @BeforeAll
    static void setup() {
        service = new ZooService();
    }

    @Test
    @Order(1)
    void testAjouterAnimal() {
        // Ajouter un animal
        service.ajouterAnimal(1,"Lion", "Panthera leo", 5, "Carnivore");

        // Vérifier si l'animal a été ajouté en listant tous les animaux
        List<Animal> animaux = service.listerAnimaux();
        boolean animalAjoute = animaux.stream().anyMatch(a -> a.getNom().equals("Lion"));

        assertTrue(animalAjoute, "L'animal doit être ajouté à la base de données.");
    }

    @Test
    @Order(2)
    void testMettreAJourAnimal() {
        // Trouver l'animal ajouté
        List<Animal> animaux = service.listerAnimaux();
        Animal animal = animaux.stream()
                .filter(a -> a.getNom().equals("Lion"))
                .findFirst()
                .orElseThrow(() -> new AssertionError("L'animal n'a pas été trouvé !"));

        // Modifier l'animal
        service.mettreAJourAnimal(animal.getId(), "Lion Modifié", "Panthera leo", 6, "Carnivore");

        // Vérifier si la modification a été prise en compte
        List<Animal> animauxModifies = service.listerAnimaux();
        boolean modificationOk = animauxModifies.stream().anyMatch(a -> a.getNom().equals("Lion Modifié"));

        assertTrue(modificationOk, "L'animal doit être mis à jour.");
    }

    @Test
    @Order(3)
    void testSupprimerAnimal() {
        // Trouver l'animal ajouté
        List<Animal> animaux = service.listerAnimaux();
        Animal animal = animaux.stream()
                .filter(a -> a.getNom().equals("Lion Modifié"))
                .findFirst()
                .orElseThrow(() -> new AssertionError("L'animal n'a pas été trouvé !"));

        // Supprimer l'animal
        service.supprimerAnimal(animal.getId());

        // Vérifier que l'animal a bien été supprimé
        List<Animal> animauxApresSuppression = service.listerAnimaux();
        boolean animalSupprime = animauxApresSuppression.stream().noneMatch(a -> a.getNom().equals("Lion Modifié"));

        assertTrue(animalSupprime, "L'animal doit être supprimé.");
    }
}
