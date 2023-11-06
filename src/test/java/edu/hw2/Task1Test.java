package edu.hw2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw2.Task1.Expr.Addition;
import static edu.hw2.Task1.Expr.Constant;
import static edu.hw2.Task1.Expr.Exponent;
import static edu.hw2.Task1.Expr.Multiplication;
import static edu.hw2.Task1.Expr.Negate;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {

    @Test
    @DisplayName("Classes that implement Expr interface should compute values correctly")
    void check_ExprInterface() {
        var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negate(new Constant(1));

        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, 2);
        var res = new Addition(exp, new Constant(1));

        double computedValue = res.evaluate();

        assertThat(computedValue).isEqualTo(37);
    }

}
