package edu.hw2.Task1;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import static edu.hw2.Task1.Expr.*;
public class Task1Main {

    private static final Logger LOGGER = LogManager.getLogger();
    public static void main(String [] params){
        var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negate(new Constant(1));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, 2);
        var res = new Addition(exp, new Constant(1));

        LOGGER.info(res + " = " + res.evaluate());
    }

}
