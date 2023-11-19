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
            throw new IllegalArgumentException("name, address and phoneNumber cant be null");
        }
    }
}
