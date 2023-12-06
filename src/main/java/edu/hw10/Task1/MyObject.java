package edu.hw10.Task1;

public class MyObject {

    private final int a;
    private final boolean b;
    private final char c;

    private MyObject(int a, boolean b, char c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public static MyObject create(int a, boolean b, char c) {
        return new MyObject(a, b, c);
    }

    @Override public String toString() {
        return "MyObject{"
            + "a=" + a
            + ", b=" + b
            + ", c=" + c
            + '}';
    }

}
