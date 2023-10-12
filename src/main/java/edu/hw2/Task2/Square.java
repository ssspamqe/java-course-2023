package edu.hw2.Task2;

//well, i dont think i had understood the task correctly
public class Square extends Rectangle {

    int side;

    @Override public void setWidth(int width) {
        side = width;
        super.setWidth(width);
    }

    @Override public void setHeight(int height) {
        side = height;
        super.setHeight(height);
    }

    public int getSquareArea() {
        return side * side;
    }
}
