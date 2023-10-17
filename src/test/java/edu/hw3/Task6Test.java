package edu.hw3;

import edu.hw3.Task6.DefaultStockMarket;
import edu.hw3.Task6.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task6Test {

    DefaultStockMarket market;

    @BeforeEach
    void initializeObjects() {
        market = new DefaultStockMarket();
    }

    @Test
    @DisplayName("DefaultStockMarket.mostValuableStock() should return the most valuable stock")
    void check_mostValuableStock() {
        Stock stock1 = new Stock("stock1", 9999);
        Stock stock2 = new Stock("stock2", 34);
        Stock stock3 = new Stock("stock3", 9999.01);

        market.add(stock1);
        market.add(stock2);
        market.add(stock3);

        Stock mostValuableStock = market.mostValuableStock();

        assertThat(mostValuableStock).isEqualTo(stock3);
    }

    @Test
    @DisplayName("DefaultStockMarket.remove(Stock stock) should remove stock from a queue")
    void check_remove() {
        Stock stock1 = new Stock("stock1", 9999);
        Stock stock2 = new Stock("stock2", 34);
        Stock stock3 = new Stock("stock3", 9999.01);

        market.add(stock1);
        market.add(stock2);
        market.add(stock3);

        market.remove(stock3);

        Stock mostValuableStock = market.mostValuableStock();

        assertThat(mostValuableStock).isEqualTo(stock1);
    }
}
