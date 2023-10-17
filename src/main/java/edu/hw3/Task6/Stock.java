package edu.hw3.Task6;

public record Stock(String name, double price) implements Comparable<Stock> {
    @Override
    public int compareTo(Stock anotherStock) {
        return Double.compare(price, anotherStock.price);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else {
            //вот тут лучше же сравнивать просто name и price, тк это будет быстрее, чем нахождение хэша?
            return hashCode() == obj.hashCode();
        }
    }

    @Override
    public int hashCode() {
        return (name + price).hashCode();
    }
}
