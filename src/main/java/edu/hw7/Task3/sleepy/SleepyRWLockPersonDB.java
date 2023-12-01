package edu.hw7.Task3.sleepy;

import edu.hw7.Task3.AbstractPersonDB;
import edu.hw7.Task3.Person;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.jetbrains.annotations.Nullable;

public class SleepyRWLockPersonDB extends AbstractPersonDB {

    private static final int SLEEP_TIME = 3000;
    private static final ReentrantReadWriteLock RW_LOCK = new ReentrantReadWriteLock();

    public void add(Person newPerson) {
        RW_LOCK.writeLock().lock();
        try {
            Thread.sleep(SLEEP_TIME);
            super.add(newPerson);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            RW_LOCK.writeLock().unlock();
        }
    }

    public void delete(int id) {
        RW_LOCK.writeLock().lock();
        try {
            super.delete(id);
        } finally {
            RW_LOCK.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        RW_LOCK.readLock().lock();
        try {
            return super.findByName(name);
        } finally {
            RW_LOCK.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        RW_LOCK.readLock().lock();
        try {
            return super.findByAddress(address);
        } finally {
            RW_LOCK.readLock().unlock();
        }
    }

    public @Nullable Person findByPhone(String phone) {
        RW_LOCK.readLock().lock();
        try {
            return super.findByPhone(phone);
        } finally {
            RW_LOCK.readLock().unlock();
        }
    }
}
