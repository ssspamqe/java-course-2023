package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public class AbstractPersonDB implements PersonDB {

    private final Map<Integer, Person> personsById = new HashMap<>();
    private final Map<String, List<Person>> personsByName = new HashMap<>();
    private final Map<String, List<Person>> personsByAddress = new HashMap<>();
    private final Map<String, Person> personsByPhone = new HashMap<>();

    @Override
    public void add(Person newPerson) {
        if (!phoneIsAvailable(newPerson.phoneNumber())
            || personsById.containsKey(newPerson.id())) {
            return;
        }
        addToDB(newPerson);
    }

    @Override
    public void delete(int id) {
        Person person = personsById.get(id);
        deletePersonFromPersonsByName(person);
        deletePersonFromPersonsByAddress(person);
        deletePersonFromPersonsByPhone(person);
        personsById.remove(id);
    }

    @Override
    public List<Person> findByName(String name) {
        return personsByName.get(name);
    }

    @Override
    public List<Person> findByAddress(String address) {
        return personsByAddress.get(address);
    }

    @Override
    public @Nullable Person findByPhone(String phone) {
        return personsByPhone.get(phone);
    }

    private void addToDB(Person person) {
        addPersonToPersonsById(person);
        addPersonToPersonsByName(person);
        addPersonToPersonsByAddress(person);
        addPersonToPersonsByPhone(person);
    }

    private void deletePersonFromPersonsByName(Person person) {
        personsByName.values().forEach(it -> it.remove(person));
    }

    private void deletePersonFromPersonsByAddress(Person person) {
        personsByAddress.values().forEach(it -> it.remove(person));
    }

    private void deletePersonFromPersonsByPhone(Person person) {
        String phone = person.phoneNumber();
        personsByPhone.remove(phone);
    }

    private void addPersonToPersonsById(Person person) {
        int id = person.id();
        if (!personsById.containsKey(id)) {
            personsById.put(id, person);
        }
    }

    private void addPersonToPersonsByName(Person person) {
        String name = person.name();
        personsByName.computeIfAbsent(name, mapper -> new ArrayList<>());
        personsByName.get(name).add(person);
    }

    private void addPersonToPersonsByAddress(Person person) {
        String address = person.address();
        personsByAddress.computeIfAbsent(address, mapper -> new ArrayList<>());
        personsByAddress.get(address).add(person);
    }

    private void addPersonToPersonsByPhone(Person person) {
        String phone = person.phoneNumber();
        if (phoneIsAvailable(phone)) {
            personsByPhone.put(phone, person);
        }
    }

    private boolean phoneIsAvailable(String phone) {
        return personsByPhone.get(phone) == null;
    }

}
