package org.example.menu;

import org.example.model.*;
import org.example.shelter.Shelter;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ConsoleMenu {
    private final Shelter<Animal> shelter;
    private final Scanner scanner =  new Scanner(System.in);
    public ConsoleMenu(Shelter<Animal> shelter) {
        this.shelter = shelter;
    }

    private void addAnimal() {
        System.out.println("Animal name: ");
        String name = scanner.nextLine();

        System.out.println("Animal age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.println("Animal species (dog/cat/bird): ");
        String species = scanner.nextLine();

        Animal animal = switch (species.toLowerCase()) {
            case "dog" -> new Dog(new AnimalId(), name, age);
            case "cat" -> new Cat(new AnimalId(), name, age);
            case "bird" -> new Bird(new AnimalId(), name, age);
            default -> null;
        };

        if (animal == null) {
            System.out.println("Unknown species: " + species);
            return;
        }

        shelter.addAnimal(animal);
        System.out.println("Animal added: " + animal);
    }

    private void printAnimals(List<Animal> animals) {
        if (animals.isEmpty()) {
            System.out.println("No animals were found or invalid input");
            return;
        }
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }

    private void showAllAnimals() {
        printAnimals(shelter.getAllAnimals());
    }

    private void findAnimalsBySpecies() {
        System.out.println("Write species: ");
        String species = scanner.nextLine();

        printAnimals(shelter.findBySpecies(species));
    }

    private void listAvailableAnimals() {
        System.out.println("Available animals: ");
        printAnimals(shelter.findAvailableAnimals());
    }

    private void markAnimalAsAdopted() {
        listAvailableAnimals();
        System.out.println("Please copy&paste animal id: ");
        String id = scanner.nextLine();
        if (shelter.markAsAdopted(id)) {
            System.out.println("Animal adopted!");
        } else {
            System.out.println("No animal found with ID: " + id);
        }
    }

    public void start(){
        while (true) {
            printMenu();
            String option = scanner.nextLine();
            switch (option) {
                case "1" -> addAnimal();
                case "2" -> showAllAnimals();
                case "3" -> findAnimalsBySpecies();
                case "4" -> listAvailableAnimals();
                case "5" -> markAnimalAsAdopted();
                case "0" -> {
                    System.out.println("Bye");
                    return;
                }
                default -> System.out.println("Unknown option");
            }
            }
        }

    private void printMenu(){
        System.out.println("""
                1. Add animal
                2. List all animals
                3. Find animals by species
                4. List available animals
                5. Mark animal as adopted
                0. Exit
                """);
    }
}
