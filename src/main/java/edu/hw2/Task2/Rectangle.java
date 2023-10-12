package edu.hw2.Task2;

public class Rectangle {
    private int width;
    private int height;

    public Rectangle(){}

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public double getRectangularArea() {
        return width * height;
    }
}
