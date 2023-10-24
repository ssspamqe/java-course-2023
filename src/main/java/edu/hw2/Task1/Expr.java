package edu.hw2.Task1;

public sealed interface Expr {
    double evaluate();

    public record Constant(int num) implements Expr {
        @Override
        public double evaluate() {
            return num;
        }
    }

    public record Negate(Expr constant) implements Expr {
        @Override
        public double evaluate() {
            return -constant.evaluate();
        }
    }

    public record Exponent(Expr constant, double num) implements Expr {
        @Override
        public double evaluate() {
            return Math.pow(constant.evaluate(), num);
        }
    }

    public record Addition(Expr a, Expr b) implements Expr {
        @Override
        public double evaluate() {
            return a.evaluate() + b.evaluate();
        }
    }

    public record Multiplication(Expr a, Expr b) implements Expr {
        @Override
        public double evaluate() {
            return a.evaluate() * b.evaluate();
        }
    }
}
