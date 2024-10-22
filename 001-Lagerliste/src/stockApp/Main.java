package stockApp;

import date.Date;
import stock.*;

public class Main {
    public static void main(String[] args) {
//        StockList stockList = new MovingAvgStockList(); // wir nutzen Polymorphismus
        StockList stockList = new FIFOStockList(); // wir nutzen Polymorphismus

        // Einlagern, Auslagern und analysieren
        stockList.store(new ValuedStockMovement(
                new Date(2024, 1, 7),
                100,
                10));
        stockList.store(new ValuedStockMovement(
                new Date(2024, 1, 8),
                100,
                11));
        stockList.remove(new StockMovement(
                new Date(2024, 1, 9),
                30
        ));
        stockList.store(new ValuedStockMovement(
                new Date(2024, 1, 10),
                100,
                12
        ));
        stockList.remove(new StockMovement(
                new Date(2024, 1, 12),
                80
        ));

        System.out.println("Lagerbestand:");
        System.out.println(stockList.getStockStatus());

        System.out.println("Wareneingänge:");
        System.out.println(stockList.getStockIngoings());

        System.out.println("Warenausgänge:");
        System.out.println(stockList.getStockOutgoings());

    }
}
