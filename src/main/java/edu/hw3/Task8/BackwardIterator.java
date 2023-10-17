package edu.hw3.Task8;

import java.util.Iterator;
import java.util.List;

public class BackwardIterator<T> implements Iterator<T> {

    private final List<T> list;
    private int currentIndex = 0;

    public BackwardIterator(List<T> list) {
        this.list = list;
        currentIndex = list.size() - 1;

    }

    @Override
    public boolean hasNext() {
        return currentIndex >= 0;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new IndexOutOfBoundsException("Iterator tries to get element from out of bounds");
        }
        currentIndex--;
        return list.get(currentIndex + 1);
    }
}
