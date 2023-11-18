package edu.hw7.Task3;

public record Person(Integer id,
                     String name,
                     String address,
                     String phoneNumber) {

    Person(String name, String address, String phoneNumber) {
        this(-1, name, address, phoneNumber);
    }

    public boolean dontHaveNullFields() {
        return id != null
            && name != null
            && address != null
            && phoneNumber != null;
    }

    public Person getPersonWithId(int id) {
        return new Person(id, name(), address(), phoneNumber());
    }

}
