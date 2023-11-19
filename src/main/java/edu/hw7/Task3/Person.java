package edu.hw7.Task3;

public record Person(Integer id,
                     String name,
                     String address,
                     String phoneNumber) {

    public Person {
        if (!(name != null
            && address != null
            && phoneNumber != null)) {
            throw new IllegalArgumentException("name, address and phoneNumber cant be null");
        }
    }

    Person(String name, String address, String phoneNumber){
        this(null,name,address,phoneNumber);
    }

    public Person gtPersonWithId(int id) {
        return new Person(id, name(), address(), phoneNumber());
    }

}
