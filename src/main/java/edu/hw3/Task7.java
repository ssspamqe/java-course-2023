package edu.hw3;

import java.util.Comparator;

public class Task7<Type> implements Comparator<Type> {

    @Override
    public int compare(Type s1, Type s2) {

        if (s1 == null && s2 == null) {
            return 0;
        }
        if (s1 == null) {
            return 1;
        }
        if (s2 == null) {
            return -1;
        }

        if(!s1.getClass().isAssignableFrom(Comparable.class))
            return 0;

        return ((Comparable<Type>)s1).compareTo(s2);
    }
}
