package stock;

import date.Date;

public class ValuedStockMovement extends StockMovement implements Cloneable{
    double pricePerUnit;

    public ValuedStockMovement(Date date, double quantity, double pricePerUnit) {
        super(date, quantity); // diese Aufruf muss immer an erster Stelle stehen
        this.pricePerUnit = pricePerUnit;
    }

    public double getValue() {
        return quantity * pricePerUnit;
    }

    @Override
    public String toString() {
        // %s = string
        // %7.2f = floating point mit 7 Zahlen, 2 davon Nachkommastellen
        return String.format("%s %7.2f EH je %7.2f      EUR %10.2f",
                date,
                quantity,
                pricePerUnit,
                getValue()
        );
    }

    @Override
    public ValuedStockMovement clone() {
        return new ValuedStockMovement(date.clone(), quantity, pricePerUnit);
    }
}
