package stock;

// importiere Klasse Date aus package date
import date.Date;

public class StockMovement implements Cloneable {

    Date date;
    double quantity;

    public StockMovement(Date date, double quantity) {
        this.date = date;
        this.quantity = quantity;
    }

    @Override
    public StockMovement clone() {
        return new StockMovement(date.clone(), quantity);
    }

    @Override
    public String toString() {
        // %s = string
        // %7.2f = floating point mit 7 Zahlen, 2 davon Nachkommastellen
        return String.format("%s %7.2f EH",
                date,
                quantity
        );
    }

}
