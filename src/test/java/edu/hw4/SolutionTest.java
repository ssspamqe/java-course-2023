package edu.hw4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SolutionTest {

    Solutions solutions = new Solutions();
    List<Animal> animals;

    @BeforeEach
    void initializeAnimals() {
        animals = new ArrayList<>();

        Random rand = new Random();

        for (int i = 0; i < 10; i++) {

            String[] names = {"Fluffy", "Spot", "Polly", "Bubbles", "Fido"};
            String randomName = names[rand.nextInt(names.length)];

            Animal.Type randomType = Animal.Type.values()[rand.nextInt(Animal.Type.values().length)];
            Animal.Sex randomSex = Animal.Sex.values()[rand.nextInt(Animal.Sex.values().length)];

            Animal animal = new Animal(
                randomName,
                randomType,
                randomSex,
                rand.nextInt(15),
                rand.nextInt(100),
                rand.nextInt(50),
                rand.nextBoolean()
            );

            animals.add(animal);
        }
    }

    @Test
    @DisplayName("Task1 should sort animals by height")
    void task1_should_sortByHeight() {

        List<Animal> sortedAnimals = solutions.task1(animals);

        for (int i = 1; i < sortedAnimals.size(); i++) {
            assertThat(sortedAnimals.get(i).height())
                .isGreaterThanOrEqualTo(sortedAnimals.get(i - 1).height());
        }
    }

    @Test
    @DisplayName("Task 2 should return list of k heaviest animals")
    void task2_should_returnListOfKHeaviestAnimals() {

        int k = 5;
        List<Animal> heaviestAnimals = solutions.task2(animals, 5);

        assertThat(heaviestAnimals).hasSize(5);
        for (int i = 1; i < k; i++) {
            assertThat(heaviestAnimals.get(i).weight())
                .isLessThanOrEqualTo(heaviestAnimals.get(i - 1).weight());
        }
    }

    @Test
    @DisplayName("Task3 should count animals by their type")
    void task3_should_returnMapThatRepresentsAmountOfAnimalsOfEachType() {

        Map<Animal.Type, Long> count = new HashMap<>();

        for (var i : animals) {
            if (!count.containsKey(i.type())) {
                count.put(i.type(), 0L);
            }
            count.replace(i.type(), count.get(i.type()) + 1);
        }
        Map<Animal.Type, Long> amountOfAnimalsByTypes = solutions.task3(animals);

        for (var i : count.keySet()) {
            assertThat(count.get(i)).isEqualTo(amountOfAnimalsByTypes.get(i));
        }
    }

    @Test
    @DisplayName("Task4 should return animal with the longest name")
    void task4_should_returnAnimalWithTheLongestName() {

        Animal animalWithTheLongestName = animals.get(0);


        for (var i : animals) {
            if (i.name().length() > animalWithTheLongestName.name().length()) {
                animalWithTheLongestName = i;
            }
        }
        Animal returnedAnimal = solutions.task4(animals);


        assertThat(animalWithTheLongestName).isEqualTo(returnedAnimal);
    }

    
}
