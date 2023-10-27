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

        for (var i : animals) {
            if (i.height() > 100 && i.bites()) {
                correctAnimals.add(i);
            }
        }

        returnedAnimals = solutions.task11(animals);

        assertThat(correctAnimals).isEqualTo(returnedAnimals);
    }

    @Test
    @DisplayName("Task 12 should return amount of animals with weight>height")
    void task12_should_returnAmount_ofAnimalsWithWeightGreaterThanHeight() {

        int correctAmount = 0;
        int returnedAmount;

        for (var i : animals) {
            if (i.weight() > i.height()) {
                correctAmount++;
            }
        }

        returnedAmount = solutions.task12(animals);

        assertThat(correctAmount).isEqualTo(returnedAmount);
    }

    @Test
    @DisplayName("Task 13 should return list of animals with names contain 2+ words")
    void task13_should_returnListOfAnimals_withNamesContainMoreThan1Word() {

        List<Animal> correctAnimals = new ArrayList<>();
        List<Animal> returnedAnimals;

        for (var i : animals) {
            if (i.name().split(" ").length > 1) {
                correctAnimals.add(i);
            }
        }

        returnedAnimals = solutions.task13(animals);

        assertThat(correctAnimals).isEqualTo(returnedAnimals);
    }

    @Test
    @DisplayName("Task 14 should return the true if animals contain dog with height > k sm")
    void task14_should_returnBoolean_listContainsDogHigherKSm() {

        int k = 50;
        boolean contains = false;
        boolean returned;

        for (var i : animals) {
            if (i.type() == Animal.Type.DOG && i.height() > k) {
                contains = true;
                break;
            }
        }

        returned = solutions.task14(animals, k);

        assertThat(contains).isEqualTo(returned);
    }

    @Test
    @DisplayName("Task 15 should return map with sum of weight animals with age in [k,l] of each type")
    void task15_should_returnMap_keyIsAnimalType_valueIsSum_ofWeightOfAnimals_withAgeInRangeKL() {

        Map<Animal.Type, Integer> correctMap = new HashMap<Animal.Type, Integer>();
        Map<Animal.Type, Integer> returnedMap;
        int k = 14, l = 30;

        for (var i : animals) {
            if (i.age() <= l && i.age() >= k) {
                if (!correctMap.containsKey(i.type())) {
                    correctMap.put(i.type(), 0);
                }
                correctMap.replace(i.type(), correctMap.get(i.type()) + i.weight());
            }
        }

        returnedMap = solutions.task15(animals, k, l);

        assertThat(correctMap).isEqualTo(returnedMap);
    }

    @Test
    @DisplayName("Task 17 should return list of animals, sorted by type, sex,name")
    void task16_should_returnListOfAnimals_sortedByTypeSexName() {

        List<Animal> returnedList;

        returnedList = solutions.task16(animals);

        animals.sort((a1, a2) -> {
            if (a1.type().ordinal() > a2.type().ordinal()) {
                return 1;
            }
            if (a1.type().ordinal() < a2.type().ordinal()) {
                return -1;
            }

            if (a1.sex().ordinal() > a2.sex().ordinal()) {
                return 1;
            }
            if (a1.sex().ordinal() < a2.sex().ordinal()) {
                return -1;
            }

            return a1.name().compareTo(a2.name());
        });

        assertThat(animals).isEqualTo(returnedList);
    }

    @Test
    @DisplayName("Task 18 should return the heaviest fish in all lists")
    void task18_should_returnTheHeaviestFish_amongAllLists() {

        List<List<Animal>> superList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            superList.add(getRandomAnimals(11));
        }
        Animal heaviestFish = new Animal(
            "",
            Animal.Type.FISH,
            Animal.Sex.F,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            false
        );
        Animal returnedFish;

        returnedFish = solutions.task18(superList);

        for (var list : superList) {
            for (var i : list) {
                if (i.type() == Animal.Type.FISH && i.weight() >= heaviestFish.weight()) {
                    heaviestFish = i;
                }
            }
        }

        assertThat(returnedFish).isEqualTo(heaviestFish);
    }

    @Test
    @DisplayName("Task 19 should return a list with mistakes associated with anima's data")
    void task19_should_returnMap_ofMistakesInData_byAnimalName() {

        List<Animal> animalsWithIncorrectData = List.of(
            new Animal(
                "wrong name1",
                Animal.Type.DOG,
                Animal.Sex.M,
                -1,
                -1,
                -1,
                false
            )
        );
        Map<String, Set<Solutions.ValidationException>> allExceptions;
        Set<Solutions.ValidationException> exceptions;
        Set<Solutions.ValidationException> correctExceptions;

        allExceptions = solutions.task19(animalsWithIncorrectData);
        exceptions = allExceptions.get("wrong name1");

        correctExceptions = Set.of(
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

        assertThat(exceptions).containsAnyElementsOf(correctExceptions);

    }

    @Test
    @DisplayName("Task 20 should return names of property with mistakes of each animal")
    void task20_should_returnNamesOfPropertyWithMistakes_ofEachAnimal(){

        List<Animal> animalsWithIncorrectData = List.of(
            new Animal(
                "wrong name1",
                Animal.Type.DOG,
                Animal.Sex.M,
                -1,
                -1,
                -1,
                false
            )
        );
        String correctLine = "AGE, HEIGHT, NAME, WEIGHT";

        Map<String,String> returnedMap;
        String returnedLine;

        returnedMap = solutions.task20(animalsWithIncorrectData);
        returnedLine = returnedMap.get("wrong name1");


        assertThat(returnedLine).isEqualTo(correctLine);
    }

}
