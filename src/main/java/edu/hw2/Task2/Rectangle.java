package edu.hw2.Task2;

public class Rectangle {
    private int width=-1;
    private int height=-1;


    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double area() {
        if(width == -1 && height ==-1)
            return 0;
        else if(width == -1)
            return height*height;
        else if(height == -1)
            return width * width;
        return width * height;
    }
}
