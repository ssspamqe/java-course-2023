package edu.hw7.Task3.sleepy;

import edu.hw7.Task3.AbstractPersonDB;
import edu.hw7.Task3.Person;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class SleepySynchronizedPersonDB extends AbstractPersonDB {
    private static final int SLEEP_TIME = 3000;

    public synchronized void add(Person newPerson) {
        try {
            Thread.sleep(SLEEP_TIME);
            super.add(newPerson);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public synchronized void delete(int id) {
        super.delete(id);
    }

    @Override
    public synchronized @Nullable List<Person> findByName(String name) {
        return super.findByName(name);
    }

    @Override
    public synchronized @Nullable List<Person> findByAddress(String address) {
        return super.findByAddress(address);
    }

    public synchronized @Nullable Person findByPhone(String phone) {
        return super.findByPhone(phone);
    }

}
