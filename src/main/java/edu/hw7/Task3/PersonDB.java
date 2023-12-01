package edu.hw7.Task3;

import java.util.List;
import org.jetbrains.annotations.Nullable;

public interface PersonDB {
    void add(Person person);

    void delete(int id);

    List<Person> findByName(String name);

    List<Person> findByAddress(String address);

    @Nullable Person findByPhone(String phone);
}
