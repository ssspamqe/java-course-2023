package edu.hw3.Task5;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public class Person implements Comparable<Person> {

    private String name = "";
    private String lastName = "";

    public Person(String nameAndLastName) {
        String[] data = nameAndLastName.split(" ");
        if (data.length == 1) {
            this.name = data[0];
        } else {
            this.name = data[0];
            this.lastName = data[1];
        }

        capitalizeNames();
    }

    public Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
        capitalizeNames();
    }

    private void capitalizeNames() {
        if (!name.isEmpty()) {
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        if (!lastName.isEmpty()) {
            lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Person) {
            return hashCode() == obj.hashCode();
        }

        return false;
    }

    public String getComparableString() {
        if ("".equals(lastName)) {
            return name;
        }
        return lastName;

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName);
    }

    @Override
    public int compareTo(@NotNull Person anotherPerson) {
        return getComparableString().compareTo(anotherPerson.getComparableString());
    }

}
