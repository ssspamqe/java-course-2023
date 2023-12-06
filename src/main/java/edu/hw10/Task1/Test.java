package edu.hw10.Task1;

public class Test {

    static RandomObjectGenerator rag = new RandomObjectGenerator();

    public static void main(String[] args) {
        var s = rag.nextObject(Student.class);
        System.out.println(s);
    }
}
