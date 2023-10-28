package edu.hw4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SolutionTest {

    Solutions solutions = new Solutions();
    List<Animal> animals;

    private List<Animal> getRandomAnimals(int size) {
        List<Animal> animals = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            animals.add(getRandomAnimal());
        }
        return animals;
    }

    private Animal getRandomAnimal() {
        Random rand = new Random();
        String[] names = {"Fluffy", "Spot", "Polly", "Bubbles", "Fido", "One Two", "Two Three"};
        String randomName = names[rand.nextInt(names.length)];

        Animal.Type randomType = Animal.Type.values()[rand.nextInt(Animal.Type.values().length)];
        Animal.Sex randomSex = Animal.Sex.values()[rand.nextInt(Animal.Sex.values().length)];

        return new Animal(
            randomName,
            randomType,
            randomSex,
            rand.nextInt(40),
            rand.nextInt(200),
            rand.nextInt(200),
            rand.nextBoolean()
        );
    }

    @BeforeEach
    void initializeAnimals() {
        animals = getRandomAnimals(11);
    }

    @Test
    @DisplayName("Task1 should sort animals by height")
    void task1_should_sortAnimalsByHeight() {

        Animal animalHeight1 = new Animal(
            "h1",
            Animal.Type.DOG,
            Animal.Sex.F,
            Integer.MIN_VALUE,
            1,
            Integer.MIN_VALUE,
            false
        );

        Animal animalHeight2 = new Animal(
            "h2",
            Animal.Type.DOG,
            Animal.Sex.F,
            Integer.MIN_VALUE,
            2,
            Integer.MIN_VALUE,
            false
        );

        Animal animalHeight3 = new Animal(
            "h3",
            Animal.Type.DOG,
            Animal.Sex.F,
            Integer.MIN_VALUE,
            3,
            Integer.MIN_VALUE,
            false
        );

        List<Animal> animals = List.of(animalHeight2, animalHeight3, animalHeight1);
        List<Animal> sortedAnimals = List.of(animalHeight1,animalHeight2,animalHeight3);
        List<Animal> returnedAnimals;

        returnedAnimals = solutions.task1(animals);

        assertThat(returnedAnimals).isEqualTo(sortedAnimals);
    }

    @Test
    @DisplayName("Task 2 should return list of k heaviest animals")
    void task2_should_returnList_ofKHeaviestAnimals() {

        int k = 2;

        Animal animalWeight1 = new Animal(
            "h1",
            Animal.Type.DOG,
            Animal.Sex.F,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            1,
            false
        );

        Animal animalWeight2 = new Animal(
            "h1",
            Animal.Type.DOG,
            Animal.Sex.F,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            2,
            false
        );

        Animal animalWeight3 = new Animal(
            "h1",
            Animal.Type.DOG,
            Animal.Sex.F,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            3,
            false
        );

        Animal animalWeight4 = new Animal(
            "h1",
            Animal.Type.DOG,
            Animal.Sex.F,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            4,
            false
        );
        List<Animal> animals = List.of(animalWeight1, animalWeight2, animalWeight3, animalWeight4);
        List<Animal> returnedAnimals;

        returnedAnimals = solutions.task2(animals, k);

        assertThat(returnedAnimals).containsExactly(animalWeight4, animalWeight3);
    }

    @Test
    @DisplayName("Task3 should count animals by their type")
    void task3_should_returnMap_thatRepresentsAmountOfAnimalsOfEachType() {

        Animal dog1 = new Animal(
            "h1",
            Animal.Type.DOG,
            Animal.Sex.F,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            1,
            false
        );
        Animal dog2 = new Animal(
            "h1",
            Animal.Type.DOG,
            Animal.Sex.F,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            1,
            false
        );
        Animal spider1 = new Animal(
            "h1",
            Animal.Type.SPIDER,
            Animal.Sex.F,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            1,
            false
        );
        List<Animal> animals = List.of(dog1, dog2, spider1);

        Map<Animal.Type, Long> correctMap = new HashMap<>();
        correctMap.put(Animal.Type.DOG, 2L);
        correctMap.put(Animal.Type.SPIDER, 1L);

        Map<Animal.Type, Long> returnedMap;

        returnedMap = solutions.task3(animals);

        assertThat(returnedMap).containsExactlyEntriesOf(correctMap);
    }

    @Test
    @DisplayName("Task4 should return animal with the longest name")
    void task4_should_returnAnimal_withTheLongestName() {

        Animal animal1 = new Animal(
            "a",
            Animal.Type.DOG,
            Animal.Sex.F,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            false
        );
        Animal animal2 = new Animal(
            "aba",
            Animal.Type.DOG,
            Animal.Sex.F,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            false
        );
        Animal animal3 = new Animal(
            "abacaba",
            Animal.Type.DOG,
            Animal.Sex.F,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            false
        );
        List<Animal> animals = List.of(animal1, animal2, animal3);

        Animal returnedAnimal;

        returnedAnimal = solutions.task4(animals);

        assertThat(returnedAnimal).isEqualTo(animal3);
    }

    @Test
    @DisplayName("Task 5 should return most frequent sex")
    void task5_should_returnMostFrequentSex() {

        Animal animal1 = new Animal(
            "aba",
            Animal.Type.DOG,
            Animal.Sex.F,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            false
        );
        Animal animal2 = new Animal(
            "aba",
            Animal.Type.DOG,
            Animal.Sex.F,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            false
        );
        Animal animal3 = new Animal(
            "aba",
            Animal.Type.DOG,
            Animal.Sex.M,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            false
        );
        List<Animal> animals = List.of(animal1, animal2, animal3);

        Animal.Sex returnedSex;

        returnedSex = solutions.task5(animals);

        assertThat(returnedSex).isEqualTo(Animal.Sex.F);
    }

    @Test
    @DisplayName("Task 6 should return the heaviest animal of each type")
    void task6_should_returnTheHeaviestAnimal_ofEachType() {

        Animal dogWeight1 = new Animal(
            "aba",
            Animal.Type.DOG,
            Animal.Sex.M,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            1,
            false
        );
        Animal dogWeight2 = new Animal(
            "aba",
            Animal.Type.DOG,
            Animal.Sex.M,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            2,
            false
        );
        Animal spiderWeight1 = new Animal(
            "aba",
            Animal.Type.SPIDER,
            Animal.Sex.M,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            1,
            false
        );
        List<Animal> animals = List.of(dogWeight1, dogWeight2, spiderWeight1);

        Map<Animal.Type, Animal> correctMap = new HashMap<>();
        correctMap.put(Animal.Type.SPIDER, spiderWeight1);
        correctMap.put(Animal.Type.DOG, dogWeight2);

        Map<Animal.Type, Animal> returnedMap;

        returnedMap = solutions.task6(animals);

        assertThat(returnedMap).containsExactlyEntriesOf(correctMap);
    }

    @Test
    @DisplayName("Task 7 should return k-th the oldest animal")
    void task7_should_returnSpecificTheOldestAnimal() {

        int k = 2;

        Animal animalAge1 = new Animal(
            "aba",
            Animal.Type.SPIDER,
            Animal.Sex.M,
            1,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            false
        );
        Animal animalAge2 = new Animal(
            "aba",
            Animal.Type.SPIDER,
            Animal.Sex.M,
            2,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            false
        );
        Animal animalAge3 = new Animal(
            "aba",
            Animal.Type.SPIDER,
            Animal.Sex.M,
            3,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            false
        );
        Animal animalAge4 = new Animal(
            "aba",
            Animal.Type.SPIDER,
            Animal.Sex.M,
            4,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            false
        );

        List<Animal> animals = List.of(animalAge1, animalAge2, animalAge3, animalAge4);

        Animal returnedAnimal;

        returnedAnimal = solutions.task7(animals, k);

        assertThat(returnedAnimal).isEqualTo(animalAge3);
    }

    @Test
    @DisplayName("Task 8 should return the heaviest one among lower than k sm animals")
    void task8_should_returnTheHeaviestAnimalAmongLowerThanKSm() {

        int k = 100;

        Animal animal1 = new Animal(
            "aba",
            Animal.Type.SPIDER,
            Animal.Sex.M,
            Integer.MIN_VALUE,
            100,
            50,
            false
        );
        Animal animal2 = new Animal(
            "aba",
            Animal.Type.SPIDER,
            Animal.Sex.M,
            Integer.MIN_VALUE,
            99,
            40,
            false
        );
        Animal animal3 = new Animal(
            "aba",
            Animal.Type.SPIDER,
            Animal.Sex.M,
            Integer.MIN_VALUE,
            50,
            10,
            false
        );
        List<Animal> animals = List.of(animal1, animal2, animal3);

        Animal returnedAnimal;

        returnedAnimal = solutions.task8(animals, k).get();

        assertThat(returnedAnimal).isEqualTo(animal2);
    }

    @Test
    @DisplayName("Task 9 must return sum of paws of all animals")
    void task9_should_returnSum_ofPawsOfAllAnimals() {

        Animal animal1 = new Animal(
            "aba",
            Animal.Type.SPIDER,
            Animal.Sex.M,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            false
        );
        Animal animal2 = new Animal(
            "aba",
            Animal.Type.DOG,
            Animal.Sex.M,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            false
        );
        List<Animal> animals = List.of(animal1, animal2);

        int returnedSum;

        returnedSum = solutions.task9(animals);

        assertThat(returnedSum).isEqualTo(12);
    }

    @Test
    @DisplayName("Task 10 must return list of animals with different amount of paws and age")
    void task10_should_returnListOfAnimals_withDifferentAmountOfPawsAndAge() {

        Animal animal1 = new Animal(
            "aba",
            Animal.Type.SPIDER,
            Animal.Sex.M,
            8,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            false
        );

        Animal animal2 = new Animal(
            "aba",
            Animal.Type.DOG,
            Animal.Sex.M,
            8,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            false
        );

        Animal animal3 = new Animal(
            "aba",
            Animal.Type.FISH,
            Animal.Sex.M,
            8,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            false
        );
        List<Animal> animals = List.of(animal1, animal2, animal3);

        List<Animal> returnedAnimals;

        returnedAnimals = solutions.task10(animals);

        assertThat(returnedAnimals).containsExactly(animal2, animal3);
    }

    @Test
    @DisplayName("Task 11 should return list of animal that are higher than 1m and bite")
    void task11_should_returnListOfAnimals_thatAreHigherThan1mAndBite() {

        Animal animal1 = new Animal(
            "aba",
            Animal.Type.SPIDER,
            Animal.Sex.M,
            Integer.MIN_VALUE,
            100,
            Integer.MIN_VALUE,
            false
        );

        Animal animal2 = new Animal(
            "aba",
            Animal.Type.SPIDER,
            Animal.Sex.M,
            Integer.MIN_VALUE,
            101,
            Integer.MIN_VALUE,
            true
        );

        Animal animal3 = new Animal(
            "aba",
            Animal.Type.SPIDER,
            Animal.Sex.M,
            Integer.MIN_VALUE,
            99,
            Integer.MIN_VALUE,
            true
        );

        List<Animal> animals = List.of(animal1, animal2, animal3);

        List<Animal> returnedAnimals;

        returnedAnimals = solutions.task11(animals);

        assertThat(returnedAnimals).containsExactly(animal2);
    }

    @Test
    @DisplayName("Task 12 should return amount of animals with weight>height")
    void task12_should_returnAmount_ofAnimalsWithWeightGreaterThanHeight() {

        Animal animal1 = new Animal(
            "aba",
            Animal.Type.SPIDER,
            Animal.Sex.M,
            Integer.MIN_VALUE,
            100,
            1,
            false
        );

        Animal animal2 = new Animal(
            "aba",
            Animal.Type.SPIDER,
            Animal.Sex.M,
            Integer.MIN_VALUE,
            100,
            100,
            false
        );

        Animal animal3 = new Animal(
            "aba",
            Animal.Type.SPIDER,
            Animal.Sex.M,
            Integer.MIN_VALUE,
            100,
            1001,
            false
        );

        List<Animal> animals = List.of(animal1, animal2, animal3);

        int returnedAmount;

        returnedAmount = solutions.task12(animals);

        assertThat(returnedAmount).isEqualTo(1);
    }

    @Test
    @DisplayName("Task 13 should return list of animals with names contain 2+ words")
    void task13_should_returnListOfAnimals_withNamesContainMoreThan1Word() {

        Animal animal1 = new Animal(
            "aba",
            Animal.Type.SPIDER,
            Animal.Sex.M,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            false
        );
        Animal animal2 = new Animal(
            "aba aba",
            Animal.Type.SPIDER,
            Animal.Sex.M,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            false
        );
        Animal animal3 = new Animal(
            "a a a",
            Animal.Type.SPIDER,
            Animal.Sex.M,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            false
        );
        List<Animal> animals = List.of(animal1, animal2, animal3);

        List<Animal> returnedAnimals;

        returnedAnimals = solutions.task13(animals);

        assertThat(returnedAnimals).containsExactly(animal2, animal3);
    }

    @Test
    @DisplayName("Task 14 should return the true if animals contain dog with height > k sm")
    void task14_should_returnBoolean_listContainsDogHigherKSm() {

        int k = 50;

        Animal animal1 = new Animal(
            "aba",
            Animal.Type.DOG,
            Animal.Sex.M,
            Integer.MIN_VALUE,
            50,
            Integer.MIN_VALUE,
            false
        );
        Animal animal2 = new Animal(
            "aba",
            Animal.Type.SPIDER,
            Animal.Sex.M,
            Integer.MIN_VALUE,
            100,
            Integer.MIN_VALUE,
            false
        );
        Animal animal3 = new Animal(
            "aba",
            Animal.Type.DOG,
            Animal.Sex.M,
            Integer.MIN_VALUE,
            51,
            Integer.MIN_VALUE,
            false
        );
        List<Animal> animals = List.of(animal1, animal2, animal3);

        boolean returnedStatement;

        returnedStatement = solutions.task14(animals, k);

        assertThat(returnedStatement).isTrue();
    }

    @Test
    @DisplayName("Task 15 should return map with sum of weight animals with age in [k,l] of each type")
    void task15_should_returnMap_keyIsAnimalType_valueIsSum_ofWeightOfAnimals_withAgeInRangeKL() {

        int k = 14, l = 30;

        Animal animal1 = new Animal(
            "aba",
            Animal.Type.DOG,
            Animal.Sex.M,
            17,
            Integer.MIN_VALUE,
            50,
            false
        );
        Animal animal2 = new Animal(
            "aba",
            Animal.Type.DOG,
            Animal.Sex.M,
            100,
            Integer.MIN_VALUE,
            50,
            false
        );
        Animal animal3 = new Animal(
            "aba",
            Animal.Type.SPIDER,
            Animal.Sex.M,
            14,
            Integer.MIN_VALUE,
            50,
            false
        );
        Animal animal4 = new Animal(
            "aba",
            Animal.Type.SPIDER,
            Animal.Sex.M,
            30,
            Integer.MIN_VALUE,
            50,
            false
        );
        List<Animal> animals = List.of(animal1, animal2, animal3 , animal4);

        Map<Animal.Type, Integer> correctMap = new HashMap<>();
        correctMap.put(Animal.Type.DOG, 50);
        correctMap.put(Animal.Type.SPIDER,100);

        Map<Animal.Type, Integer> returnedMap;


        returnedMap = solutions.task15(animals,k,l);


        assertThat(returnedMap).containsExactlyEntriesOf(correctMap);
    }

    @Test
    @DisplayName("Task 16 should return list of animals, sorted by type, sex,name")
    void task16_should_returnListOfAnimals_sortedByTypeSexName() {

        Animal animal1 = new Animal(
            "aba",
            Animal.Type.DOG,
            Animal.Sex.M,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            false
        );
        Animal animal2 = new Animal(
            "bab",
            Animal.Type.DOG,
            Animal.Sex.M,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            false
        );
        Animal animal3 = new Animal(
            "bab",
            Animal.Type.DOG,
            Animal.Sex.F,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            false
        );
        Animal animal4 = new Animal(
            "aba",
            Animal.Type.SPIDER,
            Animal.Sex.M,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            false
        );
        List<Animal> animals = List.of(animal4, animal3,animal2,animal1);
        List<Animal> sortedAnimals = List.of(animal1,animal2, animal3,animal4);

        List<Animal> returnedAnimals;


        returnedAnimals = solutions.task16(animals);

        assertThat(returnedAnimals).isEqualTo(sortedAnimals);
    }

    @Test
    @DisplayName("Task 17 should state that spiders bite more frequent than dogs")
    void task17_should_returnBoolean_of_spiderBiteMoreFrequentThanDogs() {

        Animal animal1 = new Animal(
            "aba",
            Animal.Type.SPIDER,
            Animal.Sex.M,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            true
        );
        Animal animal2 = new Animal(
            "aba",
            Animal.Type.SPIDER,
            Animal.Sex.M,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            true
        );
        Animal animal3 = new Animal(
            "aba",
            Animal.Type.SPIDER,
            Animal.Sex.M,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            true
        );
        Animal animal4 = new Animal(
            "aba",
            Animal.Type.DOG,
            Animal.Sex.M,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            false
        );
        List<Animal> animals = List.of(animal4, animal3,animal2,animal1);

        boolean returnedStatement;


        returnedStatement = solutions.task17(animals);


        assertThat(returnedStatement).isTrue();
    }

    @Test
    @DisplayName("Task 18 should return the heaviest fish in all lists")
    void task18_should_returnTheHeaviestFish_amongAllLists() {

        Animal animal1 = new Animal(
            "aba",
            Animal.Type.FISH,
            Animal.Sex.M,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            -1,
            true
        );
        Animal animal2 = new Animal(
            "aba",
            Animal.Type.FISH,
            Animal.Sex.M,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            9999,
            true
        );
        List<List<Animal>> superList = List.of(List.of(animal1), List.of(animal2));

        Animal returnedAnimal;


        returnedAnimal = solutions.task18(superList);


        assertThat(returnedAnimal).isEqualTo(animal2);
    }

    @Test
    @DisplayName("Task 19 should return a list with mistakes associated with anima's data")
    void task19_should_returnMap_ofMistakesInData_byAnimalName() {

        Animal animal = new Animal(
            "wrong name1",
            Animal.Type.DOG,
            Animal.Sex.M,
            -1,
            -1,
            -1,
            false
        );

        List<Animal> animalsWithIncorrectData = List.of(animal);

        Set<Solutions.ValidationException> correctExceptions = Set.of(
            new Solutions.ValidationException(
                "Name must start with capital letter",
                Solutions.ValidationExceptionType.NAME
            ),
            new Solutions.ValidationException(
                "Name must not contain any digits",
                Solutions.ValidationExceptionType.NAME
            ),
            new Solutions.ValidationException(
                "Age must be non negative",
                Solutions.ValidationExceptionType.AGE
            ),
            new Solutions.ValidationException(
                "Height must be positive",
                Solutions.ValidationExceptionType.HEIGHT
            ),
            new Solutions.ValidationException(
                "Weight must be positive number",
                Solutions.ValidationExceptionType.WEIGHT
            )
        );

        Set<Solutions.ValidationException> returnedExceptions;


        returnedExceptions = solutions.task19(animalsWithIncorrectData).get("wrong name1");


        assertThat(returnedExceptions).containsAnyElementsOf(correctExceptions);

    }

    @Test
    @DisplayName("Task 20 should return names of property with mistakes of each animal")
    void task20_should_returnNamesOfPropertyWithMistakes_ofEachAnimal() {

        Animal animal = new Animal(
            "wrong name1",
            Animal.Type.DOG,
            Animal.Sex.M,
            -1,
            -1,
            -1,
            false
        );
        List<Animal> animalsWithIncorrectData = List.of(animal);

        String correctLine = "AGE, HEIGHT, NAME, WEIGHT";

        String returnedLine;


        returnedLine = solutions.task20(animalsWithIncorrectData).get("wrong name1");


        assertThat(returnedLine).isEqualTo(correctLine);
    }

}
