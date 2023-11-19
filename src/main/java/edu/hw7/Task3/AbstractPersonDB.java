package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractPersonDB implements PersonDB {

    private int maxId = -1;

    Map<Integer, Person> personsById = new HashMap<>();
    Map<String, List<Person>> personsByName = new HashMap<>();
    Map<String, List<Person>> personsByAddress = new HashMap<>();
    Map<String, Person> personsByPhone = new HashMap<>();

    @Override
    public void add(Person newPerson) {
        if(!phoneIsAvailable(newPerson.phoneNumber()))
            return;

        Person personWithId = getPersonWithCorrectId(newPerson);
        addToDB(newPerson);
    }

    @Override
    public void delete(int id) {
        Person person = personsById.get(id);
        deletePersonInPersonsByName(person);
        deletePersonInPersonsByAddress(person);
        deletePersonInPersonsByPhone(person);
    }

    @Override
    public @Nullable List<Person> findByName(String name) {
        return personsByName.get(name);
    }

    @Override
    public @Nullable List<Person> findByAddress(String address) {
        return personsByAddress.get(address);
    }

    @Override
    public @Nullable Person findByPhone(String phone) {
        return personsByPhone.get(phone);
    }

    protected void addToDB(Person person){
        addPersonToPersonsByName(person);
        addPersonToPersonsByAddress(person);
        addPersonToPersonByPhone(person);
    }

    private void deletePersonInPersonsByName(Person person) {
        personsByName.values().forEach(it -> it.remove(person));
    }

    private void deletePersonInPersonsByAddress(Person person) {
        personsByAddress.values().forEach(it -> it.remove(person));
    }

    private void deletePersonInPersonsByPhone(Person person) {
        String phone = person.phoneNumber();
        personsByPhone.remove(phone);
    }

    private void addPersonToPersonsByName(Person person){
        String name = person.name();
        personsByName.computeIfAbsent(name, _ -> new ArrayList<>());
        personsByName.get(name).add(person);
    }

    private void addPersonToPersonsByAddress(Person person){
        String address = person.address();
        personsByAddress.computeIfAbsent(address,_ -> new ArrayList<>());
        personsByAddress.get(address).add(person);
    }

    private void addPersonToPersonByPhone(Person person){
        String phone = person.phoneNumber();
        if (phoneIsAvailable(phone)) {
            personsByPhone.put(phone,person);
        }
    }

    private Person getPersonWithCorrectId(Person person) {
        if (person.id() == null) {
            maxId++;
            return person.gtPersonWithId(maxId);
        }
        maxId = Math.max(person.id(), maxId);
        return person;
    }

    private boolean phoneIsAvailable(String phone) {
        return personsByPhone.get(phone) == null;
    }

}
