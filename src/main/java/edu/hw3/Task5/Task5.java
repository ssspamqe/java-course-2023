package edu.hw3.Task5;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Task5 {

    public List<Person> parseContacts(String[] contacts, String order) {

        List<Person> persons = getPersons(contacts);

        if ("DESC".equals(order)) {
            persons.sort(Collections.reverseOrder());

        } else if ("ASC".equals(order)) {
            Collections.sort(persons);

        } else {
            throw new IllegalArgumentException("Order must be either ASC or DESC");
        }

        return persons;
    }

    private List<Person> getPersons(String[] array) {
        return Arrays
            .stream(array)
            .map(Person::new)
            .collect(Collectors.toList());
    }

}
