package org.example.shelter;

import org.example.model.AdoptionStatus;
import org.example.model.Animal;

import java.util.ArrayList;
import java.util.List;

public class Shelter <T extends Animal>{
    private final List<T> animals = new ArrayList<>();

    public void addAnimal(T animal){
        animals.add(animal);
    }

    public List<T> getAllAnimals(){
        return new ArrayList<>(animals);
    }

    public List<T> findBySpecies(String species){
        List<T> results = new ArrayList<>();
        for (T animal : animals) {
            if (animal.getSpecies().equalsIgnoreCase(species)) results.add(animal);
        }
        return results;
    }

    public List<T> findAvailableAnimals(){
        List<T> results = new ArrayList<>();
        for (T animal : animals) {
            if (animal.getAdoptionStatus() == AdoptionStatus.AVAILABLE) results.add(animal);
        }
        return results;
    }

    public boolean markAsAdopted(String id){
        for (T animal : animals) {
            if (animal.getId().toString().equals(id)) {
                animal.markAsAdopted();
                return true;
            }
        }
        return false;
    }
}
