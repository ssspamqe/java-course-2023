package edu.hw4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

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

        List<Animal> animals = new ArrayList<>(List.of(animalHeight2, animalHeight3, animalHeight1));

        List<Animal> returnedAnimals;

        returnedAnimals = solutions.task1(animals);

        assertThat(returnedAnimals.get(0)).isEqualTo(animalHeight1);
        assertThat(returnedAnimals.get(1)).isEqualTo(animalHeight2);
        assertThat(returnedAnimals.get(2)).isEqualTo(animalHeight3);
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
        List<Animal> animals = new ArrayList<>(List.of(animalWeight1, animalWeight2, animalWeight3, animalWeight4));
        List<Animal> returnedAnimals;


        returnedAnimals = solutions.task2(animals, k);


        assertThat(returnedAnimals).containsExactly(animalWeight4,animalWeight3);
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
        List<Animal> animals = new ArrayList<>(List.of(dog1, dog2,spider1));

        Map<Animal.Type,Long> correctMap = new HashMap<>();
        correctMap.put(Animal.Type.DOG,2L);
        correctMap.put(Animal.Type.SPIDER,1L);

        Map<Animal.Type,Long> returnedMap;


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
        List<Animal> animals = new ArrayList<>(List.of(animal1,animal2,animal3));

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
        List<Animal> animals = new ArrayList<>(List.of(animal1, animal2, animal3));

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
        List<Animal> animals = new ArrayList<>(List.of(dogWeight1,dogWeight2,spiderWeight1));

        Map<Animal.Type, Animal> correctMap = new HashMap<>();
        correctMap.put(Animal.Type.SPIDER,spiderWeight1);
        correctMap.put(Animal.Type.DOG,dogWeight2);

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

        List<Animal> animals = new ArrayList<>(List.of(animalAge1,animalAge2,animalAge3,animalAge4));

        Animal returnedAnimal;


        returnedAnimal = solutions.task7(animals,k);


        assertThat(returnedAnimal).isEqualTo(animalAge3);
    }

    @Test
    @DisplayName("Task 8 should return the heaviest one among lower than k sm animals")
    void task8_should_returnTheHeaviestAnimalAmongLowerThanKSm() {

        int k =100;

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
        List<Animal> animals = new ArrayList<>(List.of(animal1, animal2,animal3));

        Animal returnedAnimal;


        returnedAnimal = solutions.task8(animals,k).get();


        assertThat(returnedAnimal).isEqualTo(animal2);
    }

    @Test
    @DisplayName("Task 9 must return sum of paws of all animals")
    void task9_should_returnSum_ofPawsOfAllAnimals() {

        int sum = 0;
        int returnedSum;

        for (var i : animals) {
            sum += i.paws();
        }

        returnedSum = solutions.task9(animals);

        assertThat(returnedSum).isEqualTo(sum);
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

        assertThat(returnedList).isEqualTo(animalsWithDifferentAmountOfPawsAndAge);
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

        assertThat(returnedAnimals).isEqualTo(correctAnimals);
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

        assertThat(returnedAmount).isEqualTo(correctAmount);
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

        assertThat(returnedAnimals).isEqualTo(correctAnimals);
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

        assertThat(returned).isEqualTo(contains);
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

        assertThat(returnedMap).isEqualTo(correctMap);
    }

    @Test
    @DisplayName("Task 16 should return list of animals, sorted by type, sex,name")
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

        assertThat(returnedList).isEqualTo(animals);
    }

    @Test
    @DisplayName("Task 17 should state that spiders bite more frequent than dogs")
    void task17_should_returnBoolean_of_spiderBiteMoreFrequentThanDogs() {

        int dogs = 0;
        int bitingDogs = 0;
        int spiders = 0;
        int bitingSpiders = 0;
        boolean correctState;
        boolean returnedState;

        returnedState = solutions.task17(animals);

        for (var i : animals) {
            if (i.type() == Animal.Type.DOG) {
                dogs++;
                if (i.bites()) {
                    bitingDogs++;
                }
            } else if (i.type() == Animal.Type.SPIDER) {
                spiders++;
                if (i.bites()) {
                    bitingSpiders++;
                }
            }
        }
        if (dogs == 0 || spiders == 0) {
            correctState = false;
        } else {
            correctState = (double) bitingSpiders / spiders > (double) bitingDogs / dogs;
        }

        assertThat(returnedState).isEqualTo(correctState);
    }

    @Test
    @DisplayName("Task 18 should return the heaviest fish in all lists")
    void task18_should_returnTheHeaviestFish_amongAllLists() {

        List<List<Animal>> superList = new ArrayList<>();
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

        for (int i = 0; i < 5; i++) {
            superList.add(getRandomAnimals(11));
        }
        for (var list : superList) {
            for (var i : list) {
                if (i.type() == Animal.Type.FISH && i.weight() >= heaviestFish.weight()) {
                    heaviestFish = i;
                }
            }
        }

        returnedFish = solutions.task18(superList);

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
    void task20_should_returnNamesOfPropertyWithMistakes_ofEachAnimal() {

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
        String correctLine;
        Map<String, String> returnedMap;
        String returnedLine;

        correctLine = "AGE, HEIGHT, NAME, WEIGHT";
        returnedMap = solutions.task20(animalsWithIncorrectData);
        returnedLine = returnedMap.get("wrong name1");

        assertThat(returnedLine).isEqualTo(correctLine);
    }

}
