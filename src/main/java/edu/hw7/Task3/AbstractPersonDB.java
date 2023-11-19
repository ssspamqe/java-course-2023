package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public class AbstractPersonDB implements PersonDB {

    private static final Map<Integer, Person> PERSONS_BY_ID = new HashMap<>();
    private static final Map<String, List<Person>> PERSONS_BY_NAME = new HashMap<>();
    private static final Map<String, List<Person>> PERSONS_BY_ADDRESS = new HashMap<>();
    private static final Map<String, Person> PERSONS_BY_PHONE = new HashMap<>();

    @Override
    public void add(Person newPerson) {
        if (!phoneIsAvailable(newPerson.phoneNumber())
        || PERSONS_BY_ID.containsKey(newPerson.id())) {
            return;
        }
        addToDB(newPerson);
    }

    @Override
    public void delete(int id) {
        Person person = PERSONS_BY_ID.get(id);
        deletePersonInPersonsByName(person);
        deletePersonInPersonsByAddress(person);
        deletePersonInPersonsByPhone(person);
        PERSONS_BY_ID.remove(id);
    }

    @Override
    public @Nullable List<Person> findByName(String name) {
        return PERSONS_BY_NAME.get(name);
    }

    @Override
    public @Nullable List<Person> findByAddress(String address) {
        return PERSONS_BY_ADDRESS.get(address);
    }

    @Override
    public @Nullable Person findByPhone(String phone) {
        return PERSONS_BY_PHONE.get(phone);
    }

    private void addToDB(Person person) {
        addPersonToPersonsByName(person);
        addPersonToPersonsByAddress(person);
        addPersonToPersonByPhone(person);
    }

    private void deletePersonInPersonsByName(Person person) {
        PERSONS_BY_NAME.values().forEach(it -> it.remove(person));
    }

    private void deletePersonInPersonsByAddress(Person person) {
        PERSONS_BY_ADDRESS.values().forEach(it -> it.remove(person));
    }

    private void deletePersonInPersonsByPhone(Person person) {
        String phone = person.phoneNumber();
        PERSONS_BY_PHONE.remove(phone);
    }

    private void addPersonToPersonsByName(Person person) {
        String name = person.name();
        PERSONS_BY_NAME.computeIfAbsent(name, mapper -> new ArrayList<>());
        PERSONS_BY_NAME.get(name).add(person);
    }

    private void addPersonToPersonsByAddress(Person person) {
        String address = person.address();
        PERSONS_BY_ADDRESS.computeIfAbsent(address, mapper -> new ArrayList<>());
        PERSONS_BY_ADDRESS.get(address).add(person);
    }

    private void addPersonToPersonByPhone(Person person) {
        String phone = person.phoneNumber();
        if (phoneIsAvailable(phone)) {
            PERSONS_BY_PHONE.put(phone, person);
        }
    }

    private boolean phoneIsAvailable(String phone) {
        return PERSONS_BY_PHONE.get(phone) == null;
    }

}
