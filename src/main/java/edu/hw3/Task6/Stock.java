package edu.hw3.Task6;

import org.jetbrains.annotations.NotNull;

public record Stock(String name, double price) implements Comparable<Stock>{
    @Override
    public int compareTo(Stock anotherStock) {
        return Double.compare(price, anotherStock.price);
    }

    @Override
    public boolean equals(Object obj){
        if(obj == this)
            return true;
        else
            return price == ((Stock)obj).price && name.equals(((Stock)obj).name);
    }
}
