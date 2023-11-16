package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class PersonDAO implements PersonDB {

    List<Person> persons = new ArrayList<>();

    @Override
    public synchronized void add(Person newPerson) {
        if (newPerson.id() != null) {
            return;
        }

        var personByName = findByName(newPerson.name());
        var personByAddress = findByAddress(newPerson.address());
        var personByPhone = findByPhone(newPerson.phoneNumber());

        if (personByName != null) {
            updatePersons(personByName, newPerson);
        } else if (personByAddress != null) {
            updatePersons(personByAddress, newPerson);
        } else if (personByPhone != null) {
            updatePersons(personByPhone, newPerson);
        } else {
            var id = persons.size();
            var personWithId =
                new Person(
                    id,
                    newPerson.name(),
                    newPerson.address(),
                    newPerson.phoneNumber()
                );
            persons.add(id, newPerson);
        }
    }

    @Override
    public synchronized void delete(int id) {
        persons.remove(id);
    }

    @Override
    public synchronized @Nullable Person findByName(String name) {
        return persons
            .stream()
            .filter(person ->
                person
                    .name()
                    .equals(name)

                    && person
                    .dontHaveNullFields()
            )
            .findFirst()
            .orElse(null);
    }

    @Override
    public synchronized @Nullable Person findByAddress(String address) {
        return persons
            .stream()
            .filter(person ->
                person
                    .address()
                    .equals(address)

                    && person
                    .dontHaveNullFields()
            )
            .findFirst()
            .orElse(null);
    }

    @Override
    public synchronized @Nullable Person findByPhone(String phone) {
        return persons
            .stream()
            .filter(person ->
                person
                    .address()
                    .equals(phone)

                    && person
                    .dontHaveNullFields()
            )
            .findFirst()
            .orElse(null);
    }

    private Person getMergedPerson(Person oldPerson, Person newPerson) {
        if (oldPerson.id() == null) {
            return null;
        }

        var id = oldPerson.id();

        String name = getMergedName(oldPerson, newPerson);
        String address = getMergedAddress(oldPerson, newPerson);
        String phone = getMergedPhone(oldPerson, newPerson);

        return new Person(id, name, address, phone);
    }

    private String getMergedName(Person oldPerson, Person newPerson) {
        if (newPerson.name() != null) {
            return newPerson.name();
        }
        return oldPerson.name();
    }

    private String getMergedAddress(Person oldPerson, Person newPerson) {
        if (newPerson.address() != null) {
            return newPerson.address();
        }
        return oldPerson.address();
    }

    private String getMergedPhone(Person oldPerson, Person newPerson) {
        if (newPerson.phoneNumber() != null) {
            return newPerson.phoneNumber();
        }
        return oldPerson.phoneNumber();
        ;
    }

    private void updatePersons(Person oldPerson, Person newPerson) {
        Person mergedPerson = getMergedPerson(oldPerson, newPerson);
        int id = mergedPerson.id();
        persons.set(id, mergedPerson);
    }
}
