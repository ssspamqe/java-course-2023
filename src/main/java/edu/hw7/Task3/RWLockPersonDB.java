package edu.hw7.Task3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.jetbrains.annotations.Nullable;

public class RWLockPersonDB extends AbstractPersonDB {

    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void add(Person newPerson) {
        if (newPerson.id() != null) {
            return;
        }

        var personByName = findByName(newPerson.name());
        var personByAddress = findByAddress(newPerson.address());
        var personByPhone = findByPhone(newPerson.phoneNumber());

        try {
            if (rwLock.readLock().tryLock(500, TimeUnit.MILLISECONDS)) {
                updatePerson(newPerson, personByName, personByAddress, personByPhone);
            }
        } catch (Exception ex) {
        }
    }

    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public @Nullable Person findByName(String name) {
        try {
            if (rwLock.readLock().tryLock(500, TimeUnit.MILLISECONDS)) {
                return super.findByName(name);
            }
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public @Nullable Person findByAddress(String address) {
        try {
            if (rwLock.readLock().tryLock(500, TimeUnit.MILLISECONDS)) {
                return super.findByAddress(address);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public @Nullable Person findByPhone(String phone) {
        try {
            if (rwLock.readLock().tryLock(500, TimeUnit.MILLISECONDS)) {
                return super.findByPhone(phone);
            }
        } catch (Exception e) {
        }
        return null;
    }
}
