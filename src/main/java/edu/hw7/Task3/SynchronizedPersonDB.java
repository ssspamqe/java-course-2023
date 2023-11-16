package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class SynchronizedPersonDB extends AbstractPersonDB {
    public synchronized void add(Person newPerson) {
        super.add(newPerson);
    }

    public synchronized void delete(int id) {
        super.delete(id);
    }

    @Override
    public synchronized @Nullable Person findByName(String name) {
        return super.findByName(name);
    }

    @Override
    public synchronized @Nullable Person findByAddress(String address) {
        return super.findByAddress(address);
    }
    public synchronized @Nullable Person findByPhone(String phone) {
        return super.findByPhone(phone);
    }

}
