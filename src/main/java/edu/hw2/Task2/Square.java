package edu.hw2.Task2;

public class Square extends Rectangle {
    public Square(int side) {
        super(side, side);
    }

    @Deprecated
    @Override
    public Rectangle setWidth(int width) {
        return new Square(width);
    }

    @Deprecated
    @Override
    public Rectangle setHeight(int height) {
        return new Square(height);
    }

    public Rectangle setSide(int side) {
        return new Square(side);
    }
}
