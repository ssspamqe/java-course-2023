package edu.hw2.Task2;


public class Square extends Rectangle {
    @Override public void setWidth(int width) {
        super.setHeight(width);
        super.setWidth(width);
    }

    @Override public void setHeight(int height) {
        super.setHeight(height);
        super.setWidth(height);
    }
}
