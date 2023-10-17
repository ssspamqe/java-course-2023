package edu.hw2;

import edu.hw2.Task2.Rectangle;
import edu.hw2.Task2.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    @Test
    @DisplayName("Rectangle instance should have method for getting it's area")
    void check_rectangleArea() {
        Rectangle rectangle = new Rectangle(0,0);


        rectangle = rectangle.setWidth(20);
        rectangle = rectangle.setHeight(10);
        double area = rectangle.getArea();


        assertThat(area).isEqualTo(200);
    }


    @Test
    @DisplayName("Square instance should not override Rectangle behaviour, but expand it")
    void check_squareArea(){
        Square square = new Square(0);


        square = (Square) square.setHeight(10);
        double area = square.getArea();


        assertThat(area).isEqualTo(100);
    }

}
