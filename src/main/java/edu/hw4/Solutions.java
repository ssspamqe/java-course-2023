package edu.hw4;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
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
            .sorted(new Task7Comparator())
            .toList()
            .get(k);
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
    @SuppressWarnings("MagicNumber")
    public List<Animal> task11(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.bites() && animal.height() > 100)
            .toList();
    }

    //Сколько в списке животных, вес которых превышает рост -> Integer
    public int task12(List<Animal> animals) {
        return (int) animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count();
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
    public Map<Type, Integer> task15(List<Animal> animals, int k, int l) {
        return animals.stream()
            .filter(animal -> animal.age() <= l && animal.age() >= k)
            .collect(Collectors.groupingBy(
                Animal::type,
                Collectors.summingInt(Animal::weight)
            ));
    }

    //Список животных, отсортированный по виду, затем по полу, затем по имени -> List<Integer>
    @SuppressWarnings("MagicNumber")
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
            .filter(animal -> animal.type() == Type.DOG)
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
                Comparator.comparing(list ->
                    list.stream()
                        .filter(a -> a.type() == Type.FISH)
                        .max(Comparator.comparing(Animal::weight))
                        .orElse(
                            new Animal(
                                "",
                                Animal.Type.FISH,
                                Animal.Sex.F,
                                Integer.MIN_VALUE,
                                Integer.MIN_VALUE,
                                Integer.MIN_VALUE,
                                false
                            )
                        )
                        .weight()
                )
            ).orElseThrow()
            .stream()
            .filter(a -> a.type() == Type.FISH)
            .max(Comparator.comparingInt(Animal::weight))
            .orElse(new Animal(
                "",
                Animal.Type.FISH,
                Animal.Sex.F,
                Integer.MIN_VALUE,
                Integer.MIN_VALUE,
                Integer.MIN_VALUE,
                false
            ));
    }

    //Животные, в записях о которых есть ошибки: вернуть имя и список ошибок -> Map<String, Set<ValidationError>>.
    //Класс ValidationError и набор потенциальных проверок нужно придумать самостоятельно. (поменяю на ValidationException)
    public Map<String, Set<ValidationException>> task19(List<Animal> animals) {
        return animals.stream().collect(Collectors.toMap(
            Animal::name,
            this::validateAnimal
        ));
    }

    //Сделать результат предыдущего задания более читабельным:
    // вернуть имя и названия полей с ошибками, объединенные в строку -> Map<String, String>
    public Map<String, String> task20(List<Animal> animals) {
        return animals.stream().collect(Collectors.toMap(
            Animal::name,
            animal -> {
                Set<ValidationException> exceptions = validateAnimal(animal);
                Set<String> properties = new TreeSet<>();
                for (var i : exceptions) {
                    properties.add(i.type.toString());
                }

                return String.join(", ", properties);
            }
        ));
    }

    //methods and classes for tasks 19 and 20


    private Set<ValidationException> validateAnimal(Animal animal) {
        Set<ValidationException> exceptions = new HashSet<>();

        exceptions.addAll(validateName(animal.name()));
        exceptions.addAll(validateAge(animal.age()));
        exceptions.addAll(validateType(animal.type()));
        exceptions.addAll(validateSex(animal.sex()));
        exceptions.addAll(validateWeight(animal.weight()));
        exceptions.addAll(validateHeight(animal.height()));

        return exceptions;
    }

    enum ValidationExceptionType {
        NAME,
        TYPE,
        SEX,
        AGE,
        HEIGHT,
        WEIGHT
    }

    private Set<ValidationException> validateName(String name) {

        Set<ValidationException> exceptions = new HashSet<>();

        if (name.charAt(0) != Character.toUpperCase(name.charAt(0))) {
            exceptions.add(new ValidationException(
                "Name must start with capital letter",
                ValidationExceptionType.NAME
            ));
        }

        for (int i = 0; i < name.length(); i++) {
            if (Character.isDigit(name.charAt(i))) {
                exceptions.add(new ValidationException(
                    "Name must not contain any digits",
                    ValidationExceptionType.NAME
                ));
                break;
            }
        }

        return exceptions;
    }

    private Set<ValidationException> validateType(Type type) {
        Set<ValidationException> exceptions = new HashSet<>();
        if (type == null) {
            exceptions.add(new ValidationException("Type must not be null", ValidationExceptionType.TYPE));
        }
        return exceptions;
    }

    private Set<ValidationException> validateSex(Sex sex) {
        Set<ValidationException> exceptions = new HashSet<>();
        if (sex == null) {
            exceptions.add(new ValidationException("Sex must not be null", ValidationExceptionType.TYPE));
        }
        return exceptions;
    }

    private Set<ValidationException> validateAge(int age) {
        Set<ValidationException> exceptions = new HashSet<>();
        if (age < 0) {
            exceptions.add(new ValidationException("Age must be non negative", ValidationExceptionType.AGE));
        }
        return exceptions;
    }

    private Set<ValidationException> validateHeight(int height) {
        Set<ValidationException> exceptions = new HashSet<>();
        if (height <= 0) {
            exceptions.add(new ValidationException("Height must be positive", ValidationExceptionType.HEIGHT));
        }
        return exceptions;
    }

    private Set<ValidationException> validateWeight(int weight) {
        Set<ValidationException> exceptions = new HashSet<>();
        if (weight <= 0) {
            exceptions.add(new ValidationException("Weight must be positive number", ValidationExceptionType.WEIGHT));
        }
        return exceptions;
    }

    static class Task7Comparator implements Comparator<Animal> {

        @Override
        public int compare(Animal an1, Animal an2) {
            if (an1.weight() > an2.weight()) {
                return -1;
            }
            if (an1.weight() < an2.weight()) {
                return 1;
            }

            if (an1.age() > an2.age()) {
                return -1;
            }

            if (an1.age() < an2.age()) {
                return 1;
            }
            return an2.name().compareTo(an1.name());
        }
    }

    static class ValidationException extends RuntimeException {
        private final String message;
        private final ValidationExceptionType type;

        ValidationException(String message, ValidationExceptionType type) {
            this.message = message;
            this.type = type;
        }

        public String getMessage() {
            return message;
        }

        public ValidationExceptionType getType() {
            return type;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ValidationException)) {
                return false;
            }
            return message.equals(((ValidationException) obj).getMessage())
                && type == ((ValidationException) obj).type;

        }
    }
}




