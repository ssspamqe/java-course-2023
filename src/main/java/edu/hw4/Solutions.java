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
    public Optional<Animal> task8 (List<Animal> animals, int k){
        return animals.stream()
            .filter(it -> it.height()<k)
            .max(Comparator.comparing(Animal::weight));
    }


    //Сколько в сумме лап у животных в списке -> Integer
    public Integer task9(List<Animal> animals){
        return animals.stream().mapToInt(Animal::paws).sum();
    }

    //Список животных, возраст у которых не совпадает с количеством лап -> List<Animal>
    public List<Animal> task10(List<Animal> animals){
        return animals.stream()
            .filter(animal -> animal.paws() != animal.age())
            .toList();
    }
}
