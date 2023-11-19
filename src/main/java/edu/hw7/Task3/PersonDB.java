package edu.hw7.Task3;

import org.jetbrains.annotations.Nullable;
import java.util.List;

public interface PersonDB {
    void add(Person person);
    void delete(int id);
    @Nullable List<Person> findByName(String name);
    @Nullable List<Person> findByAddress(String address);
    @Nullable Person findByPhone(String phone);
}
