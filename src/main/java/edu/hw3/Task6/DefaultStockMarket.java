package edu.hw3.Task6;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class DefaultStockMarket implements StockMarket {

    private Queue<Stock> stocks = new PriorityQueue<>(Collections.reverseOrder());

    @Override
    public void add(Stock newStock) {
        stocks.add(newStock);
    }

    @Override
    public void remove(Stock stockToRemove) {
        stocks.remove(stockToRemove);
    }

    @Override
    public Stock mostValuableStock() {
        return stocks.peek();
    }
}
