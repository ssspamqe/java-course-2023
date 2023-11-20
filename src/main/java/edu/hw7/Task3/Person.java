package edu.hw7.Task3;

public record Person(Integer id,
                     String name,
                     String address,
                     String phoneNumber) {

    public Person {
        if (!(id != null
            && name != null
            && address != null
            && phoneNumber != null)) {
            throw new IllegalArgumentException("Person's fields cant be null");
        }
    }
}
