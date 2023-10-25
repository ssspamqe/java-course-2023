package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import static edu.hw4.Animal.Sex;
import static edu.hw4.Animal.Type;

public class Solutions {

    //Отсортировать животных по росту от самого маленького к самому большому -> List<Animal>
    public List<Animal> task1(List<Animal> animals) {
        return animals.stream().sorted(Comparator.comparingInt(Animal::height)).toList();
    }

    //Отсортировать животных по весу от самого тяжелого к самому легкому, выбрать k первых -> List<Animal>
    public List<Animal> task2(List<Animal> animals, int k) {
        return animals.stream().sorted((o1, o2) -> Integer.compare(o2.weight(), o1.weight())).limit(k).toList();
    }

    //Сколько животных каждого вида -> Map<Animal.Type, Integer>
    public Map<Type, Long> task3(List<Animal> animals) {
        return animals.stream().collect(Collectors.groupingBy(
            Animal::type,
            Collectors.counting()
        ));
    }

    //У какого животного самое длинное имя -> Animal
    public Animal task4(List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparing(a -> a.name().length()))
            .orElseThrow();
    }

    //Каких животных больше: самцов или самок -> Sex
    public Sex task5(List<Animal> animals) {
        int maleAmount = (int) animals.stream().filter(animal -> animal.sex() == Sex.M).count();
        if (maleAmount >= animals.size() / 2) {
            return Sex.M;
        }
        return Sex.F;
    }

    //Самое тяжелое животное каждого вида -> Map<Animal.Type, Animal>
    public Map<Type, Animal> task6(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(
                Animal::type,
                Function.identity(),
                BinaryOperator.maxBy(Comparator.comparing(Animal::weight))
            ));
    }

    //K-е самое старое животное -> Animal
    public Animal task7(List<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::age))
            .toList()
            .get(animals.size() - k);
    }

    //Самое тяжелое животное среди животных ниже k см -> Optional<Animal>
    public Optional<Animal> task8(List<Animal> animals, int k) {
        return animals.stream()
            .filter(it -> it.height() < k)
            .max(Comparator.comparing(Animal::weight));
    }

    //Сколько в сумме лап у животных в списке -> Integer
    public Integer task9(List<Animal> animals) {
        return animals.stream().mapToInt(Animal::paws).sum();
    }

    //Список животных, возраст у которых не совпадает с количеством лап -> List<Animal>
    public List<Animal> task10(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.paws() != animal.age())
            .toList();
    }

    //Список животных, которые могут укусить (bites == null или true) и рост которых превышает 100 см -> List<Animal>
    public List<Animal> task11(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.bites() && animal.height() > 100)
            .toList();
    }

    //Сколько в списке животных, вес которых превышает рост -> Integer
    public List<Animal> task12(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .toList();
    }

    //Список животных, имена которых состоят из более чем двух слов -> List<Animal>
    public List<Animal> task13(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.name().split(" ").length >= 2)
            .toList();
    }

    //Есть ли в списке собака ростом более k см -> Boolean
    public Boolean task14(List<Animal> animals, int k) {
        return animals.stream()
            .anyMatch(animal -> animal.type() == Type.DOG && animal.height() > k);
    }

    //Найти суммарный вес животных каждого вида, которым от k до l лет -> Integer
    public Map<Type, Integer> task15(List<Animal> animals, int k, int i) {
        return animals.stream()
            .filter(animal -> animal.age() <= i && animal.age() >= k)
            .collect(Collectors.groupingBy(
                Animal::type,
                Collectors.summingInt(Animal::weight)
            ));
    }

    //Список животных, отсортированный по виду, затем по полу, затем по имени -> List<Integer>
    public List<Animal> task16(List<Animal> animals) {
        return animals.stream()
            .sorted((a1, a2) -> {
                if (a1.type().ordinal() > a2.type().ordinal()) {
                    return 1;
                } else if (a1.type().ordinal() < a2.type().ordinal()) {
                    return -1;
                }

                if (a1.sex().ordinal() > a2.sex().ordinal()) {
                    return 1;
                } else if (a1.sex().ordinal() < a2.sex().ordinal()) {
                    return -1;
                }

                return a1.name().compareTo(a2.name());
            })
            .toList();
    }

    //Правда ли, что пауки кусаются чаще, чем собаки -> Boolean (если данных для ответа недостаточно, вернуть false)
    public boolean task17(List<Animal> animals) {
        int spiders = (int) animals.stream()
            .filter(animal -> animal.type() == Type.SPIDER)
            .count();
        int bitingSpider = (int) animals.stream()
            .filter(animal -> animal.type() == Type.SPIDER && animal.bites())
            .count();

        int dogs = (int) animals.stream()
            .filter(animal -> animal.type() == Type.SPIDER)
            .count();
        int bitingDogs = (int) animals.stream()
            .filter(animal -> animal.type() == Type.DOG && animal.bites())
            .count();

        if (spiders == 0 || dogs == 0) {
            return false;
        } else {
            return (double) bitingSpider / spiders > (double) bitingDogs / dogs;
        }
    }

    //Найти самую тяжелую рыбку в 2-х или более списках -> Animal
    public Animal task18(List<List<Animal>> animalsSuperList) {
        //получается, что мы пробегаем вначале по всем животным, а потом еще раз по листу с самой тяжелой рабкой
        //не лучше ли сделать просто for-each цикл, перед ним создав объект животного с -1 массой и так найти?
        return animalsSuperList.stream().max(
                Comparator.comparingInt(list ->
                    list.stream()
                        .max(Comparator.comparing(Animal::weight))
                        .get()
                        .weight()
                )
            ).orElseThrow()
            .stream()
            .max(Comparator.comparingInt(Animal::weight))
            .orElseThrow();

    }

}

