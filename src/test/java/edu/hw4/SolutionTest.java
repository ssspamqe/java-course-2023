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

        for (int i = 0; i < 11; i++) {

            String[] names = {"Fluffy", "Spot", "Polly", "Bubbles", "Fido","One Two","Two Three"};
            String randomName = names[rand.nextInt(names.length)];

            Animal.Type randomType = Animal.Type.values()[rand.nextInt(Animal.Type.values().length)];
            Animal.Sex randomSex = Animal.Sex.values()[rand.nextInt(Animal.Sex.values().length)];

            Animal animal = new Animal(
                randomName,
                randomType,
                randomSex,
                rand.nextInt(40),
                rand.nextInt(200),
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
    void task2_should_returnList_ofKHeaviestAnimals() {

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
    void task3_should_returnMap_thatRepresentsAmountOfAnimalsOfEachType() {

        Map<Animal.Type, Long> count = new HashMap<>();
        Map<Animal.Type, Long> amountOfAnimalsByTypes;

        for (var i : animals) {
            if (!count.containsKey(i.type())) {
                count.put(i.type(), 0L);
            }
            count.replace(i.type(), count.get(i.type()) + 1);
        }

        amountOfAnimalsByTypes = solutions.task3(animals);

        for (var i : count.keySet()) {
            assertThat(count.get(i)).isEqualTo(amountOfAnimalsByTypes.get(i));
        }
    }

    @Test
    @DisplayName("Task4 should return animal with the longest name")
    void task4_should_returnAnimal_withTheLongestName() {

        Animal animalWithTheLongestName = animals.get(0);
        Animal returnedAnimal;

        for (var i : animals) {
            if (i.name().length() > animalWithTheLongestName.name().length()) {
                animalWithTheLongestName = i;
            }
        }

        returnedAnimal = solutions.task4(animals);

        assertThat(animalWithTheLongestName).isEqualTo(returnedAnimal);
    }

    @Test
    @DisplayName("Task 5 should return most frequent sex")
    void task5_should_returnMostFrequentSex() {

        Animal.Sex mostFrequentSex = Animal.Sex.M;
        int maleCount = 0;
        Animal.Sex returnedSex;

        for (var i : animals) {
            if (i.sex() == Animal.Sex.M) {
                maleCount++;
            }
        }
        if (maleCount <= animals.size() / 2) {
            mostFrequentSex = Animal.Sex.F;
        }

        returnedSex = solutions.task5(animals);

        assertThat(mostFrequentSex).isEqualTo(returnedSex);
    }

    @Test
    @DisplayName("Task 6 should return the heaviest animal of each type")
    void task6_should_returnTheHeaviestAnimal_ofEachType() {

        Map<Animal.Type, Animal> heaviestAnimal = new HashMap<>();
        Map<Animal.Type, Animal> returnedMap;

        for (var i : animals) {
            if (!heaviestAnimal.containsKey(i.type())) {
                heaviestAnimal.put(i.type(), i);
            }
            if (heaviestAnimal.get(i.type()).weight() < i.weight()) {
                heaviestAnimal.replace(i.type(), i);
            }
        }

        returnedMap = solutions.task6(animals);

        for (var i : heaviestAnimal.keySet()) {
            assertThat(heaviestAnimal.get(i)).isEqualTo(returnedMap.get(i));
        }
    }

    @Test
    @DisplayName("Task 7 should return k-th the oldest animal")
    void task7_should_returnSpecificTheOldestAnimal() {

        int k = 5;
        Animal returnedAnimal;

        returnedAnimal = solutions.task7(animals, k);

        animals.sort(new Solutions.Task7Comparator());
        Animal specificOldestAnimal = animals.get(k);

        assertThat(specificOldestAnimal).isEqualTo(returnedAnimal);
    }

    //TODO test 8
//    @Test
//    @DisplayName("Task 8 should return the heviest one among lower than k sm animals")
//    void task8_should_returnTheHeaviestAnimalAmongLowerThanKSm(){
//
//        int k = 50;
//        Animal heaviestAnimal = null;
//
//
//        Optional<Animal> returnedAnimal = solutions.task8(animals,k);
//
//
//
//    }

    @Test
    @DisplayName("Task 9 must return sum of paws of all animals")
    void task9_should_returnSum_ofPawsOfAllAnimals() {

        int sum = 0;
        int returnedSum;

        for (var i : animals) {
            sum += i.paws();
        }

        returnedSum = solutions.task9(animals);

        assertThat(sum).isEqualTo(returnedSum);
    }

    @Test
    @DisplayName("Task 10 must return list of animals with different amount of paws and age")
    void task10_should_returnListOfAnimals_withDifferentAmountOfPawsAndAge() {

        List<Animal> animalsWithDifferentAmountOfPawsAndAge = new ArrayList<>();
        List<Animal> returnedList;

        for (var i : animals) {
            if (i.paws() != i.age()) {
                animalsWithDifferentAmountOfPawsAndAge.add(i);
            }
        }

        returnedList = solutions.task10(animals);

        assertThat(animalsWithDifferentAmountOfPawsAndAge).isEqualTo(returnedList);
    }

    @Test
    @DisplayName("Task 11 should return list of animal that are higher than 1m and bite")
    void task11_should_returnListOfAnimals_thatAreHigherThan1mAndBite() {

        List<Animal> correctAnimals = new ArrayList<>();
        List<Animal> returnedAnimals;


        for(var i : animals){
            if(i.height()>100 && i.bites())
                correctAnimals.add(i);
        }

        returnedAnimals = solutions.task11(animals);


        assertThat(correctAnimals).isEqualTo(returnedAnimals);
    }

    @Test
    @DisplayName("Task 12 should return amount of animals with weight>height")
    void task12_should_returnAmount_ofAnimalsWithWeightGreaterThanHeight(){

        int correctAmount=0;
        int returnedAmount;


        for(var i: animals){
            if(i.weight() > i.height())
                correctAmount++;
        }

        returnedAmount = solutions.task12(animals);


        assertThat(correctAmount).isEqualTo(returnedAmount);
    }

    @Test
    @DisplayName("Task 13 should return list of animals with names contain 2+ words")
    void task13_should_returnListOfAnimals_withNamesContainMoreThan1Word(){

        List<Animal> correctAnimals = new ArrayList<>();
        List<Animal> returnedAnimals;


        for(var i : animals){
            if(i.name().split(" ").length > 1)
                correctAnimals.add(i);
        }

        returnedAnimals = solutions.task13(animals);


        assertThat(correctAnimals).isEqualTo(returnedAnimals);
    }
}
